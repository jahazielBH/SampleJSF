package org.dao;

import java.io.Serializable;
import org.util.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielhs
 */
public class EmpleadoDAO implements IDAO<Empleado>, Serializable{

    private ResultSet rs;
    private PreparedStatement ps;
    Empleado emple = new Empleado();

    @Override
    public boolean ingresar(Empleado pojo) {
       String insert = "INSERT INTO empleados (id,nombre,direccion,telefono) VALUES (?,?,?,?)";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getId());
            ps.setString(2, pojo.getNombre());
            ps.setString(3, pojo.getDireccion());
            ps.setString(4, pojo.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    @Override
    public boolean actualizar(Empleado pojo) {
        String insert = "UPDATE empleados SET nombre=?, direccion=?, telefono=? WHERE id=?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getNombre());
            ps.setString(2, pojo.getDireccion());
            ps.setString(3, pojo.getTelefono());
            ps.setString(4, pojo.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String insert = "DELETE FROM empleados WHERE id=?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    @Override
    public Empleado mostrarById(String id) {
        String insert = "SELECT * FROM empleados WHERE id =?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Empleado p = new Empleado();
                p.setId(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setTelefono(rs.getString(4));
                emple = p;
            } else {

                emple = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emple;
    }

    @Override
    public List<Empleado> mostrarAll() {
        String insert = "SELECT * FROM empleados ORDER BY id";
        List<Empleado> lista = new ArrayList<>();
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado p = new Empleado();
                p.setId(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setTelefono(rs.getString(4));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
