package controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import DAO.DAOProduto;
import model.Estilo;
import model.Produto;

@Named
@ViewScoped
public class controllerProduto extends Controller<Produto> implements Serializable{
	private static final long serialVersionUID = 2659253013733425367L;

	public controllerProduto() {
		super(new DAOProduto());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("prodFlash");
		setEntity((Produto)flash.get("prodFlash"));
	}
	@Override
	public Produto getEntity() {
		if (entity == null)
			entity = new Produto();
		return entity;
	}	
	public Estilo[] getListaEstilo() {
		return Estilo.values();
	}
}
