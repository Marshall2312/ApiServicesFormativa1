package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LibroController {
    private BaseDatos bd;
    private Context c;
    public LibroController( Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }
    public void agregarLibro(Libro e) {
        try {

            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_codigo, e.getCodigo());
            valores.put(DefBD.col_nombre, e.getNombre());
            valores.put(DefBD.col_autor, e.getAutor());
            valores.put(DefBD.col_editorial, e.getEditorial());

            if (!buscarLibro(e)) {
                SQLiteDatabase sql = bd.getWritableDatabase();
                long id = sql.insert(DefBD.tabla_lib, null, valores);

                Toast.makeText(c, "Libro registrado", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(c, "Libro ya existe", Toast.LENGTH_LONG).show();
            }
        }        catch(Exception ex){
            Toast.makeText(c, "Error agregando Libro " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarLibro(Libro e){
        String args[] = new String[] {e.getCodigo()}; //parametro del Where
        String[] columnas = {DefBD.col_codigo,DefBD.col_nombre};
        String[] orden = {DefBD.col_autor, DefBD.col_editorial};
        String col[] = new String[] {DefBD.col_codigo,DefBD.col_nombre, DefBD.col_autor, DefBD.col_editorial};
        SQLiteDatabase sql1 = bd.getReadableDatabase();
        Cursor c = sql1.query(DefBD.tabla_lib,null,"codigo=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public boolean buscarLibro(String cod){
        String args[] = new String[] {cod}; //parametro del Where
        String[] columnas = {DefBD.col_codigo,DefBD.col_nombre};
        String[] orden = {DefBD.col_autor, DefBD.col_editorial};
        String col[] = new String[] {DefBD.col_codigo,DefBD.col_nombre, DefBD.col_autor, DefBD.col_editorial};
        SQLiteDatabase sql1 = bd.getReadableDatabase();
        Cursor c = sql1.query(DefBD.tabla_lib,null,"codigo=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public Cursor allLibros(){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.query(DefBD.tabla_lib,null,null,null,null,null,null);
            return c;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta Libros " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor allLibros2(){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select codigo as _id , nombre, autor, editorial from Libro order by " + DefBD.col_codigo, null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta Libros " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void eliminarLibro(String cod){
        try{

            String[] args = {cod};
            if (!buscarLibro(cod)) {
                Toast.makeText(c, "Codigo no existe", Toast.LENGTH_LONG).show();
            }
            else {
                SQLiteDatabase sql = bd.getWritableDatabase();
                sql.delete(DefBD.tabla_lib, "codigo=?", args);
                Toast.makeText(c, "Libro eliminado", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(c, "Error eliminar Libros " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarLibro(Libro e){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {e.getCodigo()};
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_nombre, e.getNombre());
            valores.put(DefBD.col_autor, e.getAutor());
            valores.put(DefBD.col_editorial, e.getEditorial());
            sql.update(DefBD.tabla_lib,valores,"codigo=?",args);
            Toast.makeText(c, "Libro actualizado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error actualizar libros " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


