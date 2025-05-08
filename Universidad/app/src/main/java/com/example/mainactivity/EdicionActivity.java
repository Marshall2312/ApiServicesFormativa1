package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionActivity extends AppCompatActivity {

    EditText placa, marca, color;
    Button actualizar, eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        Intent i = getIntent();
        String cod = i.getStringExtra("placa");
        String nom = i.getStringExtra("marca");
        String prg = i.getStringExtra("color");
        placa = findViewById(R.id.edtpla);
        marca = findViewById(R.id.edtmar);
        color = findViewById(R.id.edtcol);
        actualizar = findViewById(R.id.btnactualizar);
        eliminar = findViewById(R.id.btneliminar);
        placa.setText(cod);
        placa.setEnabled(false);
        marca.setText(nom);
        color.setText(prg);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EdicionActivity.this);
// Add the buttons
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        VehiculoController ec = new VehiculoController(getApplication());
                        ec.eliminarVehiculo(placa.getText().toString());
                        Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setMessage("Esta seguro de Eliminar el registro?")
                        .setTitle("UNIVERSIDAD");
                builder.show();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo e = new Vehiculo(placa.getText().toString(),marca.getText().toString(),color.getText().toString());
                VehiculoController ec = new VehiculoController(getApplication());
                ec.actualizarVehiculo(e);
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
            }
        });
    }
}