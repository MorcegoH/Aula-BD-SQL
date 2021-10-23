package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel painelPrincipal;
	private JLabel lblData;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// Evento disparado ao ativar o JFrame
				setarData();
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/mit.png")));
		setTitle("Lojinha - SGE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 414);
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(128, 128, 128));
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		painelPrincipal.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(-4, 326, 774, 49);
		painelPrincipal.add(panel_1);
		panel_1.setLayout(null);

		lblStatus = new JLabel("New label");
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/icones/dbof.png")));
		lblStatus.setBounds(10, 11, 32, 32);
		panel_1.add(lblStatus);

		lblData = new JLabel("");
		lblData.setForeground(Color.RED);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(467, 11, 297, 32);
		panel_1.add(lblData);

		JButton btnEstoque = new JButton("");
		btnEstoque.setToolTipText("Principal");
		btnEstoque.setIcon(new ImageIcon(Principal.class.getResource("/icones/estoque.png")));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEstoque.setBounds(10, 11, 128, 128);
		painelPrincipal.add(btnEstoque);

		JButton btnRelatorios = new JButton("");
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnRelatorios.setBounds(10, 187, 128, 128);
		painelPrincipal.add(btnRelatorios);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnClientes.setBounds(632, 11, 128, 128);
		painelPrincipal.add(btnClientes);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botao
				Sobre sobre = new Sobre(); // criar objeto
				sobre.setVisible(true); // exibir o Jdialog Sobre
			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBackground(Color.GRAY);
		btnSobre.setBorder(null);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnSobre.setBounds(632, 187, 128, 128);
		painelPrincipal.add(btnSobre);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/icones/bat (1).png")));
		lblNewLabel_1.setBounds(362, 11, 64, 49);
		painelPrincipal.add(lblNewLabel_1);

	} // Fim do construtor

	/**
	 * Metodo responsavel por setar a data e hora na label lblData
	 */
	private void setarData() {
		// as linhas debaixo são usadas para obter e formatar a hora do sistema
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		// a linha abaixo substitui a label do rodapé pela data
		lblData.setText(formatador.format(dataLabel));
	}

	/**
	 * Metodo responsavel pela exibição do status de conexão
	 */
	private void status() {
		DAO dao = new DAO();
		try {
			// Abrir a conexao com o banco
			Connection con = dao.conectar();
			System.out.println(con);
			// Mudando o ícone do rodapé no caso do banco de dados estar disponivel
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbof.png")));
				
			} else {
				
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbon.png")));
			}
			// IMPORTANTE: Sempre encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
