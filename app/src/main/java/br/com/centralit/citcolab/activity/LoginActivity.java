package br.com.centralit.citcolab.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.helper.LoadButtonLogin;

public class LoginActivity extends AppCompatActivity {

    //Atributos da interface grafica
    private EditText userLogin;
    private EditText userPassWord;
    private View buttonLogin;
    private LoadButtonLogin loadButtonEntrar;

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });
    }
}