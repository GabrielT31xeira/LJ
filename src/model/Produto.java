package model;

import java.time.LocalDate;

public class Produto implements Cloneable{

	private Integer id;
	private String marca;
	private Integer numSerie;
	private Integer quantidade;
	private LocalDate DatadeFabri;
	private String cor;
	private int tamanho;
	private Estilo estilo;
	private Double preco;
	
	public Produto() {
		
	}
	public Produto(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(Integer numSerie) {
		this.numSerie = numSerie;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDate getDatadeFabri() {
		return DatadeFabri;
	}
	public void setDatadeFabri(LocalDate datadeFabri) {
		DatadeFabri = datadeFabri;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Produto getClone() {
		try {
			return (Produto) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
