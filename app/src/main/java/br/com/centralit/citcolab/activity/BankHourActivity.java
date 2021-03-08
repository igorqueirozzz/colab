package br.com.centralit.citcolab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.adapter.PointAdapter;
import br.com.centralit.citcolab.model.PointRegisters;

public class BankHourActivity extends AppCompatActivity {
    private RecyclerView registerPointRecyclerView;
    private List<PointRegisters> pointRegisters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hour);

        this.pontos();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        PointAdapter pointAdapter = new PointAdapter(pointRegisters);

        registerPointRecyclerView = findViewById(R.id.registerPointRecyclerView);
        registerPointRecyclerView.setLayoutManager(layoutManager);
        registerPointRecyclerView.setHasFixedSize(true);
        registerPointRecyclerView.setAdapter(pointAdapter);
        registerPointRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


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
}