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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Servico extends JDialog {
	private JTextField txtPesquisa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servico dialog = new Servico();
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
	public Servico() {
		setTitle("Pesquisa de Servi\u00E7o");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servico.class.getResource("/img/pc.png")));
		setBounds(100, 100, 666, 300);
		getContentPane().setLayout(null);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisa.setBounds(10, 23, 194, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(218, 11, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 54, 598, 127);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 598, 127);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	} //Construtor
	
	
	DAO dao = new DAO();
	private void pesquisarCliente() {
		
		String read = "select idcli as ID, nome as Cliente, cep as Cep, endereco as Endereço, numero as Nº, complemento as Complemento,\r\n"
				+ " bairro as Bairro, uf as UF, fone as Fone, email as Email, cidade as Cidade from clientes where nome like ?";
		try {
			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setString(1, txtPesquisa.getText() + "%");
			
			ResultSet rs = pst.executeQuery();
		
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
