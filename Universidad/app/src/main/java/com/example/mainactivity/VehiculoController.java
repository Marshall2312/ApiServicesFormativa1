package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class VehiculoController {
    private BaseDatos bd;
    private Context c;
    public VehiculoController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }
    public void agregarVehiculo(Vehiculo e) {
        try {

            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_placa, e.getPlaca());
            valores.put(DefBD.col_marca, e.getMarca());
            valores.put(DefBD.col_color, e.getColor());
           /* sql.execSQL("INSERT INTO " + DefBD.tabla_est + " ( " + DefBD.col_codigo + ","
                    + DefBD.col_nombre + "," + DefBD.col_programa  + ") VALUES " +
                    " ( " + e.getCodigo() +"," + e.getNombre() + "," + e.getPrograma() + ");");
            */
            if (!buscarVehiculo(e)) {
                SQLiteDatabase sql = bd.getWritableDatabase();
                long id = sql.insert(DefBD.tabla_veh, null, valores);
                //sql.execSQL("insert into " + DefBD.tabla_est + " values (" + e.getCodigo() + "," + e.getNombre() + "," + e.getPrograma() +");");
                Toast.makeText(c, "Vehiculo registrado", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(c, "Vehiculo ya existe", Toast.LENGTH_LONG).show();
            }
        }        catch(Exception ex){
                Toast.makeText(c, "Error agregando vehiculo " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    public boolean buscarVehiculo(Vehiculo e){
        String args[] = new String[] {e.getPlaca()}; //parametro del Where
        String[] columnas = {DefBD.col_placa,DefBD.col_marca};
        String[] orden = {DefBD.col_color};
        String col[] = new String[] {DefBD.col_placa,DefBD.col_marca, DefBD.col_color};
        SQLiteDatabase sql1 = bd.getReadableDatabase();
      Cursor c = sql1.query(DefBD.tabla_veh,null,"placa=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public boolean buscarVehiculo(String cod){
        String args[] = new String[] {cod}; //parametro del Where
        String[] columnas = {DefBD.col_placa,DefBD.col_placa};
        String[] orden = {DefBD.col_placa};
        String col[] = new String[] {DefBD.col_placa,DefBD.col_marca, DefBD.col_color};
        SQLiteDatabase sql1 = bd.getReadableDatabase();
        Cursor c = sql1.query(DefBD.tabla_veh,null,"placa=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public Cursor allVehiculos(){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
 Cursor c = sql.query(DefBD.tabla_veh,null,null,null,null,null,null);
            return c;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta vehiculos " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor allVehiculos2(){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select placa as _id , marca, color from vehiculo order by " + DefBD.col_placa, null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta vehiculos " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void eliminarVehiculo(String cod){
        try{

            String[] args = {cod};
            if (!buscarVehiculo(cod)) {
                //sql.execSQL("insert into " + DefBD.tabla_est + " values (" + e.getCodigo() + "," + e.getNombre() + "," + e.getPrograma() +");");
                Toast.makeText(c, "placa no existe", Toast.LENGTH_LONG).show();
            }
            else {
                SQLiteDatabase sql = bd.getWritableDatabase();
                sql.delete(DefBD.tabla_veh, "placa=?", args);
           Toast.makeText(c, "Vehiculo eliminado", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(c, "Error eliminar vehiuclos " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarVehiculo(Vehiculo e){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {e.getPlaca()};
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_marca, e.getMarca());
            valores.put(DefBD.col_color, e.getColor());
            sql.update(DefBD.tabla_veh,valores,"placa=?",args);
            Toast.makeText(c, "Vehiculo actualizado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error actualizar vehiculos " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
   }


