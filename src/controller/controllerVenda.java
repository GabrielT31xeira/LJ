package controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;
import DAO.DAOProduto;
import model.Produto;

@Named
@ViewScoped
public class controllerVenda implements Serializable{
	private static final long serialVersionUID = -5512578110148647018L;
	
	private Integer Tipofiltro;
	private String filtro;
	private List<Produto> listaProduto;
	
	public void novoProduto() {
//		Util.redirect("midia.xhtml");
	}
	public void pesquisar() {
		DAOProduto dao = new DAOProduto();
		try {
			setListaProduto(dao.obterListaEstoque(Tipofiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaProduto(null);
		}
	}
	public Integer getTipofiltro() {
		return Tipofiltro;
	}
	public void setTipofiltro(Integer tipofiltro) {
		Tipofiltro = tipofiltro;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Produto> getListaProduto() {
		if(listaProduto == null)
			listaProduto  = new ArrayList<Produto>();
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}
