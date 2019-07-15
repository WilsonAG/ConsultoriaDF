package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;

public class Usuario {
	
	private String nombre;
	private String email;
	private String password;
	private int estado;
	
	public Usuario() {
		
	}

	public Usuario(String email, String password, int estado) {
		super();
		this.email = email;
		this.password = password;
		this.estado = estado;
	}

	public Usuario buscarUsuario(Usuario user) {
		Usuario u = null;
		try {
			Conexion con = new Conexion();
			String sql = "select u.usuario, u.e_mail, u.password, u.estado from usuarios u "
					+ "where u.e_mail = ? and u.password = ?";
			PreparedStatement ps = con.getConexion().prepareStatement(sql);
			ps.setString(1, user.email);
			ps.setString(2, user.password);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = new Usuario();
				u.setNombre(rs.getString(1));
				u.setEmail(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setEstado(rs.getInt(4));				
			}
			
			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", password=" + password + ", estado=" + estado + "]";
	}
	
	
}
