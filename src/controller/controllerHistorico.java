package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import DAO.DAOVenda;
import application.Session;
import application.Util;
import model.Usuario;
import model.Venda;

@Named 
@ViewScoped
public class controllerHistorico implements Serializable{
	private static final long serialVersionUID = 9192254290660867750L;
	
	private List<Venda> listaVenda;
	
	public List<Venda> getListaVenda(){
		if(listaVenda==null) {
			DAOVenda dao = new DAOVenda();
			Object obj = Session.getInstance().getAttribute("usuarioLogado");
		
			if(obj != null) {
				try {
					listaVenda = dao.obterTodos((Usuario) obj);
				}catch(Exception e) {
					Util.addErrorMessage("Nao foi possivel ver o historico");
					listaVenda = new ArrayList<Venda>();
				}
			}
		}
		return listaVenda;
	}
	public void detalhes(Venda venda) {
		
	}
	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

}
