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


        recyclerView = findViewById(R.id.recyclerViewTextos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Planta> plantas = PlantLista.obtenerPlantas();


        AdaptadorPlanta adaptadorPlantas = new AdaptadorPlanta(
                plantas,
                position -> {
                    Intent i = new Intent(Vista1Activity.this, EditarPlantaActivity.class);
                    i.putExtra("posicion", position);

                    startActivity(i);
                }
        );

        recyclerView.setAdapter(adaptadorPlantas);



        Button btnAgregar = findViewById(R.id.btnAgregarPlanta);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);


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

        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
