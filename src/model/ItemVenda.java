package model;

public class ItemVenda {

	private Integer id;  
	private Double preco;
	private Produto produto;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Produto getMidia() {
		return produto;
	}
	public void setMidia(Produto produto) {
		this.produto = produto;
	}
}
