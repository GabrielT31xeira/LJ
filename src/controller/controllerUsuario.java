package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;

import DAO.DAOUsuario;
import model.Perfil;
import model.Sexo;
import model.Usuario;

@Named
@ViewScoped
public class controllerUsuario extends Controller<Usuario> implements Serializable{
	private static final long serialVersionUID = -6871228965172642700L;

	private Integer tipoFiltro;
	private String filtro;
	private List<Usuario> listaUsuario; 
	
	public void pesquisarUsu() {
		DAOUsuario dao = new DAOUsuario();
		try {
			setListaUsuario(dao.abterUsuarioPesquisa(tipoFiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaUsuario(null);
		}
	}
	public controllerUsuario() {
		super(new DAOUsuario());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuarioFlash");
		setEntity((Usuario)flash.get("usuarioFlash"));
	}
	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getListaUsuario() {
		if(listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
}
