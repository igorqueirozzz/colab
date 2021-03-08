package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.model.PointRegisters;

public class BankHourActivity extends AppCompatActivity {
    //Componentes de interface gráfica.
    private RecyclerView registerPointRecyclerView;
    private MaterialCalendarView monthChangeListPointRegisters;
    private TextView bankHourText;
    //Atribudos a serem alimentados com dados
    private List<PointRegisters> pointRegisters = new ArrayList<>();
    private String bankHourValue;

    private CharSequence[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hour);
        this.pontos();

        //Adaptadores e gerenciadores de compontentes de interface gráfica
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        PointAdapter pointAdapter = new PointAdapter(pointRegisters);

        //Referencias dos componentes da interface gráfica
        registerPointRecyclerView = findViewById(R.id.registerPointRecyclerView);
        registerPointRecyclerView.setLayoutManager(layoutManager);
        registerPointRecyclerView.setHasFixedSize(true);
        registerPointRecyclerView.setAdapter(pointAdapter);
        registerPointRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        monthChangeListPointRegisters = findViewById(R.id.monthChangeListPointRegisters);
        monthChangeListPointRegisters.setTitleMonths(months);
        monthChangeListPointRegisters
                .state()
                .edit()
                .setMaximumDate(CalendarDay.today())
                .commit();

        bankHourText = findViewById(R.id.txt_bankHourValue);
        


    }

    public void pontos(){
        pointRegisters.add(new PointRegisters("01/03/2021", "MCTI - Esplanada dos Ministerios", "09:00"));
        pointRegisters.add(new PointRegisters("01/03/2021", "MCTI - Esplanada dos Ministerios", "12:00"));
        pointRegisters.add(new PointRegisters("01/03/2021", "MCTI - Esplanada dos Ministerios", "13:00"));
        pointRegisters.add(new PointRegisters("01/03/2021", "MCTI - Esplanada dos Ministerios", "18:00"));
        pointRegisters.add(new PointRegisters("02/03/2021", "MCTI - Esplanada dos Ministerios", "10:00"));
        pointRegisters.add(new PointRegisters("02/03/2021", "MCTI - Esplanada dos Ministerios", "12:00"));
        pointRegisters.add(new PointRegisters("02/03/2021", "MCTI - Esplanada dos Ministerios", "13:00"));
        pointRegisters.add(new PointRegisters("02/03/2021", "MCTI - Esplanada dos Ministerios", "20:00"));
        pointRegisters.add(new PointRegisters("03/03/2021", "MCTI - Esplanada dos Ministerios", "09:00"));
        pointRegisters.add(new PointRegisters("03/03/2021", "MCTI - Esplanada dos Ministerios", "12:00"));
        pointRegisters.add(new PointRegisters("03/03/2021", "MCTI - Esplanada dos Ministerios", "13:00"));
        pointRegisters.add(new PointRegisters("03/03/2021", "MCTI - Esplanada dos Ministerios", "18:00"));
        pointRegisters.add(new PointRegisters("04/03/2021", "MCTI - Esplanada dos Ministerios", "10:00"));
        pointRegisters.add(new PointRegisters("04/03/2021", "MCTI - Esplanada dos Ministerios", "12:00"));
        pointRegisters.add(new PointRegisters("04/03/2021", "MCTI - Esplanada dos Ministerios", "13:00"));
        pointRegisters.add(new PointRegisters("04/03/2021", "MCTI - Esplanada dos Ministerios", "20:00"));

    }

    public void finishActivity(View view){
        finish();
    }
}