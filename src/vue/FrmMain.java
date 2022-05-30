package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import java.awt.Dimension;

/**
 * La fenêtre principale permettant d'ajouter un nouveau personnel.
 * @author ugo.
 */
public class FrmMain extends JFrame {

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
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Permet de remplir la table avec les absences du personnel via un idpersonnel donné.
	 * @return data: le tableau des données à remplir.
	 */
	public String[][] genererTable(){
		ArrayList<ArrayList<String>> listePersonnels = controle.lirePersonnelsRangesASC();
		int taille1 = 0;
		int taille2 = 0;
		// création du tableau data dans le but de remplir la table
		for (ArrayList<String> ligne : listePersonnels) {
			taille1++;
		}
		for (ArrayList<String> ligne : listePersonnels) {
			for (String s : ligne) {
				taille2++;
			}
		}
		String[][] data = new String[taille2][taille1*taille2];
		int j = 0;
		for (ArrayList<String> ligne : listePersonnels) {
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

	/**
	 * Créer la fenêtre FrmMain.
	 */
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1215, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// initialisation des données nécessaires au remplissage de la table
		
		String[][] data = genererTable();
		String[] columns = {"idpersonnel","idservice", "prenom", "nom", "tel", "mail", "nomservice"};
		
		
		JButton btnAjouterPersonnel = new JButton("Ajouter un personnel");
		btnAjouterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				String[] remplissage = new String[0];
				controle.changerFenetreAjoutPersonnel();
			}
		});
		btnAjouterPersonnel.setBounds(21, 330, 225, 32);
		contentPane.add(btnAjouterPersonnel);
		
		JButton btnSupprimerPersonnel = new JButton("Supprimer un personnel");
		btnSupprimerPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucun personnel sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucun personnel sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						int choix = JOptionPane.showConfirmDialog(null, "Etes vous sûr de vouloir supprimer ce personnel?", "Attention", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE);
						if (choix == JOptionPane.YES_OPTION){
							String recup_idpersonnel = data[tableDonnees.getSelectedRow()][0];
							String recup_idservice = data[tableDonnees.getSelectedRow()][1];
							String recup_nom = data[tableDonnees.getSelectedRow()][2];
							String recup_prenom = data[tableDonnees.getSelectedRow()][3];
							String recup_tel = data[tableDonnees.getSelectedRow()][4];
							String recup_mail = data[tableDonnees.getSelectedRow()][5];
							controle.supprimerDeLaBasePersonnels(recup_idpersonnel, recup_idservice, recup_nom, recup_prenom, recup_tel, recup_mail);
							dispose();
							controle.changerFenetreMain();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs personnels sélectionnés.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimerPersonnel.setBounds(256, 330, 225, 32);
		contentPane.add(btnSupprimerPersonnel);
		
		JButton btnModifierPersonnel = new JButton("Modifier un personnel");
		btnModifierPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucun personnel sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucun personnel sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						if (data[tableDonnees.getSelectedRow()][0] == null) {
							JOptionPane.showMessageDialog(null, "Erreur: la ligne sélectionnée est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							dispose();
							String[] remplissage = new String[6];
							remplissage[0] = data[tableDonnees.getSelectedRow()][0];
							remplissage[1] = data[tableDonnees.getSelectedRow()][1];
							remplissage[2] = data[tableDonnees.getSelectedRow()][2];
							remplissage[3] = data[tableDonnees.getSelectedRow()][3];
							remplissage[4] = data[tableDonnees.getSelectedRow()][4];
							remplissage[5] = data[tableDonnees.getSelectedRow()][5];
							controle.changerFenetreModificationPersonnel(remplissage);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs personnels sélectionnés.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifierPersonnel.setBounds(491, 330, 225, 32);
		contentPane.add(btnModifierPersonnel);
		
		JButton btnAfficherAbsences = new JButton("Afficher les absences");
		btnAfficherAbsences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Erreur: aucun personnel sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: la ligne sélectionnée est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						dispose();
						controle.changerFenetreAbsences(Integer.parseInt(data[tableDonnees.getSelectedRow()][0]));
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs personnels sélectionnés.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAfficherAbsences.setBounds(726, 330, 225, 32);
		contentPane.add(btnAfficherAbsences);
		
		JButton btnQuitterPersonnel = new JButton("Quitter");
		btnQuitterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreConnexion();
			}
		});
		btnQuitterPersonnel.setBounds(961, 330, 225, 32);
		contentPane.add(btnQuitterPersonnel);
		
		// initialisation de la table et options
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(21, 11, 1165, 310);
		contentPane.add(scrollPane);
		tableDonnees = new JTable(data, columns) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		
		tableDonnees.setMaximumSize(new Dimension(2147483647, 2147483647));
		scrollPane.setViewportView(tableDonnees);
		tableDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDonnees.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		scrollPane.getViewport().setViewPosition(new Point(0,0));
	}
}
