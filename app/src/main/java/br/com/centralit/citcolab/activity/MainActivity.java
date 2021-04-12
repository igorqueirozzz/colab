package br.com.centralit.citcolab.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.model.CurrentUser;
import br.com.centralit.citcolab.model.User;
import br.com.centralit.citcolab.services.RetrofitServices;
import br.com.centralit.citcolab.services.UserServices;
import de.hdodenhof.circleimageview.CircleImageView;


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


    }

    @Override
    protected void onStart() {
        super.onStart();
        setUser();
    }

    public void openBankHour(View view){
        startActivity(new Intent(MainActivity.this, BankHourActivity.class));
    }
    public void openFinances(View view){
        startActivity(new Intent(MainActivity.this, FinanceActivity.class));
    }


    public void setUser(){
        txt_user.setText(CurrentUser.getUser_name());
        txt_locale.setText(CurrentUser.getLocal_office());
        txt_office.setText(CurrentUser.getOccupation());
        Glide.with(MainActivity.this).load(CurrentUser.getPhoto_profile()).into(userImageView);
    }



}