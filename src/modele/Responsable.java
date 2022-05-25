package modele;

/**
 * Un responsable, celui-ci est caractérisé par un login et un pwd.
 * @author ugo.
 */
public class Responsable {
	/**
	 * login permettant au responsable de se connecter.
	 */
	private String login;
	/**
	 * mot de passe permettant au responsable de se connecter.
	 */
	private String pwd;
	
	/**
	 * Getter sur login.
	 * @return login.
	 */
	public String getLogin() {
		return this.login;
	}
	
	/**
	 * Getter sur pwd.
	 * @return pwd.
	 */
	public String getPwd() {
		return this.pwd;
	}
	
	/**
	 * Setter sur login.
	 * @param login: la valeur login à modifier.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Setter sur pwd.
	 * @param pwd: la valeur pwd à modifier.
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
