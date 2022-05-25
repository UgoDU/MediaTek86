package modele;

/**
 * Un service, celui-ci est caractérisé par un idservice et un nom.
 * @author ugo.
 */
public class Service {
	/**
	 * id permettant d'identifier le service.
	 */
	private int idservice;
	/**
	 * nom du service.
	 */
	private String nom;
	
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
}
