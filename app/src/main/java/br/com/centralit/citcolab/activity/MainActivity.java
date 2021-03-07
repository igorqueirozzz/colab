package br.com.centralit.citcolab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.centralit.citcolab.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private String userID;
    private TextView txt_user;
    private TextView txt_locale;
    private TextView txt_office;
    private CircleImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_user = findViewById(R.id.txt_user);
        txt_locale = findViewById(R.id.txt_locale);
        txt_office = findViewById(R.id.txt_office);
        userImageView = findViewById(R.id.userPhoto);
        userImageView.setImageResource(R.drawable.profilesample);

    }

    public void openBankHour(View view){
        startActivity(new Intent(MainActivity.this, BankHourActivity.class));
    }
}