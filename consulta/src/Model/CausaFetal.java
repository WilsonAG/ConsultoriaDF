package Model;

public class CausaFetal {
	private String id_causa_fetal;
	private String nombre;

	public CausaFetal(String id_causa_fetal, String nombre) {
		super();
		this.id_causa_fetal = id_causa_fetal;
		this.nombre = nombre;
	}

	public String getId_causa_fetal() {
		return id_causa_fetal;
	}

	public void setId_causa_fetal(String id_causa_fetal) {
		this.id_causa_fetal = id_causa_fetal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CausaFetal [id_causa_fetal=" + id_causa_fetal + ", nombre=" + nombre + "]";
	}

	

}
