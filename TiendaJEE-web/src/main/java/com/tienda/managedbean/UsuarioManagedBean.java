package com.tienda.managedbean;

import com.tienda.entidades.Usuario;
import com.tienda.session.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean implements Serializable, ManagedBeanInterface<Usuario> {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    @PostConstruct
    public void init() {
        listaUsuario = usuarioFacadeLocal.findAll();

    }

    private List<Usuario> listaUsuario;

    private Usuario usuario;

    public UsuarioManagedBean() {
    }

    @Override
    public void nuevo() {
        usuario = new Usuario();
    }

    @Override
    public void grabar() {
        try {
            if (usuario.getCodigo() == null) {
                usuarioFacadeLocal.create(usuario);
            } else {
                usuarioFacadeLocal.edit(usuario);
            }
            usuario = null;
            listaUsuario = usuarioFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Usuario c) {
        usuario = c;
    }

    @Override
    public void eliminar(Usuario c) {
        try {
            usuarioFacadeLocal.remove(c);
            listaUsuario = usuarioFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        usuario = null;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
