package br.com.centralit.citcolab.activity;


import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.dto.UserCredentials;
import br.com.centralit.citcolab.helper.LoadButtonLogin;
import br.com.centralit.citcolab.model.User;
import br.com.centralit.citcolab.services.RetrofitServices;
import br.com.centralit.citcolab.services.UserServices;
import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private User currentUser;
    private UserServices userServices;

    //Atributos da interface grafica
    private EditText userLogin;
    private EditText userPassWord;
    private View buttonLogin;
    private LoadButtonLogin loadButtonEntrar;
    private UserCredentials userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencias de elementos de interface grafica.
        userLogin = findViewById(R.id.txt_editLogin);
        userPassWord = findViewById(R.id.txt_editpassword);
        buttonLogin = findViewById(R.id.login_button);
        loadButtonEntrar = new LoadButtonLogin(this, buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadButtonEntrar.buttonActivate();
                getUser();
            }
        });
    }

    private void getUser(){
         String email = userLogin.getText().toString();
         String password = userPassWord.getText().toString();
         userCredentials = new UserCredentials(email, password);


        userServices = RetrofitServices.getRetrofitService().create(UserServices.class);
        Call<User> userCall = userServices.getUser(userCredentials);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User.setCurrentUser(response.body());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Falha no login verifique sua senha ou tente novamente mais tarde " + response.code(), Toast.LENGTH_LONG).show();
                    loadButtonEntrar.buttonFinished();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha no login verifique sua senha ou tente novamente mais tarde ", Toast.LENGTH_LONG).show();
                loadButtonEntrar.buttonFinished();
            }
        });

    }

}