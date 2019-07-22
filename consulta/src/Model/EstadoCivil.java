package Model;

public class EstadoCivil {
	
	private int id_estCivil;
	private String nombre;
	
	public EstadoCivil(int id_estCivil, String nombre) {
		super();
		this.id_estCivil = id_estCivil;
		this.nombre = nombre;
	}

	public int getId_estCivil() {
		return id_estCivil;
	}

	public void setId_estCivil(int id_estCivil) {
		this.id_estCivil = id_estCivil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EstadoCivil [id_estCivil=" + id_estCivil + ", nombre=" + nombre + "]";
	}
	
	
	
	

	
}
