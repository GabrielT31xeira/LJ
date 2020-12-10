package controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import DAO.DAO;
import DAO.DAOUsuario;
import application.Util;
import application.Session;
import model.Usuario;

@Named
@RequestScoped
public class controllerPerfil extends Controller<Usuario> {

	public controllerPerfil(DAO<Usuario> dao) {
		super(dao);
	}

	private Usuario usuario;

	public List<Usuario> getPerfil() {
		DAOUsuario dao = new DAOUsuario();
		List<Usuario> list = new ArrayList<>();
		try {
			usuario = dao.obterUm(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Erro ao buscar o perfil");
			return null;
		}
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("perfilUsuario");
		setEntity((Usuario) flash.get("perfilUsuario"));
		list.add(usuario);
		return list;
	}

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
}
