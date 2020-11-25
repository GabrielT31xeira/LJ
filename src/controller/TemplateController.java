package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = -125520822566217671L;
	
//	private Usuario usuarioLogado = null;

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Usuario) obj;
	}

}