package com.example.mainactivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Vehiculo e;
    VehiculoController ec;
    EditText placa, marca, color;
    Button agregar, cancelar, mostrar, eliminar, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        mostrar = findViewById(R.id.btnlistado);
        eliminar = findViewById(R.id.btneliminar);
        buscar = findViewById(R.id.btnbuscar);
        placa = findViewById(R.id.edtplaca);
        marca = findViewById(R.id.edtmarca);
        color = findViewById(R.id.edtcolor);

        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        buscar.setOnClickListener(this);

        ec = new VehiculoController(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnguardar) {
            if (TextUtils.isEmpty(placa.getText().toString()) || TextUtils.isEmpty(marca.getText().toString()) ||
                    TextUtils.isEmpty(color.getText().toString())) {
                Toast.makeText(this, "Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
            } else {
                e = new Vehiculo(placa.getText().toString(), marca.getText().toString(),
                        color.getText().toString());
                if (ec.buscarVehiculo(e)) {
                    Toast.makeText(this, "Código ya existe", Toast.LENGTH_LONG).show();
                } else {
                    ec.agregarVehiculo(e);
                }
            }
        }

        if (v.getId() == R.id.btnlistado) {
            Cursor c = ec.allVehiculos();
            String cadena = "";
            while (c.moveToNext()) {
                cadena = cadena + c.getString(1) + ",";
            }
            Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);
        }

        if (v.getId() == R.id.btneliminar) {
            ec.eliminarVehiculo(placa.getText().toString());
        }

        if (v.getId() == R.id.btncancelar) {
            placa.setText("");
            marca.setText("");
            color.setText("");
        }

        if (v.getId() == R.id.btnbuscar) {
            String placaBuscada = placa.getText().toString();
            if (TextUtils.isEmpty(placaBuscada)) {
                Toast.makeText(this, "Por favor ingrese una placa para buscar", Toast.LENGTH_LONG).show();
            } else {

                e = new Vehiculo(placaBuscada, "", "");
                if (ec.buscarVehiculo(e)) {

                    Toast.makeText(this, "Vehículo encontrado: " + placaBuscada, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
