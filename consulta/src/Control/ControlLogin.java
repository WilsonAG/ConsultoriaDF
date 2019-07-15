package Control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Usuario;

@Named("login")
@ViewScoped
public class ControlLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario user;
	
	@PostConstruct
	public void init() {
		user = new Usuario();
	}
	
	public String iniciarSesion() {
		String url = null;
		try {
			
			Usuario userBD = user.buscarUsuario(user);			
			if (userBD != null) {
				url = "consulta_madre";				
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
				
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
		}
		
		return url;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	

}
