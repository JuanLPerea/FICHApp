package com.example.appk.i_esquema;

public interface IEmpresaEsquema {
    String C_TABLA = "empresa";
    String C_COL_ID_EMPRESA = "id_empresa";
    String C_COL_CIF = "cif";
    String C_COL_NOMBRE = "nombre_empresa";
    String C_COL_RESPONSABLE = "responsable";
    String C_COL_EMAIL = "email";
    String C_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS "
            + C_TABLA
            + " ("
            + C_COL_ID_EMPRESA
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_COL_CIF
            + " TEXT, "
            + C_COL_NOMBRE
            + " TEXT, "
            + C_COL_RESPONSABLE
            + " TEXT,"
            + C_COL_EMAIL
            + " TEXT "
            + ")";

    String[] C_COLUMNAS = { C_COL_ID_EMPRESA,
            C_COL_CIF, C_COL_NOMBRE, C_COL_RESPONSABLE, C_COL_EMAIL };
}
