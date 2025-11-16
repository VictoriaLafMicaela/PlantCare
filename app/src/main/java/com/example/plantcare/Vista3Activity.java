package com.example.plantcare;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;



public class Vista3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista3);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        CheckBox checkBoxTerms = findViewById(R.id.checkbox_terms);

        btnAtras.setOnClickListener(v -> {
            Intent intent = new Intent(Vista3Activity.this, Vista2Activity.class);
            startActivity(intent);
            finish();
        });

        btnSiguiente.setOnClickListener(v -> {
            if (checkBoxTerms.isChecked()) {
                Intent intent = new Intent(Vista3Activity.this, Vista4Activity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Debes aceptar los términos y condiciones para continuar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
