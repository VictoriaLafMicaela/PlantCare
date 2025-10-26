package com.example.plantcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorTexto extends RecyclerView.Adapter<AdaptadorTexto.VistaTextoViewHolder> {

    private final List<String> listaDeTextos;

    public AdaptadorTexto(List<String> listaDeTextos) {
        this.listaDeTextos = listaDeTextos;
    }

    @NonNull
    @Override
    public VistaTextoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_texto_simple, parent, false);
        return new VistaTextoViewHolder(vistaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaTextoViewHolder holder, int position) {
        String texto = listaDeTextos.get(position);
        holder.txtTexto.setText(texto);
    }

    @Override
    public int getItemCount() {
        return (listaDeTextos == null) ? 0 : listaDeTextos.size();
    }

    static class VistaTextoViewHolder extends RecyclerView.ViewHolder {
        final TextView txtTexto;

        VistaTextoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTexto = itemView.findViewById(R.id.txtTexto);
        }
    }
}
