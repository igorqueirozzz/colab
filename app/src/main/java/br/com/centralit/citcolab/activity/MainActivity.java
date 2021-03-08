package br.com.centralit.citcolab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    /*
     * Referencias dos componentes da Interface Gráfica
     */
    private TextView txt_user;
    private TextView txt_locale;
    private TextView txt_office;
    private CircleImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = buildUser();

        txt_user = findViewById(R.id.txt_user);
        txt_locale = findViewById(R.id.txt_locale);
        txt_office = findViewById(R.id.txt_office);
        userImageView = findViewById(R.id.userPhoto);
        userImageView.setImageResource(R.drawable.profilesample);

        txt_user.setText(user.getName());
        txt_locale.setText(user.getLocalOffice());
        txt_office.setText(user.getOffice());



    }

    public void openBankHour(View view){
        startActivity(new Intent(MainActivity.this, BankHourActivity.class));
    }

    public User buildUser(){
        return new User("5314",
                "Igor Queiroz",
                "igor.santos@centralit.com.br",
                "senha@demo",
                "Android Developer",
                "MCTI - Esplanada dos Ministérios",
                "null");
    }
}