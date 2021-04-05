package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.dto.RegisterDTO;
import br.com.centralit.citcolab.helper.DateHelper;
import br.com.centralit.citcolab.model.PointRegister;
import br.com.centralit.citcolab.model.User;
import br.com.centralit.citcolab.services.PointRegisterServices;
import br.com.centralit.citcolab.services.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankHourActivity extends AppCompatActivity {
    //Componentes de interface gráfica.
    private RecyclerView registerPointRecyclerView;
    private MaterialCalendarView monthChangeListPointRegisters;
    private TextView bankHourText;
    //Atribudos a serem alimentados com dados
    private List<PointRegister> pointRegisters = new ArrayList<>();
    private String bankHourValue;

    private static PointRegisterServices pointRegisterServices;
    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hour);
        user = User.getCurrentUser();
        /*TODO
         *Recuperar dados do usuário e seus registros de ponto.
         */

        //Referencias dos componentes da interface gráfica
        registerPointRecyclerView = findViewById(R.id.registerPointRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        PointAdapter pointAdapter = new PointAdapter(pointRegisters);
        registerPointRecyclerView.setLayoutManager(layoutManager);
        registerPointRecyclerView.setHasFixedSize(true);
        registerPointRecyclerView.setAdapter(pointAdapter);
        registerPointRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        monthChangeListPointRegisters = findViewById(R.id.monthChangeListPointRegisters);
        monthChangeListPointRegisters.setTitleMonths(months);
        monthChangeListPointRegisters
                .state()
                .edit()
                .setMaximumDate(CalendarDay.today())
                .commit();

        bankHourText = findViewById(R.id.txt_bankHourValue);


    }

    public void requestRegisterPoint(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(BankHourActivity.this);
        alert.setTitle("Confirmação de registro de ponto")
                .setMessage("Deseja registrar seu ponto?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        registerPoint();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BankHourActivity.this, "Registro cancelado.", Toast.LENGTH_SHORT).show();
            }
        }).create().show();


    }

    private void registerPoint(){

        Date date = new Date();
        RegisterDTO register;
        register = RegisterDTO.builder()
                .register_date(date)
                .register_time(date)
                .reference(DateHelper.getRef(date))
                .user(user)
                .user_id(user.getId())
                .user_location(user.getLocal_office())
                .build();
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);
        Call<RegisterDTO> registerDTOCall = pointRegisterServices.registerNewPoint(register);
        registerDTOCall.enqueue(new Callback<RegisterDTO>() {
            @Override
            public void onResponse(Call<RegisterDTO> call, Response<RegisterDTO> response) {
                Toast.makeText(BankHourActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterDTO> call, Throwable t) {
                Toast.makeText(BankHourActivity.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finishActivity(View view){
        finish();
    }
}