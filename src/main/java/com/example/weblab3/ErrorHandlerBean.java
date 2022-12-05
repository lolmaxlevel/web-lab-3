package com.example.weblab3;

import jakarta.inject.Named;

import javax.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;


@RequestScoped
@Named("errorHandlerBean")
public class ErrorHandlerBean {
    public String getStatusCode(){
        return FacesContext.getCurrentInstance().getAttributes().values().toString();
    }
    public String getMessage(){
        String val = (String)FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.message");
        return val;
    }

    public String getExceptionType(){
        String val = FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception_type").toString();
        return val;
    }

    public String getException(){
        String val = (String)((Exception)FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception")).toString();
        return val;
    }

    public String getRequestURI(){
        return (String)FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
    }

    public String getServletName(){
        return (String)FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.servlet_name");
    }

}