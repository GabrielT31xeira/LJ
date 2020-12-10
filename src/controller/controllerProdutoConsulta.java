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
public class controllerProdutoConsulta implements Serializable {
	
	private static final long serialVersionUID = 4393598592135030784L;
	
	private Integer tipoFiltro;
	private String filtro;
	private List<Produto> listaProduto;
	
	public void novoProduto() {
//		Util.redirect(page);
	}
	public void Pesquisar(Produto produto) {
		DAOProduto dao = new DAOProduto();
		try {
			setListaProduto(dao.obterListaProduto(tipoFiltro, filtro));
		}catch (Exception e) {
			e.printStackTrace();
			setListaProduto(null);
		}
	}
	public void editar(Produto produto) {
		DAOProduto dao = new DAOProduto();
		Produto editarProduto = null;
		try {
			editarProduto = dao.obterUm(produto);
		}catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o produto no banco de dados.");
			return;
		}
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("prodFlash", editarProduto);
		novoProduto();
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
	public List<Produto> getListaProduto() {
		if (listaProduto == null) 
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
