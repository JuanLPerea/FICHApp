package edu.cftic.fichapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class CRUD {

    public SQLiteDatabase bd;

    public CRUD(SQLiteDatabase bd) {
        this.bd = bd;
    }

    public long insert(String tabla, ContentValues valores) {
        return bd.insert(tabla, null, valores);
    }

    public int update(String tabla, ContentValues valores,
                      String seleccion, String[] argumentos) {
        return bd.update(tabla, valores, seleccion, argumentos);
    }

    public int delete(String tabla, String seleccion,
                      String[] argumentos) {
        return bd.delete(tabla, seleccion, argumentos);
    }

    public Cursor query(String tabla, String[] columnas, String seleccion, String[] argumentos) {
        Cursor cursor =  null ;
        try {
            cursor = bd.query(tabla, columnas, seleccion, argumentos, null, null, null);
        } catch (Throwable t){
            Log.i("APP", "Error CRUD -1 ");
            return cursor;
        }
        return cursor;
    }

    protected abstract <T> T cursorATabla(Cursor cursor);


    public Cursor query(String tabla, String[] columnas, String seleccion, String[] argumentos, String orden) {
        Cursor cursor =  null ;
        try {
            cursor = bd.query(tabla, columnas, seleccion, argumentos, null, null, orden);
        } catch (Throwable t) {
            Log.i("APP", "Error CRUD -2 ");
            return cursor;
        }
        return cursor;
    }

    public Cursor query(String tabla, String[] columnas, String seleccion, String[] argumentos, String orden,
                        String limite) {
        Cursor cursor =  null ;
        try {
            cursor = bd.query(tabla, columnas, seleccion, argumentos, null, null, orden, limite);
        } catch (Throwable t) {
            Log.i("APP", "Error CRUD -3 ");
            return cursor;
        }
        return cursor;
    }

    public Cursor query(String tabla, String[] columnas, String seleccion, String[] argumentos, String agrupado,
                        String having, String orden, String limite) {
        Cursor cursor =  null ;
        try {
            cursor = bd.query(tabla, columnas, seleccion, argumentos, agrupado, having, orden, limite);
        } catch (Throwable t) {
            Log.i("APP", "Error CRUD -4 ");
            return cursor;
        }
        return cursor;
    }

}
