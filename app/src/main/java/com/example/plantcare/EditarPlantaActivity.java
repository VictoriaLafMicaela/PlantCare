package com.example.plantcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class EditarPlantaActivity extends AppCompatActivity {

    private static final int REQ_GALERIA = 300;
    private static final int REQ_PERMISO = 600;

    private Planta planta;
    private PlantaDAO plantaDAO;
    private ImageView ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_planta);

        int id = getIntent().getIntExtra("id_planta", -1);

        plantaDAO = AppDatabase.getInstance(this).plantaDAO();
        planta = plantaDAO.obtenerPorId(id);

        EditText etNombre = findViewById(R.id.etEditarNombre);
        EditText etTipo = findViewById(R.id.etEditarTipo);
        Button btnGuardar = findViewById(R.id.btnGuardarCambios);
        Button btnEliminar = findViewById(R.id.btnEliminarPlanta);
        Button btnCambiarFoto = findViewById(R.id.btnCambiarFoto);
        ivFoto = findViewById(R.id.ivFotoPlanta);

        etNombre.setText(planta.nombre);
        etTipo.setText(planta.tipo);

        if (planta.rutaFoto != null) {
            Bitmap bmp = BitmapFactory.decodeFile(planta.rutaFoto);
            if (bmp != null) {
                ivFoto.setImageBitmap(bmp);
            }
        }
