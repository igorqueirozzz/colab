package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import br.com.centralit.citcolab.R;

public class FinanceActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        pdfView = findViewById(R.id.pdfViewer);
        pdfView.fromAsset("example.pdf")
                .enableAntialiasing(true)
                .load();
    }

    public void finishActivity(View view) {
        finish();
    }
}