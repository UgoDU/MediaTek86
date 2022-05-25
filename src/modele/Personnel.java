package modele;

/**
 * Un personnel, celui-ci est caractérisé par un idpersonnel, un idservice, un nom, un prenom, un tel et un mail.
 * @author ugo.
 */
public class Personnel {
	private int idpersonnel;
	private int idservice;
	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	
	/**
	 * Getter sur idpersonnel.
	 * @return idpersonnel.
	 */
	public int getIdpersonnel() {
		return this.idpersonnel;
	}
	
	/**
	 * Getter sur idservice.
	 * @return idservice.
	 */
	public int getIdservice() {
		return this.idservice;
	}
	
	/**
	 * Getter sur nom.
	 * @return nom.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Getter sur prenom.
	 * @return prenom.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * Getter sur tel.
	 * @return tel.
	 */
	public String getTel() {
		return this.tel;
	}
	
	/**
	 * Getter sur mail.
	 * @return mail.
	 */
	public String getMail() {
		return this.mail;
	}
	
	/**
	 * Setter sur idpersonnel.
	 * @param idpersonnel: la valeur idpersonnel à modifier.
	 */
	public void setIdpersonnel(int idpersonnel) {
		this.idpersonnel = idpersonnel;
	}
	
	/**
	 * Setter sur idservice.
	 * @param idservice: la valeur idservice à modifier.
	 */
	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}
	
	/**
	 * Setter sur nom.
	 * @param nom: la valeur nom à modifier.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Setter sur prenom.
	 * @param prenom: la valeur prenom à modifier.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Setter sur tel.
	 * @param tel: la valeur tel à modifier.
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/**
	 * Setter sur mail.
	 * @param mail: la valeur mail à modifier.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
}
