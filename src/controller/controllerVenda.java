package controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;
import application.Session;
import DAO.DAOVenda;
import DAO.DAOProduto;
import model.ItemVenda;
import model.Produto;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class controllerVenda implements Serializable{
	private static final long serialVersionUID = -5512578110148647018L;
	
	private Integer Tipofiltro;
	private String filtro;
	private List<Produto> listaProduto;
	
	public void pesquisar() {
		DAOProduto dao = new DAOProduto();
		try {
			setListaProduto(dao.obterListaEstoque(Tipofiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaProduto(null);
		}
	}
	public void addCarrinho(Produto produto) {
		try {	
			DAOProduto dao = new DAOProduto();
			produto = dao.obterUm(produto);
			
			List<ItemVenda> listaIV = null;
			Object obj = Session.getInstance().getAttribute("carrinho");
			
			if(obj == null) {
				listaIV = new ArrayList<ItemVenda>();
			}else {
				listaIV = (List<ItemVenda>) obj;
			}
			ItemVenda item = new ItemVenda();
			item.setProduto(produto);
			item.setPreco(produto.getPreco());
			listaIV.add(item);
			Session.getInstance().setAttribute("carrinho", listaIV);
			Util.addInfoMessage("O produto foi adcionado");
		}catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Erro ao add ao carrinho");
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
