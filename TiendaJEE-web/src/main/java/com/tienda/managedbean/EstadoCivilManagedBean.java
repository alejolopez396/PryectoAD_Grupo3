package com.tienda.managedbean;

import com.tienda.entidades.EstadoCivil;
import com.tienda.session.EstadoCivilFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "estadoCivilManagedBean")
@ViewScoped
public class EstadoCivilManagedBean implements Serializable, ManagedBeanInterface<EstadoCivil> {

    @EJB
    private EstadoCivilFacadeLocal estadoCivilFacadeLocal;

    @PostConstruct
    public void init() {
        listaEstadoCivil = estadoCivilFacadeLocal.findAll();

    }

    private List<EstadoCivil> listaEstadoCivil;

    private EstadoCivil estadoCivil;

    public EstadoCivilManagedBean() {
    }

    @Override
    public void nuevo() {
        estadoCivil = new EstadoCivil();
    }

    @Override
    public void grabar() {
        try {
            if (estadoCivil.getCodigo() == null) {
                estadoCivilFacadeLocal.create(estadoCivil);
            } else {
                estadoCivilFacadeLocal.edit(estadoCivil);
            }
            estadoCivil = null;
            listaEstadoCivil = estadoCivilFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(EstadoCivil c) {
        estadoCivil = c;
    }

    @Override
    public void eliminar(EstadoCivil c) {
        try {
            estadoCivilFacadeLocal.remove(c);
            listaEstadoCivil = estadoCivilFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }

    }

    @Override
    public void cancelar() {
        estadoCivil = null;
    }

    public List<EstadoCivil> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<EstadoCivil> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

}
