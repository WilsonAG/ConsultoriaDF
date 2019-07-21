package Model;

public class Parroquia {
	
	private int id_provincia;
	private int id_canton;
	private int id_parroquia;
	private String nombre;
	
	public Parroquia(int id_provincia, int id_canton, int id_parroquia, String nombre) {
	
		this.id_provincia = id_provincia;
		this.id_canton = id_canton;
		this.id_parroquia = id_parroquia;
		this.nombre = nombre;
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public int getId_canton() {
		return id_canton;
	}

	public void setId_canton(int id_canton) {
		this.id_canton = id_canton;
	}

	public int getId_parroquia() {
		return id_parroquia;
	}

	public void setId_parroquia(int id_parroquia) {
		this.id_parroquia = id_parroquia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Parroquia [id_provincia=" + id_provincia + ", id_canton=" + id_canton + ", id_parroquia=" + id_parroquia
				+ ", nombre=" + nombre + "]";
	}
	
	

}
