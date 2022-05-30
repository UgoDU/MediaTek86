package controleur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dal.AccesDonnees;
import modele.Absence;
import modele.Motif;
import modele.Personnel;
import modele.Responsable;
import modele.Service;
import vue.FrmAbsences;
import vue.FrmAjoutAbsence;
import vue.FrmAjoutPersonnel;
import vue.FrmConnexion;
import vue.FrmMain;
import vue.FrmModificationAbsence;
import vue.FrmModificationPersonnel;

/**
 * Contrôleur de l'application, la plupart des fonctions passent par la classe Controle afin qu'elles soient redirigées dans les classes correspondantes.
 * @author ugo.
 */
public class Controle {
	
	/**
	 * Permet d'afficher la liste des responsables (debug).
	 */
	public static void lireResponsables() {
		ArrayList<Responsable> lesResponsables = AccesDonnees.recupResponsables();
		for(Responsable unResponsable : lesResponsables) {
			// affiche les responsables
			System.out.println(unResponsable.getLogin()+" "+unResponsable.getPwd()+" ");
		}
	}
	
	/**
	 * Permet d'afficher les noms des services.
	 * @return lesNomsServices: contient la liste des noms des services.
	 */
	public static ArrayList<String> lireNomServices() {
		ArrayList<Service> lesServices = AccesDonnees.recupServices();
		ArrayList<String> lesNomsServices = new ArrayList<String>();
		for(Service unService : lesServices) {
			// affiche les noms des services
			lesNomsServices.add(unService.getNom());
		}
		return lesNomsServices;
	}
	
	/**
	 * Permet d'afficher les noms des motifs.
	 * @return lesNomsMotifs: contient la liste des noms des motifs.
	 */
	public static ArrayList<String> lireNomMotifs() {
		ArrayList<Motif> lesMotifs = AccesDonnees.recupMotifs();
		ArrayList<String> lesNomsMotifs = new ArrayList<String>();
		for(Motif unMotif : lesMotifs) {
			// affiche les noms des motifs
			lesNomsMotifs.add(unMotif.getLibelle());
		}
		return lesNomsMotifs;
	}
	
	/**
	 * Permet de remplir la table de la fenêtre principale avec la liste des personnels et les noms des services dans l'ordre croissant des idPersonnel.
	 * @return listePersonnels: contient la liste des personnels et les noms des services dans l'ordre croissant des idPersonnel, à afficher dans la table de la fenêtre principale.
	 */
	public static ArrayList<ArrayList <String>> lirePersonnelsRangesASC() {
		ArrayList<Personnel> lesPersonnels = AccesDonnees.recupPersonnelsRangesASC();
		ArrayList<ArrayList<String>> listePersonnels = new ArrayList<ArrayList<String>>();
		for (Personnel unPersonnel : lesPersonnels) {
			ArrayList<String> ligne = new ArrayList<String>();
			
			// ajouter dans l'array toutes les informations en string
			ligne.add(Integer.toString(unPersonnel.getIdpersonnel()));
			ligne.add(Integer.toString(unPersonnel.getIdservice()));
			ligne.add(unPersonnel.getNom());
			ligne.add(unPersonnel.getPrenom());
			ligne.add(unPersonnel.getTel());
			ligne.add(unPersonnel.getMail());
			// affiche les noms des services en fonction de l'id
			if (unPersonnel.getIdservice() == 1) {
				ligne.add("administratif");
			} else if (unPersonnel.getIdservice() == 2) {
				ligne.add("médiation culturelle");
			} else if (unPersonnel.getIdservice() == 3) {
				ligne.add("prêt");
			} else {
				ligne.add("(non défini)");
			}
					
			listePersonnels.add(ligne);
		}
		
		return listePersonnels;
	}
	
	/**
	 * Permet de remplir la table de la fenêtre principale avec la liste des absences et les noms des motifs dans l'ordre décroissant via un idpersonnel donné.
	 * @param idpersonnel: l'idpersonnel de la personne dont on souhaite connaître la liste d'absences.
	 * @return listeAbsences: contient la liste des absences du personnel souhaité et les noms des motifs dans l'ordre décroissant des datefin, à afficher dans la table de la fenêtre des absences.  
	 */
	public static ArrayList<ArrayList <String>> lireAbsencesRangesDESC(int idpersonnel) {
		ArrayList<Absence> lesAbsences = AccesDonnees.recupAbsencesRangesRecentParDatefin(idpersonnel);
		ArrayList<ArrayList<String>> listeAbsences = new ArrayList<ArrayList<String>>();
		for (Absence uneAbsence : lesAbsences) {
			ArrayList<String> ligne = new ArrayList<String>();
			
			// ajouter dans l'array toutes les informations en string
			ligne.add(Integer.toString(uneAbsence.getIdpersonnel()));
			ligne.add(uneAbsence.getDatedebut().toString());
			ligne.add(Integer.toString(uneAbsence.getIdmotif()));
			ligne.add(uneAbsence.getDatefin().toString());
			
			// affiche les noms des services en fonction de l'id
			if (uneAbsence.getIdmotif() == 1) {
				ligne.add("vacances");
			} else if (uneAbsence.getIdmotif() == 2) {
				ligne.add("maladie");
			} else if (uneAbsence.getIdmotif() == 3) {
				ligne.add("motif familial");
			} else if (uneAbsence.getIdmotif() == 4) {
				ligne.add("congé parental");
			} else {
				ligne.add("(non défini)");
			}
					
			listeAbsences.add(ligne);
		}
		
		return listeAbsences;
	}
	
	/**
	 * Permet de vérifier que le login et le mot de passe saisis sont présents dans la table responsable, et si c'est le cas, d'ouvrir la fenêtre principale.
	 * @param login: le login du responsable.
	 * @param pwd: le mot de passe du responsable.
	 * @return true si le login et le mot de passe correspondent, false sinon.
	 */
	public static Boolean seConnecter(String login, String pwd) {
		ArrayList<Responsable> lesResponsables = AccesDonnees.recupResponsablesLoginPwd(login, pwd);
		if (lesResponsables.isEmpty()) {
			// erreur de login / mot de passe
			JOptionPane.showMessageDialog(null, "Erreur: mauvais login ou mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			// connexion réussie
			changerFenetreMain();
			return true;
		}
	}
	
	/**
	 * Permet d'insérer un compte avec un login "test" et un pwd "motdepasse" dans la table responsable (debug).
	 */
	public static void sauverEnBaseResponsables() {
		Responsable unResponsable = new Responsable();
		unResponsable.setLogin("test");
		unResponsable.setPwd("motdepasse");
		// sauvegarde du responsable
		AccesDonnees.addResponsable(unResponsable);
	}
	
	/**
	 * Permet d'insérer un nouveau personnel dans la table personnel.
	 * @param idpersonnel: idpersonnel du personnel.
	 * @param idservice: idservice du personnel.
	 * @param nom: nom du personnel.
	 * @param prenom: prenom du personnel.
	 * @param tel: tel du personnel.
	 * @param mail: mail du personnel.
	 */
	public static void sauverEnBasePersonnel(int idpersonnel, int idservice, String nom, String prenom, String tel, String mail) {
		Personnel unPersonnel = new Personnel();
		unPersonnel.setIdpersonnel(idpersonnel);
		unPersonnel.setIdservice(idservice);
		unPersonnel.setNom(nom);
		unPersonnel.setPrenom(prenom);
		unPersonnel.setTel(tel);
		unPersonnel.setMail(mail);
		// sauvegarde du personnel
		AccesDonnees.addPersonnel(unPersonnel);
	}
	
	/**
	 * Permet d'insérer une nouvelle absence dans la table absence.
	 * @param idpersonnel: idpersonnel de l'absence.
	 * @param datedebut: datedebut de l'absence.
	 * @param idmotif: idmotif de l'absence.
	 * @param datefin: datefin de l'absence.
	 */
	public static void sauverEnBaseAbsence(int idpersonnel, Date datedebut, int idmotif, Date datefin) {
		Absence uneAbsence = new Absence();
		uneAbsence.setIdpersonnel(idpersonnel);
		uneAbsence.setDatedebut(datedebut);
		uneAbsence.setIdmotif(idmotif);
		uneAbsence.setDatefin(datefin);
		// sauvegarde d'une absence
		AccesDonnees.addAbsence(uneAbsence);
	}
	
	/**
	 * Permet de supprimer le responsable ayant le login "test" de la table responsable (debug).
	 */
	public static void supprimerDeLaBaseResponsables() {
		// supprimer un responsable avec le login test
		AccesDonnees.removeResponsableParLogin("test");
	}
	
	/**
	 * Permet de supprimer un personnel de la table personnel.
	 * @param idpersonnel: idpersonnel du personnel.
	 * @param idservice: idservice du personnel.
	 * @param nom: nom du personnel.
	 * @param prenom: prenom du personnel.
	 * @param tel: tel du personnel.
	 * @param mail: mail du personnel.
	 */
	public static void supprimerDeLaBasePersonnels(String idpersonnel, String idservice, String nom, String prenom, String tel, String mail) {
		// supprimer un personnel
		AccesDonnees.removePersonnel(idpersonnel, idservice, nom, prenom, tel, mail);
	}
	
	/**
	 * Permet de supprimer une absence de la table absence.
	 * @param idpersonnel: idpersonnel de l'absence.
	 * @param datedebut: datedebut de l'absence.
	 * @param idmotif: idmotif de l'absence.
	 * @param datefin: datefin de l'absence.
	 */
	public static void supprimerDeLaBaseAbsences(String idpersonnel, String datedebut, String idmotif, String datefin){
		// supprimer une absence
		AccesDonnees.removeAbsence(idpersonnel, datedebut, idmotif, datefin);
	}
	
	/**
	 * Permet de modifier un responsable ayant pour login "test" en un responsable ayant pour login "modifie" et comme pwd "456" (debug).
	 */
	public static void modifierEnBaseResponsables() {
		Responsable unResponsable = new Responsable();
		unResponsable.setLogin("modifie");
		unResponsable.setPwd("456");
		// modifier un responsable avec le login test
		AccesDonnees.updateResponsableParLogin(unResponsable, "test");
	}
	
	/**
	 * Permet de modifier un personnel dans la table personnel.
	 * @param idpersonnel: ancien idpersonnel du personnel.
	 * @param idservice: ancien idservice du personnel.
	 * @param nom: ancien nom du personnel.
	 * @param prenom: ancien prenom du personnel.
	 * @param tel: ancien tel du personnel.
	 * @param mail: ancien mail du personnel.
	 * @param nouveau_idservice: nouveau idservice du personnel.
	 * @param nouveau_nom: nouveau nom du personnel.
	 * @param nouveau_prenom: nouveau prenom du personnel.
	 * @param nouveau_tel: nouveau tel du personnel.
	 * @param nouveau_mail: nouveau mail du personnel.
	 */
	public static void modifierEnBasePersonnel(String idpersonnel, String idservice, String nom, String prenom, String tel, String mail, String nouveau_idservice, String nouveau_nom, String nouveau_prenom, String nouveau_tel, String nouveau_mail) {
		// modifier un personnel
		AccesDonnees.updatePersonnel(idpersonnel, idservice, nom, prenom, tel, mail, nouveau_idservice, nouveau_nom, nouveau_prenom, nouveau_tel, nouveau_mail);
	}
	
	/**
	 * Permet de modifier une absence dans la table absence.
	 * @param idpersonnel: ancien idpersonnel de l'absence.
	 * @param datedebut: ancien datedebut de l'absence.
	 * @param idmotif: ancien idmotif de l'absence.
	 * @param datefin: ancien datefin de l'absence.
	 * @param nouveau_idmotif: nouveau idmotif de l'absence.
	 * @param nouveau_datedebut: nouveau datedebut de l'absence.
	 * @param nouveau_datefin: nouveau datefin de l'absence.
	 */
	public static void modifierEnBaseAbsence(String idpersonnel, String datedebut, String idmotif, String datefin, String nouveau_idmotif, String nouveau_datedebut, String nouveau_datefin) {
		// modifier une absence
		AccesDonnees.updateAbsence(idpersonnel, datedebut, idmotif, datefin, nouveau_idmotif, nouveau_datedebut, nouveau_datefin);
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre de modification du personnel et de remplir les informations avec celles du personnel que l'on souhaite modifier.
	 * @param remplissage: contient toutes les informations du personnel à remplir dans la fenêtre de modification du personnel.
	 */
	public static void changerFenetreModificationPersonnel(String[] remplissage){
		// création de la fenêtre
		FrmModificationPersonnel fenetre = new FrmModificationPersonnel();
		fenetre.setVisible(true);
		if (remplissage.length > 0) {
			// remplissage des champs
			fenetre.setTextFieldNomPersonnel(remplissage[2]);
			fenetre.setTextFieldPrenomPersonnel(remplissage[3]);
			fenetre.setTextFieldTelephonePersonnel(remplissage[4]);
			fenetre.setTextFieldMailPersonnel(remplissage[5]);
			fenetre.setComboBoxServicePersonnelSelectedIndex(Integer.parseInt(remplissage[1]));
			fenetre.setSIdpersonnel(remplissage[0]);
			fenetre.setSIdservice(remplissage[1]);
			fenetre.setSNom(remplissage[2]);
			fenetre.setSPrenom(remplissage[3]);
			fenetre.setSTel(remplissage[4]);
			fenetre.setSMail(remplissage[5]);
		}
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre de modification de l'absence et de remplir les informations avec celles de l'absence que l'on souhaite modifier.
	 * @param remplissage: contient toutes les informations de l'absence à remplir dans la fenêtre de modification de l'absence.
	 */
	public static void changerFenetreModificationAbsence(String[] remplissage){
		FrmModificationAbsence fenetre = new FrmModificationAbsence();
		fenetre.setIdpersonnel(Integer.parseInt(remplissage[0]));
		// création de la fenêtre
		fenetre.setVisible(true);
		if (remplissage.length > 0) {
			// remplissage des champs
			fenetre.setTextFieldDatedebut(remplissage[1]);
			fenetre.setTextFieldDatefin(remplissage[3]);
			fenetre.setComboBoxMotifAbsenceSelectedIndex(Integer.parseInt(remplissage[2]));
			fenetre.setSIdpersonnel(remplissage[0]);
			fenetre.setSDatedebut(remplissage[1]);
			fenetre.setSIdmotif(remplissage[2]);
			fenetre.setSDatefin(remplissage[3]);
		}
	}
	
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre des absences.
	 * @param idpersonnel: l'idpersonnel de la personne dont on souhaite connaître les absences.
	 */
	public static void changerFenetreAbsences(int idpersonnel){
		// création de la fenêtre
		FrmAbsences fenetre = new FrmAbsences(idpersonnel);
		fenetre.setVisible(true);
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre d'ajout des absences.
	 * @param idpersonnel: l'idpersonnel de la personne dont on souhaite ajouter une absence.
	 */
	public static void changerFenetreAjoutAbsence(int idpersonnel){
		// création de la fenêtre
		FrmAjoutAbsence fenetre = new FrmAjoutAbsence();
		fenetre.setIdpersonnel(idpersonnel);
		fenetre.setVisible(true);
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre d'ajout des personnels.
	 */
	public static void changerFenetreAjoutPersonnel(){
		// création de la fenêtre
		FrmAjoutPersonnel fenetre = new FrmAjoutPersonnel();
		fenetre.setVisible(true);
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre principale.
	 */
	public static void changerFenetreMain(){
		// création de la fenêtre
		FrmMain fenetre = new FrmMain();
		fenetre.setVisible(true);
	}
	
	/**
	 * Permet de changer la fenêtre actuelle en la fenêtre de connexion.
	 */
	public static void changerFenetreConnexion(){
		// création de la fenêtre
		FrmConnexion fenetre = new FrmConnexion();
		fenetre.setVisible(true);
	}
	
	/**
	 * Permet de démarrer l'application sur la fenêtre de connexion.
	 * @param args: les arguments.
	 */
	public static void main(String[] args) {
		changerFenetreConnexion();
	}
}
