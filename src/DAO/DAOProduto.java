package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Util;
import model.Produto;
import model.Estilo;

public class DAOProduto implements DAO<Produto>{

	@Override
	public void inserir(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("produto ");
		sql.append("  (marca, num_serie, quantidade, data_fabri, cor, tamanho, estilo, preco) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?, ? ) ");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getMarca());
			stat.setInt(2, obj.getNumSerie());
			stat.setInt(3, obj.getQuantidade());
			if (obj.getDatadeFabri() != null) {
				stat.setDate(4, Date.valueOf(obj.getDatadeFabri()));
			}else {
				stat.setDate(4, null);
			}
			stat.setObject(5, obj.getCor());
			stat.setObject(6, obj.getTamanho());
			stat.setObject(7, (obj.getCor() == null ? null : obj.getEstilo().getId()));
			stat.setDouble(8, obj.getPreco());
			
			stat.execute();
			
			conn.commit();
			
			Util.addInfoMessage("Produto adcionado com sucesso");
			
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
	public void alterar(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE produto SET");
		sql.append(" marca = ?, ");
		sql.append(" num_serie = ?, ");
		sql.append(" quantidade = ?, ");
		sql.append(" data_fabri = ?, ");
		sql.append(" cor = ?, ");
		sql.append(" tamanho = ?, ");
		sql.append(" estilo = ?, ");
		sql.append(" preco = ? ");
		sql.append("WHERE ");
		sql.append(" id = ?");
		
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getMarca());
			stat.setInt(2, obj.getNumSerie());
			stat.setInt(3, obj.getQuantidade());
			if (obj.getDatadeFabri() != null) {
				stat.setDate(4, Date.valueOf(obj.getDatadeFabri()));
			}else {
				stat.setDate(4, null);
			}
			stat.setObject(5, obj.getCor());
			stat.setObject(6, obj.getTamanho());
			stat.setObject(7, (obj.getCor() == null ? null : obj.getEstilo().getId()));	
			stat.setDouble(8, obj.getPreco());

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
	public void excluir(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM produto WHERE id = ?");

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
	public List<Produto> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.marca, ");
		sql.append(" u.num_serie, ");
		sql.append(" u.quantidade, ");
		sql.append(" u.data_fabri, ");
		sql.append(" u.cor, ");
		sql.append(" u.tamanho,");
		sql.append(" u.estilo, ");
		sql.append(" preco ");
		sql.append(" FROM ");
		sql.append(" produto u ");
		sql.append("ORDER BY u.marca");

		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setMarca(rs.getString("marca"));
				produto.setNumSerie(rs.getInt("num_serie"));
				produto.setQuantidade(rs.getInt("quantidade"));
				Date data = rs.getDate("data_fabri");
				produto.setDatadeFabri(data == null ? null:data.toLocalDate());
				produto.setCor(rs.getString("cor"));
				produto.setTamanho(rs.getInt("tamanho"));
				produto.setEstilo(Estilo.valueOf(rs.getInt("estilo")));
				produto.setPreco(rs.getDouble("preco"));
				
				listaProduto.add(produto);
			}
		}catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em DAOproduto.");
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
		return listaProduto;
	}

	@Override
	public Produto obterUm(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Produto produto = null;

		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.marca, ");
		sql.append(" u.num_serie, ");
		sql.append(" u.quantidade, ");
		sql.append(" u.data_fabri, ");
		sql.append(" u.cor, ");
		sql.append(" u.tamanho,");
		sql.append(" u.estilo, ");
		sql.append(" u.preco ");
		sql.append(" FROM ");
		sql.append(" produto u ");
		sql.append(" WHERE u.id ");
		
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setMarca(rs.getString("marca"));
				produto.setNumSerie(rs.getInt("num_serie"));
				produto.setQuantidade(rs.getInt("quantidade"));
				Date data = rs.getDate("data_fabri");
				produto.setDatadeFabri(data == null ? null:data.toLocalDate());
				produto.setCor(rs.getString("cor"));
				produto.setTamanho(rs.getInt("tamanho"));
				produto.setEstilo(Estilo.valueOf(rs.getInt("estilo")));
				produto.setPreco(rs.getDouble("preco"));
			}
		}catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em produtoDAO.");
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
		
		return produto;
	}	
	public List<Produto> obterListaProduto(Integer tipo, String filtro) throws Exception{
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();
	
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.marca, ");
		sql.append(" u.num_serie, ");
		sql.append(" u.quantidade, ");
		sql.append(" u.data_fabri, ");
		sql.append(" u.cor, ");
		sql.append(" u.tamanho,");
		sql.append(" u.estilo ");
		sql.append(" u.produto");
		sql.append(" FROM ");
		sql.append(" produto u ");
		sql.append(" WHERE ");
		sql.append(" upper(u.marca) LIKE upper ( ? )");
		sql.append(" and upper(u.cor) LIKE upper ( ? )");
		sql.append(" ORDER BY u.marca");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setString(1, tipo == 1 ? "%"+ filtro + "%" : "%");
			stat.setString(2, tipo == 2 ? "%"+ filtro + "%" : "%");
			
			ResultSet rs = stat.executeQuery();

			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setMarca(rs.getString("marca"));
				produto.setNumSerie(rs.getInt("num_serie"));
				produto.setQuantidade(rs.getInt("quantidade"));
				Date data = rs.getDate("data_fabri");
				produto.setDatadeFabri(data == null ? null:data.toLocalDate());
				produto.setCor(rs.getString("cor"));
				produto.setTamanho(rs.getInt("tamanho"));
				produto.setEstilo(Estilo.valueOf(rs.getInt("estilo")));
				produto.setPreco(rs.getDouble("preco"));
			}
			
			
		}catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em produtoDAO.");
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
		
		return listaProduto;
	}
	public List<Produto> obterListaEstoque(Integer tipo, String filtro) throws Exception{
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.marca, ");
		sql.append(" u.num_serie, ");
		sql.append(" u.quantidade, ");
		sql.append(" u.data_fabri, ");
		sql.append(" u.cor, ");
		sql.append(" u.tamanho,");
		sql.append(" u.estilo, ");
		sql.append(" u.preco ");
		sql.append(" FROM ");
		sql.append(" produto u ");
		sql.append(" WHERE ");
		sql.append(" upper(u.marca) LIKE upper ( ? )");
		sql.append(" and upper(u.cor) LIKE upper ( ? )");
		sql.append(" and u.estoque > 0");
		
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setString(1, tipo == 1 ? "%"+ filtro + "%" : "%");
			stat.setString(2, tipo == 2 ? "%"+ filtro + "%" : "%");
			
			ResultSet rs = stat.executeQuery();

			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setMarca(rs.getString("marca"));
				produto.setNumSerie(rs.getInt("num_serie"));
				produto.setQuantidade(rs.getInt("quantidade"));
				Date data = rs.getDate("data_fabri");
				produto.setDatadeFabri(data == null ? null:data.toLocalDate());
				produto.setCor(rs.getString("cor"));
				produto.setTamanho(rs.getInt("tamanho"));
				produto.setEstilo(Estilo.valueOf(rs.getInt("estilo")));	
			}
			
			
		}catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em produtoDAO.");
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
		
		return listaProduto;
	}

}
