package edu.cftic.fichapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.appk.i_esquema.IFichajeEsquema;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import edu.cftic.fichapp.beans.Fichaje;
import edu.cftic.fichapp.controlador.DB;
import edu.cftic.fichapp.dao.CRUD;
import edu.cftic.fichapp.i_dao.IFichajeDao;

public class FichajeDao extends CRUD implements IFichajeDao, IFichajeEsquema {

    private Cursor cursor;
    private ContentValues valoresIniciales;

    public FichajeDao(SQLiteDatabase bd) {
        super(bd);
    }

    @Override
    public List<Fichaje> getFicheje(int id_empleado) {
        final String argumentos[] = { String.valueOf(id_empleado)};
        final String seleccion = F_COL_ID_EMPLEADO + " = ?";
        List<Fichaje> ee = new ArrayList<Fichaje>();
        cursor = super.query(F_TABLA, F_COLUMNAS, seleccion, argumentos);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Fichaje e = cursorATabla(cursor);
                ee.add(e);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return ee;
    }

    @Override
    public List<Fichaje> getFichaje(Timestamp desde, Timestamp hasta) {
        final String argumentos[] = { String.valueOf(desde), String.valueOf(hasta) };
        final String seleccion = F_COL_INICIO + " >= ? AND " + F_COL_FIN + " <= ?";
        List<Fichaje> fichajeLista = new ArrayList<Fichaje>();
        cursor = super.query(F_TABLA, F_COLUMNAS, seleccion, argumentos);
        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Fichaje e = cursorATabla(cursor);
                fichajeLista.add(e);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return fichajeLista;
    }

    @Override
    public Fichaje getFichajeUltimo(int id_empleado) {
        final String argumentos[] = { String.valueOf(id_empleado)};
        final String seleccion = F_COL_ID_EMPLEADO + " = ?";
        String LIMITE = "1";
        Fichaje f = null;
        cursor = super.query(F_TABLA, F_COLUMNAS, seleccion, argumentos, F_COL_ID_FICHAJE + " DESC ", LIMITE);
        if(cursor != null){
            cursor.moveToFirst();
            f = cursorATabla(cursor);
        }
        cursor.close();
        return f;
    }

    @Override
    public boolean nuevo(Fichaje f) {
        setRegistro(f);
        try {
            return super.insert(F_TABLA, getRegistro()) > 0;
        } catch ( SQLiteConstraintException ex) {
            Log.i("APPK", "Fichaje nuevo DAO: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id_fichaje) {
        final String argumentos[] = { String.valueOf(id_fichaje) };
        final String seleccion = F_COL_ID_FICHAJE + " = ?";
        try {
            return super.delete(F_TABLA, seleccion, argumentos) > 0;
        } catch (SQLiteConstraintException ex) {
            Log.i("APPK", "Fichaje eliminar DAO: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Fichaje f) {
        setRegistro(f);
        final String argumentos[] = { String.valueOf(f.getId_fichaje()) };
        final String seleccion = F_COL_ID_FICHAJE + " = ?";
        try {
            return super.update(F_TABLA, getRegistro(), seleccion, argumentos) > 0;
        } catch ( SQLiteConstraintException ex) {
            Log.i("APPK", "Fichaje actualizar DAO: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    protected Fichaje cursorATabla(Cursor cursor) {
        Fichaje f = new Fichaje();
        int idIndex;
        int id_empleadoIndex;
        int inicioIndex;
        int finIndex;
        int mensajeIndex;
        if(cursor.getColumnIndex(F_COL_ID_FICHAJE) != -1){
            idIndex = cursor.getColumnIndexOrThrow(F_COL_ID_FICHAJE);
            f.setId_fichaje( cursor.getInt(idIndex));
        }
        if(cursor.getColumnIndex(F_COL_ID_EMPLEADO) != -1){
            id_empleadoIndex = cursor.getColumnIndexOrThrow(F_COL_ID_EMPLEADO);
            f.setEmpleado(DB.empleados.getEmpleadoId( cursor.getInt(id_empleadoIndex)));
        }
        if(cursor.getColumnIndex(F_COL_INICIO) != -1){
            inicioIndex = cursor.getColumnIndexOrThrow(F_COL_INICIO);
            f.setFechainicio( new  Timestamp(cursor.getLong(inicioIndex)));
        }
        if(cursor.getColumnIndex(F_COL_FIN) != -1){
            finIndex = cursor.getColumnIndexOrThrow(F_COL_FIN);
            f.setFechafin( new  Timestamp(cursor.getLong(finIndex)));
        }
        if(cursor.getColumnIndex(F_COL_MENSAJE) != -1){
            mensajeIndex = cursor.getColumnIndexOrThrow(F_COL_MENSAJE);
            f.setMensaje( cursor.getString(mensajeIndex));
        }
        return f;
    }

    private void setRegistro(Fichaje e){
        valoresIniciales = new ContentValues();
        // valoresIniciales.put(F_COL_ID_FICHAJE, e.getId_fichaje());  // Activar si el campo No es Autoincremental
        valoresIniciales.put(F_COL_ID_EMPLEADO, e.getEmpleado().getId_empleado());
        valoresIniciales.put(F_COL_INICIO, e.getFechainicio().getTime());
        valoresIniciales.put(F_COL_FIN, e.getFechafin().getTime());
        valoresIniciales.put(F_COL_MENSAJE, e.getMensaje());
    }

    private ContentValues getRegistro(){
        return valoresIniciales;
    }
}
