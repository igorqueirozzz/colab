package br.com.app.colab.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.app.colab.R;
import br.com.app.colab.model.PointRegister;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {

    public List<PointRegister> listItems = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

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
        PointRegister pointRegisters = listItems.get(position);

        holder.date.setText(pointRegisters.getRegister_date());
        holder.local.setText(pointRegisters.getRegister_local());
        holder.time.setText(pointRegisters.getRegister_time());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public PointAdapter(List<PointRegister> pointRegisters){
        listItems = pointRegisters;
    };
}
