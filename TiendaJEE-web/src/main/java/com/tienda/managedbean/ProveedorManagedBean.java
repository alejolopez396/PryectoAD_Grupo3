package com.tienda.managedbean;

import com.tienda.entidades.Proveedor;
import com.tienda.session.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "proveedorManagedBean")
@ViewScoped
public class ProveedorManagedBean implements Serializable, ManagedBeanInterface<Proveedor> {

    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;

    @PostConstruct
    public void init() {
        listaProveedor = proveedorFacadeLocal.findAll();

    }

    private List<Proveedor> listaProveedor;

    private Proveedor proveedor;

    public ProveedorManagedBean() {
    }

    @Override
    public void nuevo() {
        proveedor = new Proveedor();
    }

    @Override
    public void grabar() {
        try {
            if (proveedor.getCodigo() == null) {
                proveedorFacadeLocal.create(proveedor);
            } else {
                proveedorFacadeLocal.edit(proveedor);
            }
            proveedor = null;
            listaProveedor = proveedorFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Proveedor c) {
        proveedor = c;
    }

    @Override
    public void eliminar(Proveedor c) {
        try {
            proveedorFacadeLocal.remove(c);
            listaProveedor = proveedorFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        proveedor = null;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
