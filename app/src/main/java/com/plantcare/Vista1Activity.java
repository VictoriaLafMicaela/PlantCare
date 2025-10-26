package com.example.plantcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Vista1Activity extends AppCompatActivity {

    private AdaptadorTexto adaptador;
    private List<String> textos;
    static final String PREFS = "prefs_onboarding";
    static final String KEY_NOMBRE = "nombre_usuario";

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


        RecyclerView recyclerView = findViewById(R.id.recyclerViewTextos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textos = new ArrayList<>();
        textos.add("Trasplantar al cactus");
        textos.add("Regar a Norma, la rosa");
        textos.add("Sacar foto al potus");

        adaptador = new AdaptadorTexto(textos);
        recyclerView.setAdapter(adaptador);


        Button btnMisPlantas = findViewById(R.id.btnMisPlantas);
        Button btnGaleria = findViewById(R.id.btnGaleria);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnMisPlantas.setOnClickListener(v -> {
            textos.add("Nueva planta agregada (simulado)");
            adaptador.notifyItemInserted(textos.size() - 1);
        });

        btnGaleria.setOnClickListener(v -> {
            textos.add("Abriendo galería (simulado)");
            adaptador.notifyItemInserted(textos.size() - 1);
        });


        btnCerrarSesion.setOnClickListener(v -> {
            sp.edit().clear().apply(); // borra los datos del usuario
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
