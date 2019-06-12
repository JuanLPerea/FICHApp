package edu.cftic.fichapp.beans;

import java.sql.Timestamp;
import java.util.Date;

public class Fichaje {
    private int id_fichaje;
    private Empleado empleado;
    private Timestamp fechainicio;
    private Timestamp fechafin;
    private String mensaje;

    public Fichaje() {
    }

    public Fichaje(Empleado empleado, Timestamp fechainicio, Timestamp fechafin, String mensaje) {
        this.empleado = empleado;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.mensaje = mensaje;
    }

    public Fichaje(int id_fichaje, Empleado empleado, Timestamp fechainicio, Timestamp fechafin, String mensaje) {
        this.id_fichaje = id_fichaje;
        this.empleado = empleado;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.mensaje = mensaje;
    }

    public int getId_fichaje() {
        return id_fichaje;
    }

    public void setId_fichaje(int id_fichaje) {
        this.id_fichaje = id_fichaje;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Timestamp getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Timestamp fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Timestamp getFechafin() {
        return fechafin;
    }

    public void setFechafin(Timestamp fechafin) {
        this.fechafin = fechafin;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Fichaje{" +
                "id_fichaje=" + id_fichaje +
                ", empleado=" + empleado.getId_empleado() +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
