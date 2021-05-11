/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.dao.Empleado;
import org.dao.EmpleadoDAO;

/**
 *
 * @author jacielpc
 */
@ManagedBean
@SessionScoped
public class EmpleadoBean implements Serializable{

    private Empleado empleado;
    private EmpleadoDAO empleadoDAO;
    private List<Empleado> lista;
    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
        empleado = new Empleado();
        empleadoDAO = new EmpleadoDAO();
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoDAO getEmpleadoDAO() {
        return empleadoDAO;
    }

    public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public List<Empleado> getLista() {
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public void insertar() {
        boolean res;
        try {
            res = empleadoDAO.ingresar(this.empleado);
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "SIN GUARDAR DATOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS GUARDADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void actualizar() {
        boolean res;
        try {
            res = empleadoDAO.actualizar(this.empleado);
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "SIN ACTUALIZAR DATOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS ACTUALIZADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void eliminar() {
        boolean res;
        try {
            res = empleadoDAO.eliminar(this.empleado.getId());
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "DATOS NO ELIMINADOS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "DATOS ELIMINADOS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void buscarId() {
        lista = new ArrayList<>();
        boolean res;
        try {
            res = lista.add(empleadoDAO.mostrarById(empleado.getId()));
            if (res == false) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INCORRECTO", "REGISTRO NO ENCONTRADO"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "REGISTRO ENCONTRADO"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void listar() {
        lista = new ArrayList<>();
        try {
            lista = (empleadoDAO.mostrarAll());
            if (!lista.isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("EXITO", "LISTA CON REGISTROS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("EXITO", "LISTA SIN REGISTROS"));
            }
        } catch (Exception e) {
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("EXITO", "MOSTRANDO DATOS"));
    }

}
