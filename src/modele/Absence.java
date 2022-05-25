package modele;

import java.time.LocalDate;
import java.util.Date;

/**
 * Une absence, celle-ci est caractérisée par un idpersonnel, une datedebut, un idmotif et une datefin.
 * @author ugo.
 */
public class Absence {
	
	/**
	 * id permettant d'identifier le personnel.
	 */
	int idpersonnel;
	/**
	 * date de début de l'absence.
	 */
	Date datedebut;
	/**
	 * id permettant d'identifier le motif.
	 */
	int idmotif;
	/**
	 * date de fin de l'absence.
	 */
	Date datefin;
	
	/**
	 * Getter sur idpersonnel.
	 * @return idpersonnel.
	 */
	public int getIdpersonnel() {
		return this.idpersonnel;
	}
	
	/**
	 * Getter sur datedebut.
	 * @return datedebut.
	 */
	public Date getDatedebut() {
		return this.datedebut;
	}
	
	/**
	 * Getter sur idmotif.
	 * @return idmotif.
	 */
	public int getIdmotif() {
		return this.idmotif;
	}
	
	/**
	 * Getter sur datefin.
	 * @return datefin.
	 */
	public Date getDatefin() {
		return this.datefin;
	}
	
	/**
	 * Setter sur idpersonnel.
	 * @param idpersonnel: la valeur idpersonnel à modifier.
	 */
	public void setIdpersonnel(int idpersonnel) {
		this.idpersonnel = idpersonnel;
	}
	
	/**
	 * Setter sur datedebut.
	 * @param datedebut: la valeur datedebut à modifier.
	 */
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	
	/**
	 * Setter sur idmotif.
	 * @param idmotif: la valeur idmotif à modifier.
	 */
	public void setIdmotif(int idmotif) {
		this.idmotif = idmotif;
	}
	
	/**
	 * Setter sur datefin.
	 * @param datefin: la valeur datefin à modifier.
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	

}
