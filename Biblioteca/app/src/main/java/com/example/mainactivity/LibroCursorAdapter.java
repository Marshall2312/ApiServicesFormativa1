package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class LibroCursorAdapter extends CursorAdapter {
    public LibroCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_libro,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView cod = view.findViewById(R.id.txtcodigo);
        TextView nom = view.findViewById(R.id.txtnombre);
        TextView aut = view.findViewById(R.id.txtautor);
        TextView edit = view.findViewById(R.id.txteditorial);
        String codigo = cursor.getString(0);
        String nombre = cursor.getString(1);
        String autor = cursor.getString(2);
        String editorial = cursor.getString(3);
        cod.setText(codigo);
        nom.setText(nombre);
        aut.setText(autor);
        edit.setText(editorial);
    }
}
