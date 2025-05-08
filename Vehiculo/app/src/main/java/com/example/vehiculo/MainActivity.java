package com.example.vehiculo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Vehiculo e;
    VehiculoController ec;
    EditText placa, marca, color;
    Button agregar, cancelar, mostrar,eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        mostrar = findViewById(R.id.btnlistado);
        eliminar = findViewById(R.id.btneliminar);
        placa = findViewById(R.id.edtcodigo);
        marca = findViewById(R.id.edtnombre);
        color = findViewById(R.id.edtprograma);
        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        ec = new VehiculoController(this.getApplication());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnguardar) { //agregarEstudiante(e);
            if (TextUtils.isEmpty(codigo.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                    TextUtils.isEmpty(programa.getText().toString())) {
                Toast.makeText(this, "Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
            } else {
                e = new Estudiante(codigo.getText().toString(), nombre.getText().toString(),
                        programa.getText().toString());
                if (ec.buscarEstudiante(e)) {
                    Toast.makeText(this, "Código ya existe", Toast.LENGTH_LONG).show();
                } else {
                    ec.agregarEstudiante(e);
                }
            }
        }
        if (v.getId()==R.id.btnlistado) {
            Cursor c = ec.allEstudiantes();
            String cadena = "";
            while (c.moveToNext()){
                cadena = cadena + c.getString(1) + ",";
            }
            Toast.makeText(this,cadena,Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);

        }
        if (v.getId()==R.id.btneliminar){
            ec.eliminarEstudiante(codigo.getText().toString());
        }

        if(v.getId()==R.id.btncancelar) {
            codigo.setText("");
            programa.setText("");
            nombre.setText("");
        }


    }
}
