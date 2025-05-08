package com.example.mainactivity;


    public class DefBD {

        public static final String nameDb = "VEHICULOS";
        public static final String tabla_veh = "vehiculo";
        public static final String col_placa = "placa";
        public static final String col_marca = "marca";
        public static final String col_color = "color";

        public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " + DefBD.tabla_veh + " ( " +
                DefBD.col_placa + " text primary key," +
                DefBD.col_marca + " text," +
                DefBD.col_color + " text" +
                ");";


}
