package com.example.plantcare;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorGaleria extends RecyclerView.Adapter<AdaptadorGaleria.GaleriaViewHolder> {

    public interface OnItemClickListener {
        void alHacerClick(Planta planta);
    }

    private final List<Planta> lista;
    private final OnItemClickListener listener;

    public AdaptadorGaleria(List<Planta> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto_galeria, parent, false);
        return new GaleriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {
        Planta planta = lista.get(position);

        if (planta.rutaFoto != null) {
            Bitmap bmp = BitmapFactory.decodeFile(planta.rutaFoto);
            if (bmp != null) {
                holder.ivFoto.setImageBitmap(bmp);
            }
        }

        holder.itemView.setOnClickListener(v -> listener.alHacerClick(planta));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;

        public GaleriaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFotoGaleria);
        }
    }
}
