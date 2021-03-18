package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.helper.SumHours;
import br.com.centralit.citcolab.model.PointRegisters;
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
    private List<PointRegisters> pointRegisters = new ArrayList<>();
    private String bankHourValue;

    private static PointRegisterServices pointRegisterServices;
    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hour);
        /*TODO
         *Recuperar dados do usuário e seus registros de ponto.
         */
        requestPointRegister();
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
        user.setUser_name("Igor Queiroz");
        user.setEmployer_id(5304);
        user.setUser_email("igorqueirozz@outlook.com");
        user.setUser_cpf("06870602140");
        user.setUser_occupation("Java Developer");
        user.setOffice_local("MCTI - Esplanada dos Ministérios");
        user.setUser_password("************");



    }


    public void requestPointRegister(){
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);
        Call<List<PointRegisters>> callRegisters = pointRegisterServices.getRegisterList(user);
        callRegisters.enqueue(new Callback<List<PointRegisters>>() {
            @Override
            public void onResponse(Call<List<PointRegisters>> call, Response<List<PointRegisters>> response) {
                if(response.isSuccessful()){
                    pointRegisters = response.body();

                }
            }

            @Override
            public void onFailure(Call<List<PointRegisters>> call, Throwable t) {

            }
        });
    }

    public void finishActivity(View view){
        finish();
    }
}