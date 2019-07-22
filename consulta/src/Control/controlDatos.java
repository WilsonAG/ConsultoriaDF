package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Conexion.Conexion;
import Model.Canton;
import Model.EstadoCivil;
import Model.Parroquia;
import Model.Provincia;

@Named("data")
@ViewScoped
public class controlDatos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int provincia;
	private ArrayList<Provincia>provincias;
	private int canton;
	private ArrayList<Canton>cantones;
	private int parroquia;
	private ArrayList<Parroquia> parroquias;
	private int est_civil;
	private ArrayList<EstadoCivil> estados_civiles;
	
	
	@PostConstruct
	public void init() {
		this.provincias = new ArrayList<Provincia>();
		this.estados_civiles = new ArrayList<EstadoCivil>();
		cargarProvincias();
		cargarEstados();
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
	
	private void cargarEstados() {
		String sql = "SELECT * from estado_civil"; 
		ResultSet rs = null;
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			
			while (rs.next()) {
				EstadoCivil ec = new EstadoCivil(rs.getInt(1), rs.getString(2));
				this.estados_civiles.add(ec);

			}
			
			con.cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Canton> obtenerCantones() {
		cantones = new ArrayList<Canton>();
		String sql = "select id_provincia, id_canton, canton from d_ubicacion"
				+ " where id_provincia="+this.provincia+" and id_canton is not null"
				+ " GROUP BY id_provincia, id_canton, canton ORDER BY id_canton";
		ResultSet rs = null;
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			
			while (rs.next()) {
				Canton cant = new Canton(rs.getInt(1), rs.getInt(2), rs.getString(3));
				cantones.add(cant);
			}
			
			con.cerrarConexion();
			return cantones;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<Parroquia> obtenerParroquias(){
		parroquias = new ArrayList<Parroquia>();
		String sql = "select id_provincia, id_canton, id_parroquia, parroquia from d_ubicacion "
				+ "where id_provincia="+this.provincia+" and id_canton="+this.canton+" and id_parroquia is not null"
						+ " GROUP BY id_provincia, id_canton,id_parroquia, parroquia ORDER BY id_parroquia";
		ResultSet rs = null;
		
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			
			while (rs.next()) {
				Parroquia parr = new Parroquia(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
				parroquias.add(parr);
			}
			con.cerrarConexion();
			return parroquias;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void print() {
		System.out.println(this.provincia+" "+this.canton);
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

	public int getCanton() {
		return canton;
	}
	
	public void setCanton(int canton) {
		this.canton = canton;
	}
	
	public ArrayList<Canton> getCantones() {
		return cantones;
	}
	
	public void setCantones(ArrayList<Canton> cantones) {
		this.cantones = cantones;
	}
	
	public int getParroquia() {
		return parroquia;
	}
	
	public void setParroquia(int parroquia) {
		this.parroquia = parroquia;
	}
	
	public ArrayList<Parroquia> getParroquias() {
		return parroquias;
	}
	
	public void setParroquias(ArrayList<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
	public int getEst_civil() {
		return est_civil;
	}

	public void setEst_civil(int est_civil) {
		this.est_civil = est_civil;
	}

	public ArrayList<EstadoCivil> getEstados_civiles() {
		return estados_civiles;
	}

	public void setEstados_civiles(ArrayList<EstadoCivil> estados_civiles) {
		this.estados_civiles = estados_civiles;
	}

	@Override
	public String toString() {
		return "ControlDatos [provincias=" + provincias + "]";
	}
	

}
