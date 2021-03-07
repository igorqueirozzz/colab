package br.com.centralit.citcolab.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.centralit.citcolab.R;
import br.com.centralit.citcolab.model.Ponto;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {

    public List<Ponto> listItems = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView date;
        private TextView local;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.txt_date_register_point);
            local = (TextView) itemView.findViewById(R.id.txt_register_point_local);
            time = (TextView) itemView.findViewById(R.id.register_point_hour);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.point_registers_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ponto ponto = listItems.get(position);
        holder.date.setText(ponto.getDate());
        holder.local.setText(ponto.getLocal());
        holder.time.setText(ponto.getHora());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public PointAdapter(List<Ponto> pontos){
        listItems = pontos;
    };
}
