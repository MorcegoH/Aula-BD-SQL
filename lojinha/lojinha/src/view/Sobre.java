package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setTitle("SOBRE");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/icones/pc.png")));
		setBounds(100, 100, 450, 347);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gest\u00E3o de E-commerce - Ver 1.0");
		lblNewLabel.setBounds(31, 26, 291, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Aluno Heder Santos");
		lblNewLabel_1.setBounds(31, 51, 170, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sob Licensa MIT");
		lblNewLabel_2.setBounds(31, 76, 120, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Sobre.class.getResource("/icones/mit.png")));
		lblNewLabel_3.setBounds(340, 233, 64, 64);
		getContentPane().add(lblNewLabel_3);

	}

}
