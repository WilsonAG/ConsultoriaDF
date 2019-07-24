package Control;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.bar.BarChartModel;

import Conexion.Conexion;
import Model.Canton;
import Model.CausaFetal;
import Model.EstadoCivil;
import Model.Parroquia;
import Model.Provincia;
import Model.RegistroAnalisis;

@Named("data")
@ViewScoped
public class controlDatos implements Serializable {

	/**
	 * 
	 */
	private final long serialVersionUID = 1L;
	private static int provincia;
	private ArrayList<Provincia> provincias;
	private static int canton;
	private ArrayList<Canton> cantones;
	private static int parroquia;
	private ArrayList<Parroquia> parroquias;
	private static int est_civil;
	private ArrayList<EstadoCivil> estados_civiles;
	private static String cau_fetal;
	private ArrayList<CausaFetal> causas_fetales;
	private static int anio;
	private static int trimestre;
	private static int resp=0;
	private ArrayList<RegistroAnalisis> registros;

	@PostConstruct
	public void init() {
		this.provincias = new ArrayList<Provincia>();
		this.estados_civiles = new ArrayList<EstadoCivil>();
		this.causas_fetales = new ArrayList<CausaFetal>();
		this.cantones = new ArrayList<Canton>();
		this.parroquias = new ArrayList<Parroquia>();
		cargarCausas();
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
		String sql = "select id_provincia, id_canton, canton from d_ubicacion" + " where id_provincia=" + this.provincia
				+ " and id_canton is not null" + " GROUP BY id_provincia, id_canton, canton ORDER BY id_canton";
		ResultSet rs = null;
		System.out.println(sql);
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

	public ArrayList<Parroquia> obtenerParroquias() {
		parroquias = new ArrayList<Parroquia>();
		String sql = "select id_provincia, id_canton, id_parroquia, parroquia from d_ubicacion " + "where id_provincia="
				+ this.provincia + " and id_canton=" + this.canton + " and id_parroquia is not null"
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

	private void cargarCausas() {
		String sql = "SELECT * from CAUSA_FETAL";
		ResultSet rs = null;
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);

			while (rs.next()) {
				CausaFetal cf = new CausaFetal(rs.getString(1), rs.getString(2));
				this.causas_fetales.add(cf);

			}

			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void numeroMuertes() {
		int r1=0;
		int r2=0;
		if (getTrimestre()==1) {
			r1=1;
			r2=3;
		}else if (getTrimestre()==2) {
			r1=4;
			r2=6;
		}else if (getTrimestre()==3) {
			r1=7;
			r2=9;
		}else if (getTrimestre()==4) {
			r1=10;
			r2=12;
		}
		
		String sql = "select count(*) from defunciones df where df.anio_fall=" + getAnio() + " and df.mes_fall <="
				+ r2 + " and df.mes_fall >=" + r1 +" and df.prov_fall="
				+ getProvincia() + " and df.cant_fall=" + getProvincia()+0+getCanton() 
				+ " and df.id_causa_fetal='" + getCau_fetal().replace(" ", "")+"'";
		Conexion c = new Conexion();
		ResultSet rs=null;
		System.out.println(sql);
		try {
			c.conectar();
			rs=c.ejecutarQuery(sql);
			resp=0;
			while (rs.next()) {
				resp=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		c.cerrarConexion();

	}

	
	public void consultaMadre() {
		
		String sql = "SELECT t.anio, t.trimestre , u.provincia||' '||u.canton , civ.estado_civil , sum(th.n_muertes) "
				+ "FROM th_analisis_madre th, d_tiempo t, d_ubicacion u, d_estado_civil civ "
				+ "WHERE (to_number(th.id_tiempo) = t.id_tiempo and t.id_tiempo in (select id_tiempo from d_tiempo where mes is null)) "
				+ "AND th.id_ubicacion = u.id_ubicacion "
				+ "AND th.id_estado_civil = civ.id_estado_civil "
				+ "and t.anio="+anio+" and t.trimestre="+trimestre+" and u.id_provincia="+provincia+" "
				+ "GROUP BY t.anio, t.trimestre, u.provincia,u.canton, civ.estado_civil ";
		
		ResultSet rs = null;
		registros = new ArrayList<RegistroAnalisis>();
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			while (rs.next()) {
				RegistroAnalisis ra = new RegistroAnalisis();
				ra.setAnio(rs.getInt(1));
				ra.setTrim(rs.getInt(2));
				ra.setUbic(rs.getString(3));
				ra.setEstado_civil(rs.getString(4));
				ra.setFallecidos(rs.getInt(5));
				registros.add(ra);
			}
			
			con.cerrarConexion();
			
			graficoBarras();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private PieChartModel modeloGrafico;
	
	
	public void graficoBarras() {
		modeloGrafico = new PieChartModel();
		String prov = "";
		for (RegistroAnalisis reg : registros) {
			modeloGrafico.set(reg.getEstado_civil(), reg.getFallecidos());
			prov = reg.getUbic();
		}
		
		modeloGrafico.setTitle("Anio "+anio+" - Trimestre "+trimestre+" Provincia "+prov);
		modeloGrafico.setShowDataLabels(true);
		
	}
	
	public PieChartModel getModeloGrafico() {
		return modeloGrafico;
	}
	
	public void setModeloGrafico(PieChartModel modeloGrafico) {
		this.modeloGrafico = modeloGrafico;
	}
	

	
	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}

	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
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

	public String getCau_fetal() {
		return cau_fetal;
	}

	public void setCau_fetal(String cau_fetal) {
		this.cau_fetal = cau_fetal;
	}

	public ArrayList<CausaFetal> getCausas_fetales() {
		return causas_fetales;
	}

	public void setCausas_fetales(ArrayList<CausaFetal> causas_fetales) {
		this.causas_fetales = causas_fetales;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getResp() {
		return resp;
	}
	public void setResp(int resp) {
		this.resp = resp;
	}
	
	public ArrayList<RegistroAnalisis> getRegistros() {
		return registros;
	}
	
	public void setRegistros(ArrayList<RegistroAnalisis> registros) {
		this.registros = registros;
	}
	
	
}
