package edu.cftic.fichapp.i_dao;



import java.util.List;

import edu.cftic.fichapp.beans.Empleado;

public interface IEmpleadoDao {
    public Empleado getEmpleadoId(int id_empleado);
    public List<Empleado> getEmpleados();
    public Empleado primero();
    public Empleado ultimo();
    public List<String> getRoles();
    public Empleado getEmpleadoUsuarioClave(String usuario, String clave);
    public boolean nuevo(Empleado e);
    public boolean eliminar(int id_empleado);
    public boolean actualizar(Empleado e);
    public boolean baja(Empleado e);
}
