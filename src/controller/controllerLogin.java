package controller;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import application.Session;
import application.Util;
import DAO.DAOUsuario;
import model.Usuario;

@Named
@RequestScoped
public class controllerLogin {

	private Usuario usuario;

	public void Logar() {
		
		DAOUsuario dao = new DAOUsuario();
		try {
			Usuario usuarioLogado = 
					dao.obterUsuario(getUsuario().getEmail(), 
							Util.hash(getUsuario().getEmail()+getUsuario().getSenha()));
			if (usuarioLogado == null)
				Util.addErrorMessage("Usuário ou senha inválido.");
			else {
				// Usuario existe com as credenciais
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				if(usuarioLogado.getPerfil().getId()==1) {
					Util.redirect("templateFunci.xhtml");					
				}else {
					Util.redirect("templateUsu.xhtml");					
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao verificar o Login. Entre em contato pelo email: contato@email.com.br");
		}
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
