package com.example.plantcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Vista1Activity extends AppCompatActivity {

    static final String PREFS = "prefs_onboarding";
    static final String KEY_NOMBRE = "nombre_usuario";

    private RecyclerView recyclerView;
    private AdaptadorPlanta adaptadorPlantas;
    private PlantaDAO plantaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
        String nombre = sp.getString(KEY_NOMBRE, "Usuario");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bienvenido, " + nombre);
        }

        plantaDAO = AppDatabase.getInstance(this).plantaDAO();
        recyclerView = findViewById(R.id.recyclerViewTextos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Planta> plantas = plantaDAO.todas();

        adaptadorPlantas = new AdaptadorPlanta(
                plantas,
                position -> {
                    int id = adaptadorPlantas.listaPlantas.get(position).id;
                    Intent i = new Intent(Vista1Activity.this, EditarPlantaActivity.class);
                    i.putExtra("id_planta", id);
                    startActivity(i);
                }
        );

        recyclerView.setAdapter(adaptadorPlantas);

        Button btnMisPlantas = findViewById(R.id.btnMisPlantas);
        Button btnGaleria = findViewById(R.id.btnGaleria);
        Button btnAgregar = findViewById(R.id.btnAgregarPlanta);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnMisPlantas.setOnClickListener(v -> {
            List<Planta> listaActual = plantaDAO.todas();
            adaptadorPlantas.actualizarLista(listaActual);
        });

        btnGaleria.setOnClickListener(v -> {
            Intent intent = new Intent(Vista1Activity.this, GaleriaActivity.class);
            startActivity(intent);
        });

        btnAgregar.setOnClickListener(v -> {
            Intent i = new Intent(Vista1Activity.this, AgregarPlantaActivity.class);
            startActivity(i);
        });

        btnCerrarSesion.setOnClickListener(v -> {
            sp.edit().clear().apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Planta> plantasActualizadas = plantaDAO.todas();
        adaptadorPlantas.actualizarLista(plantasActualizadas);
    }
}
