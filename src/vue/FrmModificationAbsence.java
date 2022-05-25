package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * La fen�tre principale permettant de modifier une absence.
 * @author ugo.
 */
public class FrmModificationAbsence extends JFrame {

	/**
	 * Contenu de la fen�tre.
	 */
	private JPanel contentPane;
	
	/**
	 * Textfield contenant les donn�es relatives � datedebut.
	 */
	private JTextField textFieldDatedebut;
	
	/**
	 * Textfield contenant les donn�es relatives � datefin.
	 */
	private JTextField textFieldDatefin;
	
	/**
	 * Initialisation du contr�leur.
	 */
	private Controle controle = new Controle();
	
	/**
	 * Combobox contenant les diff�rents noms des diff�rents motifs.
	 */
	private JComboBox comboBoxMotifAbsence = new JComboBox();
	
	/**
	 * L'idpersonnel du personnel dont on veut modifier une absence.
	 */
	private int idpersonnel;
	
	/**
	 * L'idpersonnel de l'absence sauvegard� (pour charger ses informations).
	 */
	private String s_idpersonnel;
	
	/**
	 * La datedebut de l'absence sauvegard�e (pour charger ses informations).
	 */
	private String s_datedebut;
	
	/**
	 * L'idmotif du personnel sauvegard� (pour charger ses informations).
	 */
	private String s_idmotif;
	
	/**
	 * La datefin de l'absence sauvegard�e (pour charger ses informations).
	 */
	private String s_datefin;
	
	/**
	 * Setter sur l'idpersonneL
	 * @param idpersonnel: la valeur idpersonnel � modifier.
	 */
	public void setIdpersonnel(int idpersonnel) {
		this.idpersonnel = idpersonnel;
	}
	
	/**
	 * Setter sur le texte de textFieldDatedebut.
	 * @param s: la valeur s � mettre dans le texte de textFieldDatedebut.
	 */
	public void setTextFieldDatedebut(String s) {
		textFieldDatedebut.setText(s);
	}
	
	/**
	 * Setter sur le texte de textFieldDatefin.
	 * @param s: la valeur s � mettre dans le texte de textFieldDatefin.
	 */
	public void setTextFieldDatefin(String s) {
		textFieldDatefin.setText(s);
	}
	
	/**
	 * Setter sur les �l�ments de comboBoxMotifAbsence.
	 * @param i: la valeur i � mettre dans les �l�ments de comboBoxMotifAbsence.
	 */
	public void setComboBoxMotifAbsenceSelectedIndex(int i) {
		comboBoxMotifAbsence.setSelectedIndex(i-1);
	}
	
	/**
	 * Setter sur la sauvegarde s_idpersonnel.
	 * @param s_idpersonnel: la valeur s_idpersonnel � modifier.
	 */
	public void setSIdpersonnel(String s_idpersonnel) {
		this.s_idpersonnel = s_idpersonnel;
	}
	
	/**
	 * Setter sur la sauvegarde s_datedebut.
	 * @param s_datedebut: la valeur s_datedebut � modifier.
	 */
	public void setSDatedebut(String s_datedebut) {
		this.s_datedebut = s_datedebut;
	}
	
	/**
	 * Setter sur la sauvegarde s_motif.
	 * @param s_idmotif: la valeur s_motif � modifier.
	 */
	public void setSIdmotif(String s_idmotif) {
		this.s_idmotif = s_idmotif;
	}
	
	/**
	 * Setter sur la sauvegarde s_datefin.
	 * @param s_datefin: la valeur s_datefin � modifier.
	 */
	public void setSDatefin(String s_datefin) {
		this.s_datefin = s_datefin;
	}
	
	/**
	 * Permet de v�rifier que l'information donn�e est belle et bien une date.
	 * @param s: le String qu'on essaye de convertir en Date.
	 * @return true si l'information donn�e est une date, false sinon.
	 */
	public Boolean estUneDate(String s) {
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	/**
	 * Lancer l'application (debug).
	 * @param args: les arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAjoutAbsence frame = new FrmAjoutAbsence();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Permet de v�rifier si la date est conforme (la date de fin n'est pas inf�rieure � la date de d�but).
	 * @param datedebut: la datedebut.
	 * @param datefin: la datefin.
	 * @return true si les dates sont conformes, false sinon.
	 */
	public Boolean problemesDates(String datedebut, String datefin) {
		String y1 = "";
		String m1 = "";
		String d1 = "";
		String y2 = "";
		String m2 = "";
		String d2 = "";
		y1 = y1 + datedebut.charAt(0) + datedebut.charAt(1) + datedebut.charAt(2) + datedebut.charAt(3);
		m1 = m1 + datedebut.charAt(5) + datedebut.charAt(6);
		d1 = d1 + datedebut.charAt(8) + datedebut.charAt(9);
		y2 = y2 + datefin.charAt(0) + datefin.charAt(1) + datefin.charAt(2) + datefin.charAt(3);
		m2 = m2 + datefin.charAt(5) + datefin.charAt(6);
		d2 = d2 + datefin.charAt(8) + datefin.charAt(9);
		try {
			int y3 = Integer.parseInt(y2) - Integer.parseInt(y1);
			if (y3 == 0) {
				int m3 = Integer.parseInt(m2) - Integer.parseInt(m1);
				if (m3 == 0) {
					int d3 = Integer.parseInt(d2) - Integer.parseInt(d1);
					if (d3 >= 0) {
						return true;
					} else {
						return false;
					}
				} else if (m3 > 0) {
					return true;
				} else {
					return false;
				}
			} else if (y3 > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Cr�er la fen�tre FrmModificationAbsence.
	 */
	public FrmModificationAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDateDebutAbsence = new JLabel("Date de d\u00E9but");
		lblDateDebutAbsence.setBounds(10, 11, 96, 14);
		contentPane.add(lblDateDebutAbsence);
		
		JLabel lblDateFinAbsence = new JLabel("Date de fin");
		lblDateFinAbsence.setBounds(10, 36, 65, 14);
		contentPane.add(lblDateFinAbsence);
		
		JLabel lblMotifAbsence = new JLabel("Motif");
		lblMotifAbsence.setBounds(10, 61, 65, 14);
		contentPane.add(lblMotifAbsence);
		
		textFieldDatedebut = new JTextField();
		textFieldDatedebut.setBounds(146, 8, 156, 20);
		contentPane.add(textFieldDatedebut);
		textFieldDatedebut.setColumns(10);
		
		textFieldDatefin = new JTextField();
		textFieldDatefin.setColumns(10);
		textFieldDatefin.setBounds(146, 33, 156, 20);
		contentPane.add(textFieldDatefin);
		
		comboBoxMotifAbsence.setBounds(146, 61, 156, 22);
		contentPane.add(comboBoxMotifAbsence);
		
		ArrayList<String> ajoutNomsMotifs = controle.lireNomMotifs();
		for (String unNomMotif : ajoutNomsMotifs){
			comboBoxMotifAbsence.addItem(unNomMotif);
		}
		
		JButton btnAjouterAbsence = new JButton("Modifier absence");
		btnAjouterAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldDatedebut.getText().isBlank() ||  textFieldDatefin.getText().isBlank()){
					JOptionPane.showMessageDialog(null, "Erreur: tous les champs doivent �tre remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					if (estUneDate(textFieldDatedebut.getText()) && estUneDate(textFieldDatefin.getText())) {
						if (problemesDates(textFieldDatedebut.getText(), textFieldDatefin.getText())) {
							try {
								JOptionPane.showMessageDialog(null, "L'absence a correctement �t� modifi�e.", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
								controle.modifierEnBaseAbsence(s_idpersonnel, s_datedebut, s_idmotif, s_datefin, Integer.toString(comboBoxMotifAbsence.getSelectedIndex() + 1), textFieldDatedebut.getText(), textFieldDatefin.getText());
								dispose();
								controle.changerFenetreAbsences(idpersonnel);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Erreur: la date de fin pr�c�de la date de d�but.", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erreur: l'une des valeurs ins�r�e n'est pas une date.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAjouterAbsence.setBounds(340, 11, 137, 64);
		contentPane.add(btnAjouterAbsence);
		
		JButton btnQuitterPersonnel = new JButton("Annuler");
		btnQuitterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreAbsences(idpersonnel);
			}
		});
		btnQuitterPersonnel.setBounds(340, 82, 137, 50);
		contentPane.add(btnQuitterPersonnel);
		
		JLabel lblInformation = new JLabel("Format: Ann\u00E9e-Mois-Jour");
		lblInformation.setBounds(10, 94, 197, 14);
		contentPane.add(lblInformation);
		
		JLabel lblExemple = new JLabel("Exemple: 2002-05-17 pour le 17 mai 2002");
		lblExemple.setBounds(10, 119, 292, 14);
		contentPane.add(lblExemple);
		FrmModificationAbsence f = this;
	}
}
