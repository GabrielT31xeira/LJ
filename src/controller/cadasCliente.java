package controller;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import DAO.DAOUsuario;
import model.Perfil;
import model.Sexo;
import model.Usuario;

@Named
@ViewScoped
public class cadasCliente extends Controller<Usuario> implements Serializable{
	private static final long serialVersionUID = 8499775268735909762L;
		
	public cadasCliente() {
		super(new DAOUsuario());
		getEntity().setPerfil(Perfil.USUARIO);
	}
	public void cadasC() {
		try {
			dao.inserir(getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
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
	
}
