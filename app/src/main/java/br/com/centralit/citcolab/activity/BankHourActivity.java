package br.com.centralit.citcolab.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.dto.GetRegisterMonthListDTO;
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
    private PointAdapter pointAdapter;
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
        pointAdapter = new PointAdapter(pointRegisters);
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
        monthChangeListPointRegisters.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
               String monthSelected = (date.getMonth() + 1) + "/" + date.getYear();
                getPointList(monthSelected);
            }
        });

        bankHourText = findViewById(R.id.txt_bankHourValue);


    }

    public void getPointList(String months){
        GetRegisterMonthListDTO reference = GetRegisterMonthListDTO.builder()
                .userId(1L)
                .reference(months)
                .build();
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);
        Call<List<PointRegister>> registersCall = pointRegisterServices.getRegistersByMonth(reference);
        registersCall.enqueue(new Callback<List<PointRegister>>() {
            @Override
            public void onResponse(Call<List<PointRegister>> call, Response<List<PointRegister>> response) {
                pointRegisters = response.body();
                pointAdapter.notifyDataSetChanged();
                Toast.makeText(BankHourActivity.this, " "+response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PointRegister>> call, Throwable t) {
                Log.i("ERRO DO CARALHO", t.getMessage());
                Toast.makeText(BankHourActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        user = User.getCurrentUser();
        Date date = new Date();
        String dateFormatedForJson = DateHelper.formatForJson(date);
        RegisterDTO register;
        register = RegisterDTO.builder()
                .register_date(dateFormatedForJson)
                .register_time(dateFormatedForJson)
                .reference(DateHelper.getRef(date))
                .userEntity(user)
                .user_id(user.getId())
                .user_location(user.getLocal_office())
                .build();


        
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);
        Call<String> teste = pointRegisterServices.getTest();
        teste.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(BankHourActivity.this, "Codigo: "
                        + response.code() +
                        "Corpo: " + response.body() +
                        "Menssagem: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
//        Call<RegisterDTO> registerDTOCall = pointRegisterServices.registerNewPoint(register);
//        registerDTOCall.enqueue(new Callback<RegisterDTO>() {
//            @Override
//            public void onResponse(Call<RegisterDTO> call, Response<RegisterDTO> response) {
//                if(response.code() == 200){
//                    Toast.makeText(BankHourActivity.this, "Ponto registrado com sucesso", Toast.LENGTH_SHORT).show();
//                } else if(response.code() == 400){
//                    Toast.makeText(BankHourActivity.this, "Falha ao registrar o ponto", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RegisterDTO> call, Throwable t) {
//                Toast.makeText(BankHourActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.i("ERRO DO CARAI", t.getMessage());
//            }
//        });
    }

    public void finishActivity(View view){
        finish();
    }


}