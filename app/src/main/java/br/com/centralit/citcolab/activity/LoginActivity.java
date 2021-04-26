package br.com.centralit.citcolab.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.api.APIError;
import br.com.centralit.citcolab.dto.UserCredentials;
import br.com.centralit.citcolab.helper.LoadButtonLogin;
import br.com.centralit.citcolab.helper.CurrentUserBuilder;
import br.com.centralit.citcolab.model.CurrentUser;
import br.com.centralit.citcolab.services.RetrofitServices;
import br.com.centralit.citcolab.services.UserServices;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private UserServices userServices;
    private Retrofit retrofitServices = RetrofitServices.getRetrofitService();
    public static CurrentUser currentUser;

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

    public void getUser(){
         String email = userLogin.getText().toString();
         String password = userPassWord.getText().toString();
         userCredentials = new UserCredentials(email, password);


        userServices = retrofitServices.create(UserServices.class);
        Call<ArrayList> userCall = userServices.getUser(userCredentials);
        userCall.enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call<ArrayList> call, Response<ArrayList> response) {
                if (response.code() == 200){
                    try {
                        currentUser = CurrentUserBuilder.build(response.body());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } catch (JSONException e) {
                        Log.i("ERRO 200 ", e.getMessage());
                        e.printStackTrace();
                    }
                } else if(response.code() == 400){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String error = jsonObject.getString("errors").replace("[\"]\"", "");
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                        loadButtonEntrar.buttonFinished();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {
                if(t.getMessage().startsWith("Failed to connect to")){
                    Toast.makeText(LoginActivity.this, "Falha ao conectar ao servidor, verifique sua conexão", Toast.LENGTH_SHORT).show();
                }
                loadButtonEntrar.buttonFinished();
            }
        });

    }

}