package com.example.plantcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

 
    static final String PREFS = "prefs_onboarding";
    static final String KEY_FINALIZADO = "onboarding_done";
    static final String KEY_NOMBRE = "nombre_usuario"; // opcional si después se pide registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // muestra logo y botón

        Button btnComenzar = findViewById(R.id.btnComenzar);

        btnComenzar.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences(PREFS, MODE_PRIVATE);
            boolean done = sp.getBoolean(KEY_FINALIZADO, false);
            String nombre = sp.getString(KEY_NOMBRE, "");


            if (!done) {
                startActivity(new Intent(this, Vista2Activity.class));
                finish();
                return;
            }


            if (nombre.isEmpty()) {
                startActivity(new Intent(this, Vista4Activity.class));
                finish();
                return;
            }


            startActivity(new Intent(this, Vista1Activity.class));
            finish();
        });
    }
}
