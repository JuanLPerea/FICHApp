package com.example.appk.i_esquema;

public interface IEmpleadoEsquema {
    String E_TABLA = "empleado";
    String E_COL_ID_EMPLEADO = "id_empleado";
    String E_COL_NOMBRE = "nombre";
    String E_COL_USUARIO = "usuario";
    String E_COL_CLAVE = "clave";
    String E_COL_ROL = "rol";
    String E_COL_BAJA = "baja";
    String E_COL_ID_EMPRESA = "id_empresa";
    String E_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS "
            + E_TABLA
            + " ("
            + E_COL_ID_EMPLEADO
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + E_COL_NOMBRE
            + " TEXT, "
            + E_COL_USUARIO
            + " TEXT, "
            + E_COL_CLAVE
            + " TEXT, "
            + E_COL_ROL
            + " TEXT, "
            + E_COL_BAJA
            + " INTEGER, "
            + E_COL_ID_EMPRESA
            + " INTEGER "
            + ")";

    String[] E_COLUMNAS = { E_COL_ID_EMPLEADO, E_COL_NOMBRE, E_COL_USUARIO, E_COL_CLAVE,
            E_COL_ROL, E_COL_BAJA, E_COL_ID_EMPRESA };
}
