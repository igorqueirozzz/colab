package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.centralit.citcolab.R;

public class FinanceActivity extends AppCompatActivity {

    String pdfurl = "http://www.pdf995.com/samples/pdf.pdf";

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        pdfView = findViewById(R.id.pdfViewer);
        pdfView.fromAsset("example.pdf").load();

    }

    public void finishActivity(View view) {
        finish();
    }


}