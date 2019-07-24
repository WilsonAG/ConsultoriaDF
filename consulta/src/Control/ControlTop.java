package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Conexion.Conexion;
import Model.RegistroAnalisis;

@Named("top")
@ViewScoped
public class ControlTop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int valor;
	private ArrayList<RegistroAnalisis> regs;

	public void consultar() {
		System.out.println(valor);
		regs = new ArrayList<RegistroAnalisis>();
		String sql = "SELECT * FROM ( SELECT * FROM v_th_m_anio_prov_estciv ORDER BY \"Fallecidos\" DESC )"
				+ " WHERE rownum <= "+valor;
		ResultSet rs = null;
		
		try {
			Conexion con = new Conexion();
			rs = con.ejecutarQuery(sql);
			
			while (rs.next()) {
				RegistroAnalisis rg = new  RegistroAnalisis();
				rg.setAnio(rs.getInt(1));
				rg.setUbic(rs.getString(2));
				rg.setEstado_civil(rs.getString(3));;
				rg.setFallecidos(rs.getInt(4));
				this.regs.add(rg);
				
			}
			
			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public ArrayList<RegistroAnalisis> getRegs() {
		return regs;
	}

	public void setRegs(ArrayList<RegistroAnalisis> regs) {
		this.regs = regs;
	}

}
