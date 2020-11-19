package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


import DAO.DAOUsuario;
import model.Sexo;
import model.Usuario;

@Named
@ViewScoped
public class controllerUsuario extends Controller<Usuario> implements Serializable{
	private static final long serialVersionUID = -6871228965172642700L;

	public controllerUsuario() {
		super(new DAOUsuario());
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
}
