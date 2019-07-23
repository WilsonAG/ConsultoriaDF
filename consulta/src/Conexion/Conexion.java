package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	
	private final String SERVER= "172.17.42.63";
	private final String PORT= "1521";
	private final String SID= "orclupsoltp";
	private final String USER = "p54g2_oltp_df";
	private final String PASSWORD = "g02_4263";
	
	private Connection conexion;
	private Statement statement;
	private ResultSet resulset;
	
	private boolean conectada;
	
	public Conexion() {
		conexion = null;
		statement = null;
		resulset = null;
		conectada = false;
		this.conectar();
	}
	
	public void conectar() {
		String url = "jdbc:oracle:thin:@"+this.SERVER+":"+this.PORT+":"+this.SID+"";
		try {
			conexion = DriverManager.getConnection(url, USER, PASSWORD);
			conectada = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet ejecutarQuery(String sql) {
		try {
			this.statement = conexion.createStatement();
			resulset = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resulset;
	}
	
	public int ejecutarUpdate(String sql) {
		int r = 0;
		try {
			this.statement = conexion.createStatement();
			r = statement.executeUpdate(sql);
			this.resulset = statement.getResultSet();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return r;
	}
	
	public void cerrarConexion() {
		try {
			if (this.resulset != null) {
				this.resulset.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (this.conexion != null) {
				this.conexion.close();
			}
			
			conectada = false;
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResulset() {
		return resulset;
	}

	public void setResulset(ResultSet resulset) {
		this.resulset = resulset;
	}

	public boolean isConectada() {
		return conectada;
	}

	public void setConectada(boolean conectada) {
		this.conectada = conectada;
	}
	

}
