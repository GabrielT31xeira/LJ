package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import DAO.DAOVenda;
import application.Session;
import application.Util;
import model.ItemVenda;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class controllerCarrinho implements Serializable{
	private static final long serialVersionUID = -952667649776070037L;
	
	private Venda venda;
	
	public Venda getVenda() {
		if(venda==null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		Object obj = Session.getInstance().getAttribute("carrinho");
		if(obj != null) {
			venda.setListaItemVenda((List<ItemVenda>) obj);
		}
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public void apagar() {
		DAOVenda dao = new DAOVenda();
		try {
			dao.excluir(getVenda());
		}catch (Exception e) {
			Util.addErrorMessage("Nao foi possivel apagar");
		}
		
	}
	public void finalizar() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if(obj == null) {
			Util.addErrorMessage("SEM login");
			return;
		}
		getVenda().setUsuario((Usuario) obj);
		DAOVenda dao = new DAOVenda();
		
		try {
			dao.inserir(getVenda());
			Util.addErrorMessage("Incluido");
			
			Session.getInstance().setAttribute("carrinho", null);
			setVenda(null);
			
		}catch (Exception e) {
			Util.addErrorMessage("Erro na inclusao de Venda.");
			e.printStackTrace();
		}
	}
	

}
