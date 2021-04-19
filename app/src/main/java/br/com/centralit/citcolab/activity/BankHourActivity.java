package br.com.centralit.citcolab.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.dto.GetRegisterMonthListDTO;
import br.com.centralit.citcolab.dto.RegisterDTO;
import br.com.centralit.citcolab.helper.DateHelper;
import br.com.centralit.citcolab.model.CurrentUser;
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
    private LinearProgressIndicator linearProgressIndicator;
    //Atribudos a serem alimentados com dados
    private List<PointRegister> pointRegisters = new ArrayList<>();
    private PointAdapter pointAdapter;
    private String bankHourValue;

    private static PointRegisterServices pointRegisterServices;
    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private String monthReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hour);
        monthReference = DateHelper.getRef(new Date());


        //Referencias dos componentes da interface gráfica
        linearProgressIndicator = findViewById(R.id.pointLinearIndicator);
        linearProgressIndicator.setVisibility(View.VISIBLE);
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
                linearProgressIndicator.setVisibility(View.VISIBLE);
                pointRegisters.clear();
                pointAdapter.notifyDataSetChanged();
                String monthSelected = (date.getMonth() + 1) + "/" + date.getYear();
                getPointList(monthSelected);
            }
        });

        bankHourText = findViewById(R.id.txt_bankHourValue);

        getPointList(monthReference);

    }

    public void getPointList(String months){
        linearProgressIndicator.setVisibility(View.VISIBLE);
        pointRegisters.clear();
        pointAdapter.notifyDataSetChanged();
        GetRegisterMonthListDTO reference = new GetRegisterMonthListDTO();
        reference.setUserId(CurrentUser.getId());
        reference.setReference(months);
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);

        Call<ArrayList> registersCall = pointRegisterServices.getRegistersByMonth(reference);

        registersCall.enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call<ArrayList> call, Response<ArrayList> response) {
                if(response.code() == 200){
                    JSONArray jsonArray = new JSONArray(response.body());
                    try {
                        for (int index = 0; index < jsonArray.length(); index++){
                            JSONObject object = jsonArray.getJSONObject(index);
                            Long pointID = object.getLong("id");
                            String pointLocal = object.getString("register_local");
                            Long pointDate = object.getLong("register_date");
                            String pointTime = object.getString("register_time");
                            String pointReference = object.getString("reference");
                            Long userID = object.getLong("user_id");
                            Date date = new Date(pointDate);
                            PointRegister pointRegister = new PointRegister();
                            pointRegister.setId(pointID);
                            pointRegister.setRegister_local(pointLocal);
                            pointRegister.setRegister_date(DateHelper.formatSimple(date));
                            pointRegister.setRegister_time(pointTime);
                            pointRegister.setReference(pointReference);
                            pointRegister.setUser_id(userID);
                            pointRegisters.add(pointRegister);
                            Collections.reverse(pointRegisters
                            );
                            pointAdapter.notifyDataSetChanged();
                            linearProgressIndicator.setVisibility(View.INVISIBLE);
                        }
                    } catch (JSONException e) {
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        Log.i("ERRO 1", e.getMessage());
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String error = jsonObject.getString("errors");
                        Toast.makeText(BankHourActivity.this, error, Toast.LENGTH_SHORT).show();
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                    } catch (JSONException e) {
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                    } catch (IOException e) {
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {
                Log.i("ERRO 2", t.getMessage());
                t.printStackTrace();
                linearProgressIndicator.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void requestRegisterPoint(View view){
        registerPointRecyclerView.setVisibility(View.INVISIBLE);
        linearProgressIndicator.setVisibility(View.VISIBLE);

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
                registerPointRecyclerView.setVisibility(View.VISIBLE);
                linearProgressIndicator.setVisibility(View.INVISIBLE);
            }
        }).create().show();


    }

    private void registerPoint(){
        Date date = new Date();
        User user = new User();
        user.setId(CurrentUser.getId());
        String dateFormatedForJson = DateHelper.formatForDataBase(date);
        RegisterDTO register = new RegisterDTO();
        register.setReference(DateHelper.getRef(date));
        register.setRegister_date(dateFormatedForJson);
        register.setRegister_time(dateFormatedForJson);
        register.setUser_id(CurrentUser.getId());
        register.setUser_location(CurrentUser.getLocal_office());
        register.setUserEntity(user);


        
        pointRegisterServices = RetrofitServices.getRetrofitService().create(PointRegisterServices.class);

        Call<Void> registerDTOCall = pointRegisterServices.registerNewPoint(register);
        registerDTOCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Toast.makeText(BankHourActivity.this, "Ponto registrado com sucesso", Toast.LENGTH_SHORT).show();
                    getPointList(DateHelper.getRef(date));
                    pointAdapter.notifyDataSetChanged();
                    registerPointRecyclerView.setVisibility(View.VISIBLE);
                    linearProgressIndicator.setVisibility(View.INVISIBLE);

                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String error = jsonObject.getString("errors");
                        Toast.makeText(BankHourActivity.this, error, Toast.LENGTH_SHORT).show();
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        registerPointRecyclerView.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        registerPointRecyclerView.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                        linearProgressIndicator.setVisibility(View.INVISIBLE);
                        registerPointRecyclerView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                linearProgressIndicator.setVisibility(View.INVISIBLE);
                registerPointRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void finishActivity(View view){
        finish();
    }


}