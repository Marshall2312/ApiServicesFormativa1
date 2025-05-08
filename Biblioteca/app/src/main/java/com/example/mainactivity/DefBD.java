package com.example.mainactivity;


public class DefBD {

    public static final String nameDb = "Biblioteca";
    public static final String tabla_lib = "libro";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_autor = "autor";
    public static final String col_editorial = "editorial";

    public static final String create_tabla_lib = "CREATE TABLE IF NOT EXISTS " + DefBD.tabla_lib + " ( " +
            DefBD.col_codigo + " text primary key," +
            DefBD.col_nombre + " text," +
            DefBD.col_autor + " text," +
            DefBD.col_editorial + " text"+
            ");";


}
