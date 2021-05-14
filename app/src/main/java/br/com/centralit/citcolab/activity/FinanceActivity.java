package br.com.centralit.citcolab.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.model.CurrentUser;

public class FinanceActivity extends AppCompatActivity {

    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private PDFView pdfView;
    private MaterialCalendarView monthChangePaycheck;
    private LinearProgressIndicator linearProgressIndicator;
    private ProgressBar spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        pdfView = findViewById(R.id.pdfViewer);
        spinKitView = findViewById(R.id.spinFinance);
        Sprite cubeGrid = new CubeGrid();
        spinKitView.setIndeterminateDrawable(cubeGrid);
        loading();
       // new getPdfPaycheck().execute();

        monthChangePaycheck = findViewById(R.id.monthChangePaycheck);
        monthChangePaycheck.setTitleMonths(months);
        monthChangePaycheck.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                loading();
                String mesAno = String.valueOf((date.getMonth() + 1) + "" + date.getYear());
                Log.i("TESTE", "TESTE: " + mesAno );
                 String url;
               storageReference.child("docs")
                       .child("payment")
                       .child(String.valueOf(CurrentUser.getEmployerId()))
                       .child(mesAno)
                       .child("payment.pdf")
                        .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
                       new getPdfPaycheck().execute(uri.toString());
                   }
               });


            }
        });




    }

    public void finishActivity(View view) {
        finish();
    }

    class getPdfPaycheck extends AsyncTask<String, Integer, InputStream>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading();
        }

        @Override
        protected InputStream doInBackground(String... strings) {
            String stringUrl = strings[0];
            InputStream inputStream = null;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loading();
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);
            pdfView.fromStream(inputStream).load();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loaded();
                }
            },2000);
        }

    }

    public void loading(){
        pdfView.setVisibility(View.INVISIBLE);
        spinKitView.setVisibility(View.VISIBLE);
    }

    public void loaded(){
        pdfView.setVisibility(View.VISIBLE);
        spinKitView.setVisibility(View.INVISIBLE);
    }


}