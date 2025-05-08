package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VehiculoCursorAdapter extends CursorAdapter {
    public VehiculoCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_vehiculo,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView pla = view.findViewById(R.id.txtplaca);
        TextView mar = view.findViewById(R.id.txtmarca);
        TextView col = view.findViewById(R.id.txtcolor);
        String placa = cursor.getString(0);
        String marca = cursor.getString(1);
        String color = cursor.getString(2);
        pla.setText(placa);
        mar.setText(marca);
        col.setText(color);
    }
}
