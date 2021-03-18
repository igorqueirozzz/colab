package br.com.centralit.citcolab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Optional;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.model.User;
import br.com.centralit.citcolab.services.RetrofitServices;
import br.com.centralit.citcolab.services.UserServices;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /*
     * Referencias dos componentes da Interface Gráfica
     */
    private TextView txt_user;
    private TextView txt_locale;
    private TextView txt_office;
    private CircleImageView userImageView;
    private UserServices userServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txt_user = findViewById(R.id.txt_user);
        txt_locale = findViewById(R.id.txt_locale);
        txt_office = findViewById(R.id.txt_office);
        userImageView = findViewById(R.id.userPhoto);
       // userImageView.setImageResource(R.drawable.profilesample);

        requestUser();

    }

    public void openBankHour(View view){
        startActivity(new Intent(MainActivity.this, BankHourActivity.class));
    }
    public void openFinances(View view){
        startActivity(new Intent(MainActivity.this, FinanceActivity.class));
    }

    public void requestUser(){
        userServices = RetrofitServices.getRetrofitService().create(UserServices.class);
        Call<User> userCall = userServices.getUser(1);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    if (user != null){
                        setUser(user);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });

    }

    public void setUser(User user){
        txt_user.setText(user.getUser_name());
        txt_locale.setText(user.getOffice_local());
        txt_office.setText(user.getUser_occupation());
    }

}