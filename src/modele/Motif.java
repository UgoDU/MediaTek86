package modele;

/**
 * Un motif, celui-ci est caractérisé par un idmotif et un libelle.
 * @author ugo.
 */
public class Motif {

	/**
	 * id permettant d'identifier le motif.
	 */
	private int idmotif;
	/**
	 * libelle du motif.
	 */
	private String libelle;
	
	/**
	 * Getter sur idmotif.
	 * @return idmotif.
	 */
	public int getIdmotif() {
		return this.idmotif;
	}
	
	/**
	 * Getter sur libelle.
	 * @return libelle.
	 */
	public String getLibelle() {
		return this.libelle;
	}
	
	/**
	 * Setter sur idmotif.
	 * @param idmotif: la valeur idmotif à modifier.
	 */
	public void setIdmotif(int idmotif) {
		this.idmotif = idmotif;
	}
	
	/**
	 * Setter sur libelle.
	 * @param libelle: la valeur idmotif à modifier.
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
