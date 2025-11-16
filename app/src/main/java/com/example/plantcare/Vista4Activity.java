package com.example.plantcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Vista4Activity extends AppCompatActivity {

    static final String PREFS = "prefs_onboarding";
    static final String KEY_FINALIZADO = "onboarding_done";
    static final String KEY_NOMBRE = "nombre_usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista4);

        EditText edtNombre = findViewById(R.id.edtNombre);
        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        btnAtras.setOnClickListener(v -> finish());

        btnSiguiente.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                edtNombre.setError("Ingresá tu nombre");
                return;
            }
            SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
            sp.edit()
                    .putString(KEY_NOMBRE, nombre)
                    .putBoolean(KEY_FINALIZADO, true)
                    .apply();

            Toast.makeText(this, "Bienvenido, " + nombre, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Vista1Activity.class));
            finish();
        });
    }
}
