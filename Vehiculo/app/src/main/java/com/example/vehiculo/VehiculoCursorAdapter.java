package com.example.vehiculo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VehiculoCursorAdapter extends CursorAdapter {
    public VehiculoCursorAdapter(ListadoActivity context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_vehiculo,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView cod = view.findViewById(R.id.txtplaca);
        TextView nom = view.findViewById(R.id.txtmarca);
        TextView prg = view.findViewById(R.id.txtcolor);
        String codigo = cursor.getString(0);
        String nombre = cursor.getString(1);
        String prog = cursor.getString(2);
        cod.setText(codigo);
        nom.setText(nombre);
        prg.setText(prog);
    }
}
