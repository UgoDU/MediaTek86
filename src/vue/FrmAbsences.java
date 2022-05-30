package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * La fenêtre permettant de gérer les absences.
 * @author ugo.
 */
public class FrmAbsences extends JFrame {

	/**
	 * Contenu de la fenêtre.
	 */
	private JPanel contentPane;
	
	/**
	 * Table contenant les données relatives aux absences.
	 */
	private JTable tableDonnees;
	
	/**
	 * Initialisation du contrôleur.
	 */
	private Controle controle = new Controle();
	
	/**
	 * Lancer l'application (debug).
	 * @param args: les arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAbsences frame = new FrmAbsences(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Permet de remplir la table avec les absences du personnel via un idpersonnel donné.
	 * @param idpersonnel: l'idpersonnel de la personne dont on souhaite connaître les absences.
	 * @return data: le tableau des données à remplir.
	 */
	public String[][] genererTable(int idpersonnel){
		ArrayList<ArrayList<String>> listeAbsences = Controle.lireAbsencesRangesDESC(idpersonnel);
		int taille1 = 0;
		int taille2 = 0;
		// création du tableau data dans le but de remplir la table
		if (listeAbsences.isEmpty()) {
			String[][] data = new String[1][5];
			data[0][0] = "";
			data[0][1] = "";
			data[0][2] = "";
			data[0][3] = "";
			data[0][4] = "";
			return data;
		} else {
			for (ArrayList<String> ligne : listeAbsences) {
				taille1++;
			}
			for (ArrayList<String> ligne : listeAbsences) {
				for (String s : ligne) {
					taille2++;
				}
			}
			String[][] data = new String[taille2][taille1*taille2];
			int j = 0;
			for (ArrayList<String> ligne : listeAbsences) {
				String[] l = new String[taille2];
				int i = 0;
				for (String s : ligne) {
					l[i] = s;
					i++;
				}
				data[j] = l;
				j++;
			}
			return data;
		}
	}


	/**
	 * Créer la fenêtre FrmAbsences.
	 * @param idpersonnel: l'idpersonnel du personnel dont on souhaite connaître les absences.
	 */
	public FrmAbsences(int idpersonnel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// initialisation des données nécessaires au remplissage de la table
		
		String[][] data = genererTable(idpersonnel);
		String[] columns = {"idpersonnel","datedebut", "idmotif", "datefin", "nommotif"};
		
		JButton btnAjouterAbsence = new JButton("Ajouter une absence");
		btnAjouterAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreAjoutAbsence(idpersonnel);
			}
		});
		btnAjouterAbsence.setBounds(21, 330, 180, 32);
		contentPane.add(btnAjouterAbsence);
		
		JButton btnSupprimerAbsence = new JButton("Supprimer une absence");
		btnSupprimerAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucune absence sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucune absence sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						int choix = JOptionPane.showConfirmDialog(null, "Etes vous sûr de vouloir supprimer cette absence?", "Attention", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE);
						if (choix == JOptionPane.YES_OPTION){
							String recup_idpersonnel = data[tableDonnees.getSelectedRow()][0];
							String recup_datedebut = data[tableDonnees.getSelectedRow()][1];
							String recup_idmotif = data[tableDonnees.getSelectedRow()][2];
							String recup_datefin = data[tableDonnees.getSelectedRow()][3];
							controle.supprimerDeLaBaseAbsences(recup_idpersonnel, recup_datedebut, recup_idmotif, recup_datefin);
							dispose();
							controle.changerFenetreAbsences(idpersonnel);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs absences sélectionnées.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimerAbsence.setBounds(211, 330, 180, 32);
		contentPane.add(btnSupprimerAbsence);
		
		JButton btnModifierAbsence = new JButton("Modifier une absence");
		btnModifierAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucune absence sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucune absence sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						dispose();
						String[] remplissage = new String[6];
						remplissage[0] = data[tableDonnees.getSelectedRow()][0];
						remplissage[1] = data[tableDonnees.getSelectedRow()][1];
						remplissage[2] = data[tableDonnees.getSelectedRow()][2];
						remplissage[3] = data[tableDonnees.getSelectedRow()][3];
						controle.changerFenetreModificationAbsence(remplissage);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs absences sélectionnées.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifierAbsence.setBounds(401, 330, 180, 32);
		contentPane.add(btnModifierAbsence);
		
		JButton btnRetour = new JButton("Retour au personnel");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Controle.changerFenetreMain();
			}
		});
		btnRetour.setBounds(591, 330, 180, 32);
		contentPane.add(btnRetour);
		
		// initialisation de la table et options
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 771, 309);
		contentPane.add(scrollPane);
		tableDonnees = new JTable(data, columns);
		scrollPane.setViewportView(tableDonnees);
		tableDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDonnees.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableDonnees.setAutoscrolls(true);
		scrollPane.getViewport().setViewPosition(new Point(0,0));
	}
}
