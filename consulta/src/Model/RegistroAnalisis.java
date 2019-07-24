package Model;

public class RegistroAnalisis {
	
	private int anio;
	private int trim;
	private String ubic;
	private String estado_civil;
	private int fallecidos;
	
	public RegistroAnalisis() {
		
	}
	
	public RegistroAnalisis(int anio, int trim, String ubic, String estado_civil, int fallecidos) {
		super();
		this.anio = anio;
		this.trim = trim;
		this.ubic = ubic;
		this.estado_civil = estado_civil;
		this.fallecidos = fallecidos;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getTrim() {
		return trim;
	}

	public void setTrim(int trim) {
		this.trim = trim;
	}

	public String getUbic() {
		return ubic;
	}

	public void setUbic(String ubic) {
		this.ubic = ubic;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public int getFallecidos() {
		return fallecidos;
	}

	public void setFallecidos(int fallecidos) {
		this.fallecidos = fallecidos;
	}
	
	
	

}
