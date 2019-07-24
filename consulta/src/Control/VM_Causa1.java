package Control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Conexion.Conexion;
import Model.CAUSA_F1;

@Named("vm1")
@ViewScoped
public class VM_Causa1 implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CAUSA_F1>vm;
	private static int top=0;

	public VM_Causa1() {
		// TODO Auto-generated constructor stub
	}
	public void generar() {
		vm=new ArrayList<>();
		Conexion c = new Conexion();
		ResultSet rs = null;
		String sql="SELECT * FROM v_th_tiem_a_ubic_p_causa WHERE ROWNUM <="+getTop();
		try {
			c.conectar();
			rs=c.ejecutarQuery(sql);
			while (rs.next()) {
				vm.add(new CAUSA_F1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			
		} catch (Exception e) {
			
		}
		c.cerrarConexion();

	}


	public ArrayList<CAUSA_F1> getVm() {
		return vm;
	}


	public void setVm(ArrayList<CAUSA_F1> vm) {
		this.vm = vm;
	}


	public int getTop() {
		return top;
	}


	public void setTop(int top) {
		this.top = top;
	}
	
}
