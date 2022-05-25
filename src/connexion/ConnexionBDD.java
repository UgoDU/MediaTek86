package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Créé la classe permettant la connexion à la base de données
 * @author ugo
 */
public class ConnexionBDD {

	/**
	 * L'instance de ConnexionBDD.
	 */
	private static ConnexionBDD instance = null;
	/**
	 * Permet la connexion à la BDD.
	 */
	private Connection cn = null;
	/**
	 * Le résultat des fonctions.
	 */
	private ResultSet rs = null;
	
	/**
	 * Permet de connecter l'application à la BDD. 
	 * @param url: l'url permettant d'accèder à la BDD.
	 * @param login: le login de l'utilisateur de la BDD.
	 * @param pwd: le pwd de l'utilisateur de la BDD.
	 */
	private ConnexionBDD(String url, String login, String pwd) {
		if(cn == null) {
			// essaie de se connecter à la base de données
			try {
				cn = DriverManager.getConnection(url, login, pwd);
				System.out.println("Connexion réussie!");
			}catch(SQLException e) {
				System.out.println("Erreur d'accès à la BDD.");
				System.exit(0);
			}
		}
	}
	
	/**
	 * Permet de récupèrer l'instance de la connexion à la BDD.
	 * @param url: l'url permettant d'accèder à la BDD.
	 * @param login: le login de l'utilisateur de la BDD.
	 * @param pwd: le pwd de l'utilisateur de la BDD.
	 * @return instance: instance de la connexion à la BDD.
	 */
	public static ConnexionBDD getInstance(String url, String login, String pwd) {
		if (instance == null) {
			instance = new ConnexionBDD(url, login, pwd);
		}
		return instance;
	}
	
	/**
	 * Permet de mettre à jour une table de la BDD.
	 * @param req: la requête à exécuter.
	 * @param lesParams: les paramètres de la requête à exécuter.
	 */
	public void reqUpdate(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			// essaie d'update dans la base de données
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
	 * @param req: la requête à exécuter.
	 * @param lesParams: les paramètres de la requête à exécuter.
	 */
	public void reqSelect(String req, ArrayList<Object> lesParams) {
		if(cn != null) {
			// essaie de sélectionner dans la base de données
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
	 * Permet de lire le résultat d'un select via un curseur.
	 * @return rs.next() si rs n'est pas null et que rs.next() est possible, false sinon.
	 */
	public boolean read() {
		if(rs != null) {
			// essaie de créer le curseur
			try {
				return rs.next();
			}catch(SQLException e) {}
		}
		return false;
	}
	
	/**
	 * Les champs à récupérer via le curseur.
	 * @param nameField: le nom du champ désiré.
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
