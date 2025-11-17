package com.example.plantcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPlanta extends RecyclerView.Adapter<AdaptadorPlanta.PlantaViewHolder> {

    public List<Planta> listaPlantas;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public AdaptadorPlanta(List<Planta> listaPlantas, OnItemClickListener listener) {
        this.listaPlantas = listaPlantas;
        this.listener = listener;
    }

    public void actualizarLista(List<Planta> nuevas) {
        this.listaPlantas = nuevas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_planta, parent, false);
        return new PlantaViewHolder(vista, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        Planta p = listaPlantas.get(position);
        holder.txtNombre.setText(p.nombre);
        holder.txtTipo.setText(p.tipo);
    }

    @Override
    public int getItemCount() {
        return listaPlantas != null ? listaPlantas.size() : 0;
    }

    static class PlantaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtTipo;

        public PlantaViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombrePlanta);
            txtTipo = itemView.findViewById(R.id.txtTipoPlanta);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
