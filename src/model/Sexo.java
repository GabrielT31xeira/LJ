package model;

public enum Sexo {

	MASCULIMO(1,"Masculino"),
	FEMININO(2,"Feminino");
	
	private int id;
	private String label;
	
	Sexo(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public static Sexo valueOf(int id) {
		for (Sexo s : values()) {
			if (id == s.getId())
				return s;
		}
		return null;
	}	
	
}
