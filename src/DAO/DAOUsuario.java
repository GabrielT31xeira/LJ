package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Util;
import model.Usuario;
import model.Perfil;
import model.Sexo;

public class DAOUsuario implements DAO<Usuario>{

	@Override
	public void inserir(Usuario obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("usuario ");
		sql.append("  (nome, email, idade, senha, sexo, perfil) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ? ) ");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getEmail());
			stat.setInt(3, obj.getIdade());
			stat.setString(4, Util.hash(obj.getEmail()+obj.getSenha()));
			stat.setObject(5, (obj.getSexo() == null ? null : obj.getSexo().getId()));
			stat.setObject(6, (obj.getPerfil() == null ? null : obj.getPerfil().getId()));

			stat.execute();
			
			conn.commit();
			
		}catch (SQLException e) {
			
			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");
			
		}finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public void alterar(Usuario obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET");
		sql.append(" nome = ?, ");
		sql.append(" email = ?, ");
		sql.append(" idade = ?, ");
		sql.append(" senha = ?, ");
		sql.append(" sexo = ?, ");
		sql.append(" perfil = ? ");
		sql.append("WHERE ");
		sql.append(" id = ?");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getEmail());
			stat.setInt(3, obj.getIdade());
			stat.setString(4, obj.getSenha());
			stat.setObject(5, (obj.getSexo() == null ? null : obj.getSexo().getId()));
			stat.setObject(6, (obj.getPerfil() == null ? null : obj.getPerfil().getId()));

			stat.execute();
			
			conn.commit();
			
		}catch (SQLException e) {
			
			System.out.println("Erro ao realizar um comando sql de insert em incluir.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback em incluir.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir em incluir");
			
		}finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement em incluir");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco em incluir.");
				e.printStackTrace();
			}
		}
		if (exception != null) {
			throw exception;
		}
	}

	@Override
	public void excluir(Usuario obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM usuario WHERE id = ?");

		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;		
	}

	@Override
	public List<Usuario> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.nome, ");
		sql.append(" u.email, ");
		sql.append(" u.idade, ");
		sql.append(" u.senha, ");
		sql.append(" u.sexo, ");
		sql.append(" u.perfil ");
		sql.append(" FROM ");
		sql.append(" usuario u ");
		sql.append("ORDER BY u.nome");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdade(rs.getInt("idade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				listaUsuario.add(usuario);
			}
		}catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em DAOusuario.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}
		if (exception != null) {
			throw exception;
		}
		return listaUsuario;
	}

	@Override
	public Usuario obterUm(Usuario obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Usuario usuario = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.nome, ");
		sql.append(" u.email, ");
		sql.append(" u.idade, ");
		sql.append(" u.senha, ");
		sql.append(" u.sexo, ");
		sql.append(" u.perfil ");
		sql.append(" FROM ");
		sql.append(" usuario u ");
		sql.append("WHERE u.id = ?");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdade(rs.getInt("idade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));

			}
		}catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario em ObterUm.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em DAOusuario em ObterUm.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement em ObterUm");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}
		if (exception != null) {
			throw exception;
		}
		return usuario;
	}
	public Usuario obterUsuario(String email, String senha) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Usuario usuario = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.nome, ");
		sql.append(" u.email, ");
		sql.append(" u.idade, ");
		sql.append(" u.senha, ");
		sql.append(" u.sexo, ");
		sql.append(" u.perfil ");
		sql.append("FROM  ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append(" u.email = ? ");
		sql.append(" AND u.senha = ? ");
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, email);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdade(rs.getInt("idade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));

			}

		} catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario em ObterUsuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO em ObterUsuario.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return usuario;
	}
	public Usuario obterUsuarioNome(String nome) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Usuario usuario = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.nome, ");
		sql.append(" u.email, ");
		sql.append(" u.idade, ");
		sql.append(" u.senha, ");
		sql.append(" u.sexo, ");
		sql.append(" u.perfil ");
		sql.append("FROM  ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append(" u.nome = ? ");
		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, nome);

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdade(rs.getInt("idade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));

			}

		} catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario em ObterUsuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO em ObterUsuario.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return usuario;
	}
	
	public List<Usuario> abterUsuarioPesquisa(Integer tipo, String filtro) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.nome, ");
		sql.append(" u.email, ");
		sql.append(" u.idade, ");
		sql.append(" u.senha, ");
		sql.append(" u.sexo, ");
		sql.append(" u.perfil ");
		sql.append("FROM  ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append(" upper(u.nome) LIKE upper (?) ");
		sql.append(" and upper(u.email) LIKE upper (?) ");
		sql.append("ORDER BY u.nome");
		
		PreparedStatement stat = null;

		try {
			
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, tipo == 1 ? "%" + filtro +"%":"%");
			stat.setString(2, tipo == 1 ? "%" + filtro +"%":"%");
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setIdade(rs.getInt("idade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				
				listaUsuario.add(usuario);
			}
		}catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do Usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em DAOUsuario.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaUsuario;
			
	}

}
