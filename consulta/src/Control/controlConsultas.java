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

@Named("consulta")
@ViewScoped
public class controlConsultas implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Provincia> provincias;

	@PostConstruct
	public void init() {
		cargarMuertesXProvinca();
	}

	private void cargarMuertesXProvinca() {
		provincias = new ArrayList<Provincia>();
		String sql = "select p.provincia, COUNT(*) from defunciones d, provincia p "
				+ "WHERE d.prov_fall=p.id_provincia GROUP BY p.provincia ORDER BY p.provincia";
		ResultSet rs = null;
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);

			while (rs.next()) {
				Provincia p = new Provincia();
				p.setNombre(rs.getString(1));
				p.setMuertes(rs.getInt(2));
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
	
	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	
	@Override
	public String toString() {
		return "controlConsultas [provincias=" + provincias + "]";
	}


}
