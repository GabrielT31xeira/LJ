//package filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import model.Perfil;
//import model.Usuario;
//
//@WebFilter(filterName = "SecuryFilter", urlPatterns = { "/faces/funci/*", "/faces/usu" })
//public class SecuryFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
////		chain.doFilter(request, response);
////		return;
//
//		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		String endereco = servletRequest.getRequestURI();
//		System.out.println(endereco);
//
//		if (endereco.equals("LJ/faces/login.xhtml")) {
//			chain.doFilter(request, response);
//			return;
//		}
//
//		HttpSession session = servletRequest.getSession(false);
//
//		Usuario usuario = null;
//		if (session != null) {
//			usuario = (Usuario) session.getAttribute("usuarioLogado");
//		}	
//		if (usuario == null) {
//			((HttpServletResponse) response).sendRedirect("LJ/faces/login.xhtml");
//		} else {
//			if (Perfil.FUNCIONARIO.equals(usuario.getPerfil())) {
//				chain.doFilter(request, response);
//				return;
//			} else {
//				((HttpServletResponse) response).sendRedirect("LJ/faces/login.xhtml");
//			}
//
//		}
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		Filter.super.init(filterConfig);
//		System.out.println("Secury Filter Funcionando");
//	}
//
//}
