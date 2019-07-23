package Model;

public class User {
	
	private int id;
	private String user;
	private String mail;
	private String pass;
	private int estado;
	
	public User() {
		
	}
	
	public User(int id, String user, String mail, String pass, int estado) {
		super();
		this.id = id;
		this.user = user;
		this.mail = mail;
		this.pass = pass;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", mail=" + mail + ", pass=" + pass + ", estado=" + estado + "]";
	}
	
	
	

}
