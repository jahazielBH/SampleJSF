/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.dao.User;

/**
 *
 * @author jacielpc
 */
@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {

    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {

    }

    public void departamento() {
        try {
            User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("departamentos.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void empleados() {
        try {
            User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("empleados.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void menu(){
        try {
            User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
}