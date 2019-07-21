package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Conexion.Conexion;
import Model.Provincia;

@Named("provi")
@ViewScoped
public class provinciaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int provincia;
	private ArrayList<Provincia>provincias;
	
	@PostConstruct
	public void init() {
		this.provincias = new ArrayList<Provincia>();
		cargarProvincias();

	}
	
	private void cargarProvincias() {
		String sql = "select * from provincia"; 
		ResultSet rs = null;
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			
			while (rs.next()) {
				Provincia p = new Provincia(rs.getInt(1), rs.getString(2));
				this.provincias.add(p);
			}
			
			con.cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	


	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> allProvincias) {
		this.provincias = allProvincias;
	}


	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "ControlDatos [provincias=" + provincias + "]";
	}
	

}
