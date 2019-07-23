package Model;

public class Provincia {
	
	private int id_provincia;
	private String nombre;
	private int muertes;
	
	public Provincia() {
		
	}
	
	public Provincia(int id_provincia, String nombre) {
		super();
		this.id_provincia = id_provincia;
		this.nombre = nombre;
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getMuertes() {
		return muertes;
	}
	
	public void setMuertes(int muertes) {
		this.muertes = muertes;
	}

	@Override
	public String toString() {
		return "Provincia [id_provincia=" + id_provincia + ", nombre=" + nombre + ", muertes=" + muertes + "]";
	}


	
	

}
