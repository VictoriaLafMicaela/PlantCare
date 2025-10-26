package com.example.plantcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Vista2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista2);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        btnAtras.setOnClickListener(v -> {
            Intent intent = new Intent(Vista2Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(Vista2Activity.this, Vista3Activity.class);
            startActivity(intent);
        });
    }
}
