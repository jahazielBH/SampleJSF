/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.dao.Login;
import org.dao.User;

/**
 *
 * @author jacielpc
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private User user;
    private Login login;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        user = new User();
        login = new Login();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    public String iniciarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        String redireccion = null;
        User us;
        try {
            us = login.iniciarSesion(this.user);
            if(us != null){
                context.getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "index?faces-redirect=true";
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Credenciales Incorrectas"));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Campos Vacios"));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "o Usuario no existe"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", "Error"));
        }
        return redireccion;
    }
    
    public void verificarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            User us = (User) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("login.xhtml");
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
    
    public void cerrarSesion(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error")); 
        }     
    }
    
    public void registrarUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("faces/signup.xhtml");
        } catch (Exception e) {
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error")); 
        }     
    }
}