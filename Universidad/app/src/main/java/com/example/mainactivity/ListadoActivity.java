package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {

    ListView listado;
    VehiculoController vehiculoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstlistado);
        vehiculoController = new VehiculoController(this);
        Cursor c = vehiculoController.allVehiculos2();
        VehiculoCursorAdapter ecursor = new VehiculoCursorAdapter(this,c,false);
        listado.setAdapter(ecursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cod = view.findViewById(R.id.txtplaca);
                TextView nombre = view.findViewById(R.id.txtmarca);
                TextView programa = view.findViewById(R.id.txtcolor);
                Toast.makeText(getApplicationContext(),cod.getText().toString() + "," + nombre.getText().toString()
                        + "," + programa.getText().toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), EdicionActivity.class);
                i.putExtra("placa", cod.getText().toString());
                i.putExtra("marca", nombre.getText().toString());
                i.putExtra("color", programa.getText().toString());
                startActivity(i);
            }
        });
    }
}
