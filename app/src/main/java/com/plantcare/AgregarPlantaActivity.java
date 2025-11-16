package com.example.plantcare;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarPlantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_planta);
        EditText etNombre = findViewById(R.id.etNombrePlanta);
        EditText etTipo = findViewById(R.id.etTipoPlanta);
        Button btnGuardar = findViewById(R.id.btnGuardarPlanta);

        btnGuardar.setOnClickListener(v -> {

            String nombre = etNombre.getText().toString().trim();
            String tipo = etTipo.getText().toString().trim();

            if (!nombre.isEmpty()) {


                Planta nueva = new Planta(nombre, tipo);

                PlantLista.agregarPlanta(nueva);


                finish();
            }
        });

    }
}
