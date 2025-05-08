package com.example.mainactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {

    ListView listado;
    LibroController LibroController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstlistado);
        LibroController = new LibroController(this);
        Cursor c = LibroController.allLibros2();
        LibroCursorAdapter ecursor = new LibroCursorAdapter(this,c,false);
        listado.setAdapter(ecursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cod = view.findViewById(R.id.txtcodigo);
                TextView nombre = view.findViewById(R.id.txtnombre);
                TextView autor = view.findViewById(R.id.txtautor);
                TextView editorial = view.findViewById(R.id.txteditorial);
                Toast.makeText(getApplicationContext(),cod.getText().toString() + "," + nombre.getText().toString()
                        + "," + autor.getText().toString()  + "," + editorial.getText().toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), EdicionActivity.class);
                i.putExtra("codigo", cod.getText().toString());
                i.putExtra("nombre", nombre.getText().toString());
                i.putExtra("autor", autor.getText().toString());
                i.putExtra("editorial", autor.getText().toString());
                startActivity(i);
            }
        });
    }
}
