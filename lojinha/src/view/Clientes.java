package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		setTitle("Cllientes");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/pc.png")));
		setBounds(100, 100, 656, 393);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Evento disparado ao digitar na caixa de texto
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 31, 561, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(235, 190, 336, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(40, 190, 144, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(581, 19, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(194, 193, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(10, 193, 20, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(52, 236, 176, 20);
		getContentPane().add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(279, 236, 292, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(10, 239, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(235, 239, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setIcon(new ImageIcon(Clientes.class.getResource("/icones/adicionar.png")));
		btnNewButton.setBounds(40, 267, 80, 80);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Clientes.class.getResource("/icones/editar.png")));
		btnNewButton_1.setBounds(260, 267, 80, 80);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(Clientes.class.getResource("/icones/excluir.png")));
		btnNewButton_2.setBounds(486, 267, 80, 80);
		getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 561, 115);
		getContentPane().add(scrollPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		scrollPane.setRowHeaderView(desktopPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	} // Fim do construtor
	 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	// Criando um objeto de acesso a classe DAO
	DAO dao = new DAO();
	private JTable table;
	
		private void pesquisarCliente() {
			// ? -> parametro
			String read = "select idcli as ID, nome as cliente, email as Email, senha as Senha from clientes where nome like ?";
			try {
				//abrir a conexao com o banco
				Connection con = dao.conectar();
				//preparar a query(instrucao sql) para pesquisar no banco
				PreparedStatement pst = con.prepareStatement(read);
				//substituir o parametro(?) Atencao ao % para completar a query
				// 1 -> parametro (?)
				pst.setString(1, txtPesquisar.getText() + "%");
				//Executar a Query e obter os dados do banco (resultado)
				ResultSet rs = pst.executeQuery();
				//popular(preencher) a tabela com os dados do banco
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}
