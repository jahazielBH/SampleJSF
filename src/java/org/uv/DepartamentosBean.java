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
import org.dao.Departamento;
import org.dao.DepartamentoDAO;

/**
 *
 * @author jacielpc
 */
@ManagedBean
@SessionScoped
public class DepartamentosBean implements Serializable {

    private Departamento departamento;
    private DepartamentoDAO departamentoDAO;
    private List<Departamento> lista;

    /**
     * Creates a new instance of DepartamentosBean
     */
    public DepartamentosBean() {
        departamento = new Departamento();
        departamentoDAO = new DepartamentoDAO();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentoDAO getDepartamentoDAO() {
        return departamentoDAO;
    }

    public void setDepartamentoDAO(DepartamentoDAO departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    public List<Departamento> getLista() {
        return lista;
    }

    public void setLista(List<Departamento> lista) {
        this.lista = lista;
    }

    public void insertar() {
        boolean res;
        try {
            res = departamentoDAO.ingresar(this.departamento);
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
            res = departamentoDAO.actualizar(this.departamento);
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
            res = departamentoDAO.eliminar(this.departamento.getId());
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
            res = lista.add(departamentoDAO.mostrarById(departamento.getId()));
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
            lista = (departamentoDAO.mostrarAll());
            if (!lista.isEmpty()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("EXITO", "LISTA CON REGISTROS"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("INCORRECTO", "LISTA SIN REGISTROS"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

}
