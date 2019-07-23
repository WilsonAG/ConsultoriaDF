package Control;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Conexion.Conexion;
import Model.User;

@Named("registro")
@ViewScoped
public class controlRegistro implements Serializable {

	private static final long serialVersionUID = 1L;
	private User usuario;

	@PostConstruct
	public void init() {
		usuario = new User();
	}

	public void crearUser() {
		String sql = "INSERT INTO usuarios (usuario, e_mail, password, estado) VALUES (?, ?, ?, ?)";

		try {
			Conexion con = new Conexion();
			PreparedStatement ps = con.getConexion().prepareStatement(sql);
			ps.setString(1, usuario.getUser());
			ps.setString(2, usuario.getMail());
			ps.setString(3, usuario.getPass());
			ps.setInt(4, usuario.getEstado());
			ps.execute();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado", ""));
			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Algo salio mal"));
		}

	}

	public void msg() {

		FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "hola", "detail"));
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

}
