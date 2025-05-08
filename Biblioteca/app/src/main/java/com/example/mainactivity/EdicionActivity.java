package com.example.mainactivity; // Ajusta el paquete si es necesario

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EdicionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtCodigoLibro, edtNombreLibro, edtAutor, edtEditorial;
    private Button btnActualizarLibro, btnEliminarLibro, btnCancelar;
    private LibroController libroController;
    private String codigoLibroOriginal; // Para almacenar el código del libro que estamos editando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_libro);

        edtCodigoLibro = findViewById(R.id.edt_edit_codigo_libro);
        edtNombreLibro = findViewById(R.id.edt_edit_nombre_libro);
        edtAutor = findViewById(R.id.edt_edit_autor);
        edtEditorial = findViewById(R.id.edt_edit_editorial);

        btnActualizarLibro = findViewById(R.id.btn_actualizar_libro);
        btnEliminarLibro = findViewById(R.id.btn_eliminar_libro);
        btnCancelar = findViewById(R.id.btn_cancelar_edicion_libro);

        btnActualizarLibro.setOnClickListener(this);
        btnEliminarLibro.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        libroController = new LibroController(this);

        // Obtener los datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            codigoLibroOriginal = extras.getString("codigo");
            String nombre = extras.getString("nombre");
            String autor = extras.getString("autor");
            String editorial = extras.getString("editorial");

            // Llenar los campos de edición con los datos recibidos
            edtCodigoLibro.setText(codigoLibroOriginal);
            edtNombreLibro.setText(nombre);
            edtAutor.setText(autor);
            edtEditorial.setText(editorial);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_actualizar_libro) {
            actualizarLibro();
        } else if (id == R.id.btn_eliminar_libro) {
            eliminarLibro();
        } else if (id == R.id.btn_cancelar_edicion_libro) {
            finish(); // Cierra la actividad
        }
    }

    private void actualizarLibro() {
        String nombre = edtNombreLibro.getText().toString().trim();
        String autor = edtAutor.getText().toString().trim();
        String editorial = edtEditorial.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(autor) || TextUtils.isEmpty(editorial)) {
            Toast.makeText(this, "El nombre, autor y editorial son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        Libro libroActualizado = new Libro(codigoLibroOriginal, nombre, autor, editorial);
        int rowsAffected = libroController.actualizarLibro(libroActualizado);

        if (rowsAffected > 0) {
            finish(); // Cierra la actividad después de actualizar
        }
    }

    private void eliminarLibro() {
        // Puedes agregar una confirmación antes de eliminar (por ejemplo, un AlertDialog)
        int rowsAffected = libroController.eliminarLibro(codigoLibroOriginal);
        if (rowsAffected > 0) {
            finish(); // Cierra la actividad después de eliminar
        }
    }
}