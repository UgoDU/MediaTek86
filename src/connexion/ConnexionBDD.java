package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Cr�� la classe permettant la connexion � la base de donn�es
 * @author ugo
 */
public class ConnexionBDD {

	/**
	 * L'instance de ConnexionBDD.
	 */
	private static ConnexionBDD instance = null;
	/**
	 * Permet la connexion � la BDD.
	 */
	private Connection cn = null;
	/**
	 * Le r�sultat des fonctions.
	 */
	private ResultSet rs = null;
	
	/**
	 * Permet de connecter l'application � la BDD. 
	 * @param url: l'url permettant d'acc�der � la BDD.
	 * @param login: le login de l'utilisateur de la BDD.
	 * @param pwd: le pwd de l'utilisateur de la BDD.
	 */
	private ConnexionBDD(String url, String login, String pwd) {
		if(cn == null) {
			// essaie de se connecter � la base de donn�es
			try {
				cn = DriverManager.getConnection(url, login, pwd);
				System.out.println("Connexion r�ussie!");
			}catch(SQLException e) {
				System.out.println("Erreur d'acc�s � la BDD.");
				System.exit(0);
			}
		}
	}
	
	/**
	 * Permet de r�cup�rer l'instance de la connexion � la BDD.
	 * @param url: l'url permettant d'acc�der � la BDD.
	 * @param login: le login de l'utilisateur de la BDD.
	 * @param pwd: le pwd de l'utilisateur de la BDD.
	 * @return instance: instance de la connexion � la BDD.
	 */
	public static ConnexionBDD getInstance(String url, String login, String pwd) {
		if (instance == null) {
			instance = new ConnexionBDD(url, login, pwd);
		}
		return instance;
	}
	
	/**
	 * Permet de mettre � jour une table de la BDD.
	 * @param req: la requ�te � ex�cuter.
	 * @param lesParams: les param�tres de la requ�te � ex�cuter.
	 */
	public void reqUpdate(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			// essaie d'update dans la base de donn�es
			try {
				PreparedStatement pst = cn.prepareStatement(req);
				if(lesParams != null) {
					int k=1;
					for (Object param : lesParams) {
						pst.setObject(k++, param);
					}
				}
				pst.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Permet de selectionner les informations d'une table de la BDD.
	 * @param req: la requ�te � ex�cuter.
	 * @param lesParams: les param�tres de la requ�te � ex�cuter.
	 */
	public void reqSelect(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			// essaie de s�lectionner dans la base de donn�es
			try {
				PreparedStatement pst = cn.prepareStatement(req);
				if(lesParams != null) {
					int k=1;
					for (Object param : lesParams) {
						pst.setObject(k++, param);
					}
				}
				rs = pst.executeQuery();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Permet de lire le r�sultat d'un select via un curseur.
	 * @return rs.next() si rs n'est pas null et que rs.next() est possible, false sinon.
	 */
	public boolean read() {
		if(rs != null) {
			// essaie de cr�er le curseur
			try {
				return rs.next();
			}catch(SQLException e) {}
		}
		return false;
	}
	
	/**
	 * Les champs � r�cup�rer via le curseur.
	 * @param nameField: le nom du champ d�sir�.
	 * @return null si rs est null ou que rs.getObject(nameField) ne fonctionne pas, rs.getObject(nameField) si rs n'est pas null et que rs.getObject(nameField) fonctionne.
	 */
	public Object field(String nameField) {
		if(rs == null) {
			return null;
		}
		try {
			return rs.getObject(nameField);
		}catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Permet de fermer le curseur.
	 */
	public void close() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				rs = null;
			}
		}
	}



}
