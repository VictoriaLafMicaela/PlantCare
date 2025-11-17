package com.example.plantcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
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
        Button btnAgregarRiegoCalendario = findViewById(R.id.btnAgregarRiegoCalendario);
        ivFoto = findViewById(R.id.ivFotoPlanta);

        etNombre.setText(planta.nombre);
        etTipo.setText(planta.tipo);

        if (planta.rutaFoto != null) {
            Bitmap bmp = BitmapFactory.decodeFile(planta.rutaFoto);
            if (bmp != null) {
                ivFoto.setImageBitmap(bmp);
            }
        }

        btnGuardar.setOnClickListener(v -> {
            planta.nombre = etNombre.getText().toString().trim();
            planta.tipo = etTipo.getText().toString().trim();
            plantaDAO.actualizar(planta);
            finish();
        });

        btnEliminar.setOnClickListener(v -> {
            plantaDAO.eliminar(planta);
            finish();
        });

        btnCambiarFoto.setOnClickListener(v -> pedirPermisoGaleria());

        btnAgregarRiegoCalendario.setOnClickListener(v -> {
            String titulo = "Regar " + planta.nombre;

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, titulo);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, "Recordatorio de riego");

            startActivity(intent);
        });
    }

    private void pedirPermisoGaleria() {
        if (checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{android.Manifest.permission.READ_MEDIA_IMAGES},
                    REQ_PERMISO
            );
        } else {
            abrirGaleria();
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_GALERIA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_PERMISO &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            abrirGaleria();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_GALERIA && resultCode == RESULT_OK && data != null) {
            try {
                Uri uri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                File archivo = new File(getFilesDir(), "planta_edit_" + System.currentTimeMillis() + ".jpg");
                FileOutputStream fos = new FileOutputStream(archivo);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.close();

                planta.rutaFoto = archivo.getAbsolutePath();
                plantaDAO.actualizar(planta);

                ivFoto.setImageBitmap(bitmap);

                Toast.makeText(this, "Foto actualizada", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Error al cambiar foto", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
