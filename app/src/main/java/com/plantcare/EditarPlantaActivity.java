package com.example.plantcare;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;

public class EditarPlantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int posicion = getIntent().getIntExtra("posicion", -1);
        Planta planta = PlantLista.obtenerPlantas().get(posicion);

        setContentView(R.layout.activity_editar_planta);

        EditText etNombre = findViewById(R.id.etEditarNombre);
        EditText etTipo = findViewById(R.id.etEditarTipo);
        Button btnGuardar = findViewById(R.id.btnGuardarCambios);
        Button btnEliminar = findViewById(R.id.btnEliminarPlanta);


        etNombre.setText(planta.nombre);
        etTipo.setText(planta.tipo);


        btnGuardar.setOnClickListener(v -> {
            planta.nombre = etNombre.getText().toString().trim();
            planta.tipo = etTipo.getText().toString().trim();
            finish();
        });


        btnEliminar.setOnClickListener(v -> {
            PlantLista.obtenerPlantas().remove(posicion);
            finish();
        });
    }
}
