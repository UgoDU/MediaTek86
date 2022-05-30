package dal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import connexion.ConnexionBDD;
import modele.Absence;
import modele.Motif;
import modele.Personnel;
import modele.Responsable;
import modele.Service;

/**
 * Classe permettant d'éxécuter les différentes requêtes SQL.
 * @author ugo
 */
public abstract class AccesDonnees {
	
	/**
	 * L'url permettant d'accèder à la BDD.
	 */
	private static String url = "jdbc:mysql://localhost/mediatek";
	
	/**
	 * Le login de l'utilisateur de la BDD.
	 */
	private static String login_bdd = "root";
	
	/**
	 * Le pwd de l'utilisateur de la BDD.
	 */
	private static String pwd_bdd = "";
	
	/**
	 * Requête permettant d'ajouter un responsable (debug).
	 * @param unResponsable: responsable à ajouter.
	 */
	public static void addResponsable(Responsable unResponsable) {
		String sql = "insert into Responsable (login, pwd) values (?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(unResponsable.getLogin());
		lesParams.add(unResponsable.getPwd());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant d'ajouter un personnel.
	 * @param unPersonnel: personnel à ajouter.
	 */
	public static void addPersonnel(Personnel unPersonnel) {
		String sql = "insert into Personnel (idpersonnel, idservice, nom, prenom, tel, mail) values (?, ?, ?, ?, ?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(unPersonnel.getIdpersonnel());
		lesParams.add(unPersonnel.getIdservice());
		lesParams.add(unPersonnel.getNom());
		lesParams.add(unPersonnel.getPrenom());
		lesParams.add(unPersonnel.getTel());
		lesParams.add(unPersonnel.getMail());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant d'ajouter une absence.
	 * @param uneAbsence: absence à ajouter.
	 */
	public static void addAbsence(Absence uneAbsence) {
		String sql = "insert into Absence (idpersonnel, datedebut, idmotif, datefin) values ( ?, ?, ?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(uneAbsence.getIdpersonnel());
		lesParams.add(uneAbsence.getDatedebut());
		lesParams.add(uneAbsence.getIdmotif());
		lesParams.add(uneAbsence.getDatefin());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant de supprimer un responsable grâce à son login (debug).
	 * @param login: login du responsable à supprimer.
	 */
	public static void removeResponsableParLogin(String login) {
		String sql = "delete from Responsable where login = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(login);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant de supprimer un personnel grâce à son idpersonnel, son nom, son prenom, son tel et son mail.
	 * @param idpersonnel: l'idpersonnel du personnel à supprimer.
	 * @param idservice: l'idservice du personnel à supprimer.
	 * @param nom: le nom du personnel à supprimer.
	 * @param prenom: le prenom du personnel à supprimer.
	 * @param tel: le tel du personnel à supprimer.
	 * @param mail: le mail du personnel à supprimer.
	 */
	public static void removePersonnel(String idpersonnel, String idservice, String nom, String prenom, String tel, String mail) {
		String sql = "delete from Personnel where idpersonnel = ? and idservice = ? and nom = ? and prenom = ? and tel = ? and mail = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(idpersonnel);
		lesParams.add(idservice);
		lesParams.add(nom);
		lesParams.add(prenom);
		lesParams.add(tel);
		lesParams.add(mail);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant de supprimer une absence grâce à son idpersonnel, sa datedebut, son idmotif et sa datefin.
	 * @param idpersonnel: l'idpersonnel de l'absence à supprimer.
	 * @param datedebut: la datedebut de l'absence à supprimer.
	 * @param idmotif: l'idmotif de l'absence à supprimer.
	 * @param datefin: la datefin de l'absence à supprimer.
	 */
	public static void removeAbsence(String idpersonnel, String datedebut, String idmotif, String datefin) {
		String sql = "delete from Absence where idpersonnel = ? and datedebut = ? and idmotif = ? and datefin = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(idpersonnel);
		lesParams.add(datedebut);
		lesParams.add(idmotif);
		lesParams.add(datefin);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant d'update un responsable via son login (debug).
	 * @param unResponsable: les nouvelles informations du responsable.
	 * @param login: le login du responsable que l'on désire modifier.
	 */
	public static void updateResponsableParLogin(Responsable unResponsable, String login) {
		String sql = "update Responsable set login = ?, pwd = ? where login = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(unResponsable.getLogin());
		lesParams.add(unResponsable.getPwd());
		lesParams.add(login);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	
	/**
	 * Requête permettant d'update un personnel via son idpersonnel, son idservice, son nom, son prenom, son tel et son mail.
	 * @param idpersonnel: l'idpersonnel du personnel que l'on désire modifier.
	 * @param idservice: l'idservice du personnel que l'on désire modifier.
	 * @param nom: le nom du personnel que l'on désire modifier.
	 * @param prenom: le prenom du personnel que l'on désire modifier.
	 * @param tel: le tel du personnel que l'on désire modifier.
	 * @param mail: le mail du personnel que l'on désire modifier.
	 * @param nouveau_idservice: le nouveau idservice du personnel.
	 * @param nouveau_nom: le nouveau nom du personnel.
	 * @param nouveau_prenom: le nouveau prenom du personnel.
	 * @param nouveau_tel: le nouveau tel du personnel.
	 * @param nouveau_mail: le nouveau mail du personnel.
	 */
	public static void updatePersonnel(String idpersonnel, String idservice, String nom, String prenom, String tel, String mail, String nouveau_idservice, String nouveau_nom, String nouveau_prenom, String nouveau_tel, String nouveau_mail) {
		String sql = "update Personnel set idservice = ?, nom = ?, prenom = ?, tel = ?, mail = ? where idpersonnel = ? and idservice = ? and nom = ? and prenom = ? and tel = ? and mail = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(nouveau_idservice);
		lesParams.add(nouveau_nom);
		lesParams.add(nouveau_prenom);
		lesParams.add(nouveau_tel);
		lesParams.add(nouveau_mail);
		lesParams.add(idpersonnel);
		lesParams.add(idservice);
		lesParams.add(nom);
		lesParams.add(prenom);
		lesParams.add(tel);
		lesParams.add(mail);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant d'update une absence via son idpersonnel, sa datedebut, son idmotif et sa datefin.
	 * @param idpersonnel: l'idpersonnel de l'absence que l'on désire modifier.
	 * @param datedebut: la datedebut de l'absence que l'on désire modifier.
	 * @param idmotif: l'idmotif de l'absence que l'on désire modifier.
	 * @param datefin: la datefin de l'absence que l'on désire modifier.
	 * @param nouveau_idmotif: le nouveau idmotif de l'absence.
	 * @param nouveau_datedebut: la nouvelle datedebut de l'absence.
	 * @param nouveau_datefin: la nouvelle datefin de l'absence.
	 */
	public static void updateAbsence(String idpersonnel, String datedebut, String idmotif, String datefin, String nouveau_idmotif, String nouveau_datedebut, String nouveau_datefin) {
		String sql = "update Absence set idmotif = ?, datedebut = ?, datefin = ? where idpersonnel = ? and datedebut = ? and idmotif = ? and datefin = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(nouveau_idmotif);
		lesParams.add(nouveau_datedebut);
		lesParams.add(nouveau_datefin);
		lesParams.add(idpersonnel);
		lesParams.add(datedebut);
		lesParams.add(idmotif);
		lesParams.add(datefin);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	/**
	 * Requête permettant de sélectionner tous les responsables (debug).
	 * @return lesResponsable: liste des responsables.
	 */
	public static ArrayList<Responsable> recupResponsables() {
		String sql = "select * from Responsable";
		ArrayList<Responsable> lesResponsables = new ArrayList<Responsable>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Responsable unResponsable = new Responsable();
			unResponsable.setLogin((String)cn.field("login"));
			unResponsable.setPwd((String)cn.field("pwd"));
			lesResponsables.add(unResponsable);
		}
		cn.close();
		return lesResponsables;
	}
	
	/**
	 * Requête permettant de sélectionner tous les personnels par ordre croissant d'idpersonnel.
	 * @return lesPersonnels: liste des personnels par ordre croissant d'idpersonnel.
	 */
	public static ArrayList<Personnel> recupPersonnelsRangesASC() {
		String sql = "select * from Personnel order by idpersonnel";
		ArrayList<Personnel> lesPersonnels = new ArrayList<Personnel>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Personnel unPersonnel = new Personnel();
			unPersonnel.setIdpersonnel((int)cn.field("idpersonnel"));
			unPersonnel.setIdservice((int)cn.field("idservice"));
			unPersonnel.setNom((String)cn.field("nom"));
			unPersonnel.setPrenom((String)cn.field("prenom"));
			unPersonnel.setTel((String)cn.field("tel"));
			unPersonnel.setMail((String)cn.field("mail"));
			lesPersonnels.add(unPersonnel);
		}
		cn.close();
		return lesPersonnels;
	}
	
	/**
	 * Requête permettant de sélectionner toutes les absences par ordre décroissant de datefin.
	 * @param idpersonnel: idpersonnel du personnel.
	 * @return lesAbsences: liste des absences par ordre décroissant de datefin.
	 */
	public static ArrayList<Absence> recupAbsencesRangesRecentParDatefin(int idpersonnel) {
		String sql = "select * from Absence where idpersonnel = ? order by dateFin DESC";
		ArrayList<Absence> lesAbsences = new ArrayList<Absence>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(idpersonnel);
		cn.reqSelect(sql, lesParams);
		while (cn.read()) {
			Absence uneAbsence = new Absence();
			uneAbsence.setIdpersonnel((int)cn.field("idpersonnel"));
			uneAbsence.setDatedebut((Date)cn.field("datedebut"));
			uneAbsence.setIdmotif((int)cn.field("idmotif"));
			uneAbsence.setDatefin((Date)cn.field("datefin"));
			lesAbsences.add(uneAbsence);
		}
		cn.close();
		return lesAbsences;
	}
	
	
	/**
	 * Requête permettant de sélectionner tous les services.
	 * @return lesServices: liste des services.
	 */
	public static ArrayList<Service> recupServices() {
		String sql = "select * from Service";
		ArrayList<Service> lesServices = new ArrayList<Service>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Service unService = new Service();
			unService.setIdservice((int)cn.field("idservice"));
			unService.setNom((String)cn.field("nom"));
			lesServices.add(unService);
		}
		cn.close();
		return lesServices;
	}
	
	/**
	 * Requête permettant de sélectionner tous les motifs.
	 * @return lesMotifs: liste des motifs.
	 */
	public static ArrayList<Motif> recupMotifs() {
		String sql = "select * from Motif";
		ArrayList<Motif> lesMotifs = new ArrayList<Motif>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Motif unMotif = new Motif();
			unMotif.setIdmotif((int)cn.field("idmotif"));
			unMotif.setLibelle((String)cn.field("libelle"));
			lesMotifs.add(unMotif);
		}
		cn.close();
		return lesMotifs;
	}
	
	/**
	 * Requête permettant de sélectionner tous les responsables via leur login et pwd (permet la connexion).
	 * @param login: login du responsable.
	 * @param pwd: pwd du responsable.
	 * @return lesResponsables: liste des responsables.
	 */
	public static ArrayList<Responsable> recupResponsablesLoginPwd(String login, String pwd) {
		String sql = "select * from Responsable where login = ? and pwd = ?";
		ArrayList<Responsable> lesResponsables = new ArrayList<Responsable>();
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(login);
		lesParams.add(pwd);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, lesParams);
		while (cn.read()) {
			Responsable unResponsable = new Responsable();
			unResponsable.setLogin((String)cn.field("login"));
			unResponsable.setPwd((String)cn.field("pwd"));
			lesResponsables.add(unResponsable);
		}
		cn.close();
		return lesResponsables;
	}
}
