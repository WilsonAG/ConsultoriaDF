package Model;

public class CAUSA_F1 {

	private String tiempo;
	private String ubicacion;
	private String causa;
	private int total;
	public CAUSA_F1(String tiempo, String ubicacion, String causa, int total) {
		super();
		this.tiempo = tiempo;
		this.ubicacion = ubicacion;
		this.causa = causa;
		this.total = total;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
}
