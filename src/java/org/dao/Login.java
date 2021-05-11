/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.util.ConexionDB;
import org.util.Encriptar;

/**
 *
 * @author jacielpc
 */
public class Login implements Serializable{

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement ps;
    private List<User> lista;
    Encriptar enc = new Encriptar();

    public List<User> getLista() {
        return lista;
    }

    public void setLista(List<User> lista) {
        this.lista = lista;
    }

    public User iniciarSesion(User pojo) {
        User usuario = null;
        String consulta;
        try {
            consulta = "SELECT * FROM usuarios where usr = ? and pass = ?";
            conn = ConexionDB.getInstance().getConnection();
            ps = conn.prepareStatement(consulta);
            ps.setString(1, pojo.getUser());
            ps.setString(2, enc.getMD5(pojo.getPassword()));
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            if (rs.next()) {
                User usr = new User();
                usr.setId(rs.getString(1));
                usr.setUser(rs.getString(2));
                usr.setPassword(rs.getString(3));
                lista.add(usr);
            }
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error de inicio de sesión " + e.getMessage());
        }
        return usuario;
    }
    
}
