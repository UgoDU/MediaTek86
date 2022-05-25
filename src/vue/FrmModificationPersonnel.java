package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import modele.Service;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * La fenêtre principale permettant de modifier un personnel.
 * @author ugo.
 */
public class FrmModificationPersonnel extends JFrame {

	/**
	 * Contenu de la fenêtre.
	 */
	private JPanel contentPane;
	
	/**
	 * Textfield contenant les données relatives à nom.
	 */
	private JTextField textFieldNomPersonnel;
	
	/**
	 * Textfield contenant les données relatives à prenom.
	 */
	private JTextField textFieldPrenomPersonnel;
	
	/**
	 * Textfield contenant les données relatives à tel.
	 */
	private JTextField textFieldTelephonePersonnel;
	
	/**
	 * Textfield contenant les données relatives à mail.
	 */
	private JTextField textFieldMailPersonnel;
	
	/**
	 * Initialisation du contrôleur.
	 */
	private Controle controle;
	
	/**
	 * Combobox contenant les différents noms des différents services.
	 */
	private JComboBox comboBoxServicePersonnel;
	
	/**
	 * L'idpersonnel du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_idpersonnel;
	
	/**
	 * L'idservice du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_idservice;
	
	/**
	 * Le nom du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_nom;
	
	/**
	 * Le prenom du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_prenom;
	
	/**
	 * Le tel du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_tel;
	
	/**
	 * Le mail du personnel sauvegardé (pour charger ses informations).
	 */
	private String s_mail;
	
	/**
	 * Setter sur le texte de textFieldNomPersonnel.
	 * @param s: la valeur s à mettre dans le texte de textFieldNomPersonnel.
	 */
	public void setTextFieldNomPersonnel(String s) {
		textFieldNomPersonnel.setText(s);
	}

	/**
	 * Setter sur le texte de textFieldPrenomPersonnel.
	 * @param s: la valeur s à mettre dans le texte de textFieldPrenomPersonnel.
	 */
	public void setTextFieldPrenomPersonnel(String s) {
		textFieldPrenomPersonnel.setText(s);
	}
	
	/**
	 * Setter sur le texte de textFieldTelPersonnel.
	 * @param s: la valeur s à mettre dans le texte de textFieldTelPersonnel.
	 */
	public void setTextFieldTelephonePersonnel(String s) {
		textFieldTelephonePersonnel.setText(s);
	}
	
	/**
	 * Setter sur le texte de textFieldMailPersonnel.
	 * @param s: la valeur s à mettre dans le texte de textFieldMailPersonnel.
	 */
	public void setTextFieldMailPersonnel(String s) {
		textFieldMailPersonnel.setText(s);
	}
	
	/**
	 * Setter sur les éléments de comboBoxServicePersonnel.
	 * @param i: la valeur i à mettre dans les éléments de comboBoxServicePersonnel.
	 */
	public void setComboBoxServicePersonnelSelectedIndex(int i) {
		comboBoxServicePersonnel.setSelectedIndex(i-1);
	}
	
	/**
	 * Setter sur la sauvegarde s_idpersonnel.
	 * @param s_idpersonnel: la valeur s_idpersonnel à modifier.
	 */
	public void setSIdpersonnel(String s_idpersonnel) {
		this.s_idpersonnel = s_idpersonnel;
	}

	/**
	 * Setter sur la sauvegarde s_idservice.
	 * @param s_idservice: la valeur s_idservice à modifier.
	 */
	public void setSIdservice(String s_idservice) {
		this.s_idservice = s_idservice;
	}
	
	/**
	 * Setter sur la sauvegarde s_nom.
	 * @param s_nom: la valeur s_nom à modifier.
	 */
	public void setSNom(String s_nom) {
		this.s_nom = s_nom;
	}
	
	/**
	 * Setter sur la sauvegarde s_prenom.
	 * @param s_prenom: la valeur s_prenom à modifier.
	 */
	public void setSPrenom(String s_prenom) {
		this.s_prenom = s_prenom;
	}
	
	/**
	 * Setter sur la sauvegarde s_tel.
	 * @param s_tel: la valeur s_tel à modifier.
	 */
	public void setSTel(String s_tel) {
		this.s_tel = s_tel;
	}
	
	/**
	 * Setter sur la sauvegarde s_mail.
	 * @param s_mail: la valeur s_mail à modifier.
	 */
	public void setSMail(String s_mail) {
		this.s_mail = s_mail;
	}
	
	/**
	 * Lancer l'application (debug).
	 * @param args: les arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmModificationPersonnel frame = new FrmModificationPersonnel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Créer la fenêtre FrmModificationPersonnel.
	 */
	public FrmModificationPersonnel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomPersonnel = new JLabel("Nom");
		lblNomPersonnel.setBounds(10, 11, 49, 14);
		contentPane.add(lblNomPersonnel);
		
		JLabel lblPrenomPersonnel = new JLabel("Pr\u00E9nom");
		lblPrenomPersonnel.setBounds(10, 36, 49, 14);
		contentPane.add(lblPrenomPersonnel);
		
		JLabel lblTelPersonnel = new JLabel("T\u00E9l\u00E9phone");
		lblTelPersonnel.setBounds(10, 61, 65, 14);
		contentPane.add(lblTelPersonnel);
		
		JLabel lblMailPersonnel = new JLabel("Mail");
		lblMailPersonnel.setBounds(10, 86, 65, 14);
		contentPane.add(lblMailPersonnel);
		
		JLabel lblServicePersonnel = new JLabel("Service d'affectation");
		lblServicePersonnel.setBounds(10, 111, 105, 14);
		contentPane.add(lblServicePersonnel);
		
		textFieldNomPersonnel = new JTextField();
		textFieldNomPersonnel.setBounds(146, 8, 150, 20);
		contentPane.add(textFieldNomPersonnel);
		textFieldNomPersonnel.setColumns(10);
		
		textFieldPrenomPersonnel = new JTextField();
		textFieldPrenomPersonnel.setColumns(10);
		textFieldPrenomPersonnel.setBounds(146, 33, 150, 20);
		contentPane.add(textFieldPrenomPersonnel);
		
		textFieldTelephonePersonnel = new JTextField();
		textFieldTelephonePersonnel.setColumns(10);
		textFieldTelephonePersonnel.setBounds(146, 58, 150, 20);
		contentPane.add(textFieldTelephonePersonnel);
		
		textFieldMailPersonnel = new JTextField();
		textFieldMailPersonnel.setColumns(10);
		textFieldMailPersonnel.setBounds(146, 83, 150, 20);
		contentPane.add(textFieldMailPersonnel);
		
		comboBoxServicePersonnel = new JComboBox();
		comboBoxServicePersonnel.setBounds(146, 107, 150, 22);
		contentPane.add(comboBoxServicePersonnel);
		
		
		ArrayList<String> ajoutNomsServices = controle.lireNomServices();
		for (String unNomService : ajoutNomsServices){
			comboBoxServicePersonnel.addItem(unNomService);
		}
		
		JButton btnAjouterPersonnel = new JButton("Modifier personnel");
		btnAjouterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNomPersonnel.getText().isBlank() ||  textFieldPrenomPersonnel.getText().isBlank() || textFieldTelephonePersonnel.getText().isBlank() || textFieldMailPersonnel.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Erreur: tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Le personnel a correctement été modifié.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(Integer.toString(comboBoxServicePersonnel.getSelectedIndex() + 1) + textFieldNomPersonnel.getText() + textFieldPrenomPersonnel.getText() + textFieldTelephonePersonnel.getText() + textFieldMailPersonnel.getText());
					controle.modifierEnBasePersonnel(s_idpersonnel, s_idservice, s_nom, s_prenom, s_tel, s_mail, Integer.toString(comboBoxServicePersonnel.getSelectedIndex() + 1), textFieldNomPersonnel.getText(), textFieldPrenomPersonnel.getText(), textFieldTelephonePersonnel.getText(), textFieldMailPersonnel.getText());
					dispose();
					controle.changerFenetreMain();
				}
			}
		});
		btnAjouterPersonnel.setBounds(439, 11, 137, 64);
		contentPane.add(btnAjouterPersonnel);
		
		JButton btnQuitterPersonnel = new JButton("Annuler");
		btnQuitterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreMain();
			}
		});
		btnQuitterPersonnel.setBounds(439, 86, 137, 50);
		contentPane.add(btnQuitterPersonnel);
		
	}

}
