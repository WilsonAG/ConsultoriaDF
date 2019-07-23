package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import Conexion.Conexion;
import Model.Provincia;

@Named("consulta")
@ViewScoped
public class controlConsultas implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Provincia> provincias;
	private PieChartModel pastel;

	@PostConstruct
	public void init() {
		cargarMuertesXProvinca();
		graficarPastel();
	}
	
	public void graficarPastel() {
		pastel = new PieChartModel();
		ChartData data = new ChartData();
		
		PieChartDataSet dataset = new PieChartDataSet();
		ArrayList<Number> valores = new ArrayList<Number>();
		ArrayList<String> colores = new ArrayList<String>();
		for (Provincia p : provincias) {
			valores.add(p.getMuertes());
			colores.add(generarColor());
		}
		dataset.setData(valores);
		dataset.setBackgroundColor(colores);
		
		data.addChartDataSet(dataset);
		ArrayList<String> labels = new ArrayList<String>();
		for (Provincia p : provincias) {
			labels.add(p.getNombre());
		}
		
		data.setLabels(labels);
		
		
		pastel.setData(data);
		
		
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
	
	private String generarColor() {
		Random rnd = new Random();
		int n1 = rnd.nextInt(255);
		int n2 = rnd.nextInt(255);
		int n3 = rnd.nextInt(255);
		return "rgb("+n1+", "+n2+", "+n3+")";
	}
	
	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}
	
	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	public PieChartModel getPastel() {
		return pastel;
	}
	
	public void setPastel(PieChartModel pastel) {
		this.pastel = pastel;
	}
	
	@Override
	public String toString() {
		return "controlConsultas [provincias=" + provincias + "]";
	}

}
