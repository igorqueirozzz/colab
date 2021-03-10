package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
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

public class FinanceActivity extends AppCompatActivity {

    String pdfurl = "http://www.africau.edu/images/default/sample.pdf";
    String pdfurl2 = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    String pdfurl3 = "http://www.pdf995.com/samples/pdf.pdf";
    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    private PDFView pdfView;
    private MaterialCalendarView monthChangePaycheck;
    private LinearProgressIndicator linearProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        pdfView = findViewById(R.id.pdfViewer);
        linearProgressIndicator = findViewById(R.id.pdfProgressIndicator);
        new getPdfPaycheck().execute(pdfurl3);

        monthChangePaycheck = findViewById(R.id.monthChangePaycheck);
        monthChangePaycheck.setTitleMonths(months);
        monthChangePaycheck.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mesAno = String.valueOf((date.getMonth() + 1) + "" + date.getYear());
                Log.i("TESTE", "TESTE: " + mesAno );
                if(mesAno.equals("12021")){
                    new getPdfPaycheck().execute(pdfurl);
                } else if (mesAno.equals("22021")){
                    new getPdfPaycheck().execute(pdfurl2);
                }
                else if (mesAno.equals("32021")){
                    new getPdfPaycheck().execute(pdfurl3);
                }
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
            linearProgressIndicator.setVisibility(View.VISIBLE);
            pdfView.setVisibility(View.INVISIBLE);
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
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);
            linearProgressIndicator.setVisibility(View.INVISIBLE);
            pdfView.fromStream(inputStream).load();
            pdfView.setVisibility(View.VISIBLE);
        }




    }


}