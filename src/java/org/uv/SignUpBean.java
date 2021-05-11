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
import org.dao.SignUP;
import org.dao.User;

/**
 *
 * @author jacielpc
 */
@ManagedBean
@SessionScoped
public class SignUpBean implements Serializable {

    private User user;
    private SignUP signup;

    /**
     * Creates a new instance of SignUpBean
     */
    public SignUpBean() {
        user = new User();
        signup = new SignUP();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SignUP getSignup() {
        return signup;
    }

    public void setSignup(SignUP signup) {
        this.signup = signup;
    }

    public void registrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        User us;
        try {
            us = signup.registarUsuario(user);
            if(us!=null){
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Usuario Registrado"
                    + user.getUser()));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
    }

    public void regresar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
            e.getMessage();
        }
    }
}
