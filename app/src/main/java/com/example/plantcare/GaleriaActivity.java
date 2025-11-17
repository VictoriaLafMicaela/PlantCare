package com.example.plantcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GaleriaActivity extends AppCompatActivity {

    private PlantaDAO plantaDAO;
    private RecyclerView recyclerView;
    private AdaptadorGaleria adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        plantaDAO = AppDatabase.getInstance(this).plantaDAO();
        recyclerView = findViewById(R.id.recyclerGaleria);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<Planta> plantas = plantaDAO.todas();

        adaptador = new AdaptadorGaleria(plantas, planta -> {
            Intent i = new Intent(GaleriaActivity.this, EditarPlantaActivity.class);
            i.putExtra("id_planta", planta.id);
            startActivity(i);
        });

        recyclerView.setAdapter(adaptador);
    }
}
