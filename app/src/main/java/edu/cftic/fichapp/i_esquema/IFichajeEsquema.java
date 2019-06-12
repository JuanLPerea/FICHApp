package com.example.appk.i_esquema;

public interface IFichajeEsquema {
    String F_TABLA = "fichaje";
    String F_COL_ID_FICHAJE = "id_fichaje";
    String F_COL_ID_EMPLEADO = "id_empleado";
    String F_COL_INICIO = "fechainicio";
    String F_COL_FIN = "fechafin";
    String F_COL_MENSAJE = "mensaje";
    String F_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS "
            + F_TABLA
            + " ("
            + F_COL_ID_FICHAJE
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + F_COL_ID_EMPLEADO
            + " INTEGER, "
            + F_COL_INICIO
            + " INTEGER, "
            + F_COL_FIN
            + " INTEGER,"
            + F_COL_MENSAJE
            + " TEXT "
            + ")";

    String[] F_COLUMNAS = { F_COL_ID_FICHAJE, F_COL_ID_EMPLEADO, F_COL_INICIO, F_COL_FIN, F_COL_MENSAJE };
}
