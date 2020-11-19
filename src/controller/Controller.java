package controller;

import java.util.ArrayList;
import java.util.List;

import application.Util;
import DAO.DAO;

public abstract class Controller<T> {
	protected T entity;
	private DAO<T> DAO = null;
	private List<T> listaEntity;
	
	public Controller(DAO<T> dao) {
		super();
		this.DAO = dao;
	}

	public void incluir() {
		try {
			DAO.inserir(getEntity());
			Util.addInfoMessage("Inclus�o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("N�o � possivel fazer uma inclus�o.");
			e.printStackTrace();
		}
	}
	
	public void alterar() {
		try {
			DAO.alterar(getEntity());
			Util.addInfoMessage("Altera��o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("N�o � possivel fazer uma altera��o.");
			e.printStackTrace();
		}
	}

	public void excluir() {
		excluir(getEntity());
	}

	public void excluir(T entity) {
		try {
			DAO.excluir(entity);
			Util.addInfoMessage("Exclus�o realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("N�o � possivel fazer uma exclus�o.");
			e.printStackTrace();
		}
	}
	
	public void editar(T entity) {
		try {
			setEntity(DAO.obterUm(entity));
		} catch (Exception e) {
			Util.addErrorMessage("Problema ao editar.");
			e.printStackTrace();
		}
	}
	
	public List<T> getListaEntity() {
		if (listaEntity == null) {
			try {
				listaEntity = DAO.obterTodos();
			} catch (Exception e) {
				e.printStackTrace();
				listaEntity = new ArrayList<T>();
			}
		}	
		return listaEntity;
	}
	
	public void limpar() {
		entity = null;
		listaEntity = null;
	}
	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
