package Control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import Model.Usuario;

@Named("login")
@SessionScoped
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
				url = "/admin/menu_administrador.xhtml?faces-redirect=true";		
				ControlSession.getSession(true);
				ControlSession.add("nameUser", userBD.getNombre());
				String n = (String) ControlSession.get("nameUser");
				String msj = "Bienvenido "+n;
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
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
	
	public String cerrarSesion() {
		HttpSession sesion = ControlSession.getSession(false);
		if (sesion != null) {
			sesion.invalidate();
		}
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	

}
