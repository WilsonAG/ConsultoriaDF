package Model;

public class Canton {

	private int id_provncia;
	private int id_canton;
	private String nombre;
	
	public Canton(int id_provncia, int id_canton, String nombre) {
		
		this.id_provncia = id_provncia;
		this.id_canton = id_canton;
		this.nombre = nombre;
	}

	public int getId_provncia() {
		return id_provncia;
	}

	public void setId_provncia(int id_provncia) {
		this.id_provncia = id_provncia;
	}

	public int getId_canton() {
		return id_canton;
	}

	public void setId_canton(int id_canton) {
		this.id_canton = id_canton;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Canton [id_provncia=" + id_provncia + ", id_canton=" + id_canton + ", nombre=" + nombre + "]";
	}
	
	
}
