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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * La fenêtre permettant d'ajouter un nouveau personnel.
 * @author ugo.
 */
public class FrmAjoutPersonnel extends JFrame {

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
	 * Lancer l'application (debug).
	 * @param args: les arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAjoutPersonnel frame = new FrmAjoutPersonnel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Créer la fenêtre FrmAjoutPersonnel.
	 */
	public FrmAjoutPersonnel() {
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
		lblServicePersonnel.setBounds(10, 111, 126, 14);
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
		
		JComboBox comboBoxServicePersonnel = new JComboBox();
		comboBoxServicePersonnel.setBounds(146, 107, 150, 22);
		contentPane.add(comboBoxServicePersonnel);
		
		// remplissage du ComboBox avec les noms des services
		
		ArrayList<String> ajoutNomsServices = controle.lireNomServices();
		for (String unNomService : ajoutNomsServices){
			comboBoxServicePersonnel.addItem(unNomService);
		}
		
		JButton btnAjouterPersonnel = new JButton("Ajouter personnel");
		btnAjouterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNomPersonnel.getText().isBlank() ||  textFieldPrenomPersonnel.getText().isBlank() || textFieldTelephonePersonnel.getText().isBlank() || textFieldMailPersonnel.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Erreur: tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					controle.sauverEnBasePersonnel(0, comboBoxServicePersonnel.getSelectedIndex() + 1, textFieldNomPersonnel.getText(), textFieldPrenomPersonnel.getText(), textFieldTelephonePersonnel.getText(), textFieldMailPersonnel.getText());
					JOptionPane.showMessageDialog(null, "Le personnel a correctement été ajouté.", "Succès", JOptionPane.INFORMATION_MESSAGE);
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
		btnQuitterPersonnel.setBounds(439, 93, 137, 50);
		contentPane.add(btnQuitterPersonnel);
		
	}

}
