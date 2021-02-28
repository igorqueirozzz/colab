package br.com.centralit.citcolab.helper;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import br.com.centralit.citcolab.R;


public class LoadButtonLogin {

    private CardView cardView;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private ProgressBar progressBar;


    public LoadButtonLogin(Context ct, View view) {

        cardView = view.findViewById(R.id.cardViewButton);
        relativeLayout = view.findViewById(R.id.relativeButton);
        textView = view.findViewById(R.id.txt_loginBtn);
        progressBar = view.findViewById(R.id.progressBtn);

    }

   public void buttonActivate() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Aguarde...");
    }


   public void buttonFinished() {
        progressBar.setVisibility(View.GONE);
        textView.setText("Entrar");
    }


}
