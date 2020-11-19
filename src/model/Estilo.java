package model;

public enum Estilo {
	
	ESPORTIVO(1,"Esportivo"),
	SOCIAL(2,"Social"),
	FUTSAL(3,"Futsal");

	private int id;
	private String label;
	
	Estilo(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public static Estilo valueOf(int id) {
		for (Estilo estilo : values()) {
			if (id == estilo.getId())
				return estilo;
		}
		return null;
	}	
	
}
