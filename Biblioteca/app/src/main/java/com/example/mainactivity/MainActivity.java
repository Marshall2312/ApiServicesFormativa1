package com.example.mainactivity;

import android.os.Bundle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Libro e;
    LibroController ec;
    EditText codigo, nombre, autor, editorial;
    Button agregar, cancelar, mostrar,eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        mostrar = findViewById(R.id.btnlistado);
        eliminar = findViewById(R.id.btneliminar);
        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        autor = findViewById(R.id.edtautor);
        editorial = findViewById(R.id.edteditorial);
        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        ec = new LibroController(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnguardar) { ;
            if (TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                    TextUtils.isEmpty(autor.getText().toString()) ||  TextUtils.isEmpty(editorial.getText().toString())) {
                Toast.makeText(this, "Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
            } else {
                e = new Libro(codigo.getText().toString(), nombre.getText().toString(),
                        autor.getText().toString(), editorial.getText().toString());
                if (ec.buscarLibro(e)) {
                    Toast.makeText(this, "Código ya existe", Toast.LENGTH_LONG).show();
                } else {
                    ec.agregarLibro(e);
                }
            }
        }
        if (v.getId()==R.id.btnlistado) {
            Cursor c = ec.allLibros();
            String cadena = "";
            while (c.moveToNext()){
                cadena = cadena + c.getString(1) + ",";
            }
            Toast.makeText(this,cadena,Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);

        }
        if (v.getId()==R.id.btneliminar){
            ec.eliminarLibro(codigo.getText().toString());
        }

        if(v.getId()==R.id.btncancelar) {
            codigo.setText("");
            autor.setText("");
            nombre.setText("");
            editorial.setText("");
        }


    }
}
