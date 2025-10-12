package com.example.plantcare;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class Vista3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista3);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        btnAtras.setOnClickListener(v -> {
            Intent intent = new Intent(Vista3Activity.this, Vista2Activity.class);
            startActivity(intent);
            finish();
        });

        btnSiguiente.setOnClickListener(v -> {

            finish();
        });
    }
}
