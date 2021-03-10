package br.com.centralit.citcolab.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.helper.Base64Custom;
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
       // userImageView.setImageResource(R.drawable.profilesample);

        txt_user.setText(user.getName());
        txt_locale.setText(user.getLocalOffice());
        txt_office.setText(user.getOffice());

        new MyAsyncTask().execute(user.getPhotoProfileURL());

    }

    public void openBankHour(View view){
        startActivity(new Intent(MainActivity.this, BankHourActivity.class));
    }
    public void openFinances(View view){
        startActivity(new Intent(MainActivity.this, FinanceActivity.class));
    }

    public User buildUser(){
        return new User("5314",
                "Igor Queiroz",
                "igor.santos@centralit.com.br",
                "senha@demo",
                "Android Developer",
                "MCTI - Esplanada dos Ministérios",
                "https://media-exp1.licdn.com/dms/image/C4E03AQHX-oz_s5uMgA/profile-displayphoto-shrink_800_800/0/1614857183154?e=1620864000&v=beta&t=PkV_SDZsEl2oIzjdBeL47Nl6M0GmcyLkZQHhzhCxu_o");
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            userImageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            InputStream inputStream = null;
            String urlString = strings[0];
            Bitmap bitmap = null;

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }
}