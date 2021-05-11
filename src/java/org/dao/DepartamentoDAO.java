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
public class DepartamentoDAO implements IDAO<Departamento>, Serializable{

    private ResultSet rs;
    private PreparedStatement ps;
    Departamento depa = new Departamento();

    /**
     *
     * @param pojo
     * @return
     */
    @Override
    public boolean ingresar(Departamento pojo) {
        String insert = "INSERT INTO departamentos (id,nombre) VALUES (?,?)";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getId());
            ps.setString(2, pojo.getNombre());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     *
     * @param pojo
     * @return
     */
    @Override
    public boolean actualizar(Departamento pojo) {
        String insert = "UPDATE departamentos SET nombre=? WHERE id=?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, pojo.getNombre());
            ps.setString(2, pojo.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean eliminar(String id) {
        String insert = "DELETE FROM departamentos WHERE id=?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Departamento mostrarById(String id) {
        String insert = "SELECT * FROM departamentos WHERE id =?";
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Departamento p = new Departamento();
                p.setId(rs.getString(1));
                p.setNombre(rs.getString(2));
                depa = p;
            } else {

                depa = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depa;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Departamento> mostrarAll() {
        String insert = "SELECT * FROM departamentos ORDER BY id";
        List<Departamento> lista = new ArrayList<>();
        try {
            ps=ConexionDB.getInstance().getConnection().prepareStatement(insert);
            rs = ps.executeQuery();
            while (rs.next()) {
                Departamento p = new Departamento();
                p.setId(rs.getString(1));
                p.setNombre(rs.getString(2));
  
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
