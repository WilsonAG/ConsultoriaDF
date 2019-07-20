package Control;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControlSession implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo para obtener o crear una sesion.
	 * @param estado
	 * true: crea una nueva sesion si no existe-
	 * false: recupera una session.
	 * @return 
	 * null si no existe.
	 */
	public static HttpSession getSession(boolean estado) {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(estado);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * Recupera variable de session.
	 * @param attId
	 * identificador de la variable de sesion a recuperar
	 * @return
	 * null si no encuentra
	 */
	public static Object get(String attId) {
		HttpSession session = getSession(false);
		if (session != null)
			return session.getAttribute(attId);
		else
			return null;
		
	}

	/**
	 * Agregar variables de session
	 * @param attId
	 * identificador de la variable
	 * @param value
	 * objeto a guardar en la variable
	 * @return
	 */
	public static void add(String attId, Object value) {
		HttpSession session = getSession(false);
		session.setAttribute(attId, value);
	}
	
	/**
	 * Elimina la session y todo su contenido.
	 */
	public static void cerrarSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
	}

}
