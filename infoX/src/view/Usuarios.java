package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Usuarios extends JFrame {
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JLabel lblId;
	private JTextField txtId;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios frame = new Usuarios();
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
	public Usuarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//Evento ativado ao inciar a tela
				chkSenha.setVisible(false);
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/pc.png")));
		setBounds(100, 100, 708, 470);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 21, 57, 14);
		getContentPane().add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtUsuario.setBounds(62, 20, 331, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(70, 219, 48, 14);
		getContentPane().add(lblSenha);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(70, 183, 48, 25);
		getContentPane().add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(128, 182, 273, 20);
		getContentPane().add(txtLogin);

		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(506, 22, 29, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtId.setBounds(545, 11, 121, 62);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(
				new DefaultComboBoxModel(new String[] { "", "Administrador", "Usu\u00E1rio", "Tempor\u00E1rio" }));
		cboPerfil.setBounds(545, 84, 121, 66);
		getContentPane().add(cboPerfil);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPerfil.setBounds(506, 74, 48, 14);
		getContentPane().add(lblPerfil);

		btnSalvarU = new JButton("");
		btnSalvarU.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnSalvarU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnSalvarU.setBounds(62, 345, 64, 64);
		getContentPane().add(btnSalvarU);

		btnEditarU = new JButton("");
		btnEditarU.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnEditarU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tratamento do checkbox
				if (chkSenha.isSelected()) {
					editarUsuario();
				} else {
					editarUsuarioPersonalizado();

				}
				
			}
		});
		btnEditarU.setBounds(197, 345, 64, 64);
		getContentPane().add(btnEditarU);

		btnExcluirU = new JButton("");
		btnExcluirU.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluirU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluirU.setBounds(337, 345, 64, 64);
		getContentPane().add(btnExcluirU);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 486, 118);
		getContentPane().add(scrollPane);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table2);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(128, 218, 273, 20);
		getContentPane().add(txtSenha);
		
		chkSenha = new JCheckBox("Confirmar Altera\u00E7\u00E3o de Senha");
		chkSenha.setBounds(180, 245, 221, 23);
		getContentPane().add(chkSenha);

	}
	/// FIM DO CONSTRUTOR ?>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	DAO dao = new DAO();
	private JTable table;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JPasswordField txtSenha;
	private JComboBox cboPerfil;
	private JButton btnSalvarU;
	private JButton btnEditarU;
	private JButton btnExcluirU;
	private JCheckBox chkSenha;

	/**
	 * Pesquisa de Usuario
	 */

	private void pesquisarUsuario() {
		// ? -> parametro
		String read = "select id as ID, usuario as Usuario, login as Login, senha as Senha, perfil as Perfil from usuarios where usuario like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 -> parametro (?)
			pst.setString(1, txtUsuario.getText() + "%");
			// Executar a Query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table2.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Adicionar o Usuario
	 */

	private void adicionarUsuario() {

		// validação de campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do novo Usuario!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o novo Login!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Nova Senha!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o Perfil!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();

		} else {
			// inserir o cliente no banco de dados
			String create = "insert into usuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				// Abrir conexão com o banco
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// Criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente do banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuario criado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
				// o catch abaixo se refere ao valor duplicado no e-mail(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já cadastrado!\n Favor escolher outro login para o Usuário!",
						"Mensagem", JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Setar Campos
	 */

	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] ...
		int setar = table2.getSelectedRow();
		// setar os campos
		txtId.setText(table2.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table2.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table2.getModel().getValueAt(setar, 2).toString());
		txtSenha.setText(table2.getModel().getValueAt(setar, 3).toString());
		cboPerfil.setSelectedItem(table2.getModel().getValueAt(setar, 4));

		// Gerenciar os botões
		btnSalvarU.setEnabled(false);
		btnEditarU.setEnabled(true);
		btnExcluirU.setEnabled(true);
		chkSenha.setVisible(true);
	}

	// >>>>>>>>>>EDITAR USUARIO
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void editarUsuario() {

		// validação de campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o Perfil!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			// Editar os dados do cliente no banco
			String update = "update usuarios set usuario=?,login=?,senha=md5(?), perfil=? where id=?";

			try {
				// Abrir conexão com o banco
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());

				// Criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edição do cliente do banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do Usuario Atualizados com Sucesso!!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
				// o catch abaixo se refere ao valor duplicado no e-mail(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já cadastrado!\n Favor escolher outro Login para cadastrar!",
						"Mensagem", JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	//Segundo editar usuario sem mexer na senha
	
	private void editarUsuarioPersonalizado() {

		// validação de campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o Perfil!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			// Editar os dados do cliente no banco
			String update = "update usuarios set usuario=?,login=?, perfil=? where id=?";

			try {
				// Abrir conexão com o banco
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());

				// Criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edição do cliente do banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do Usuario Atualizados com Sucesso!!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
				// o catch abaixo se refere ao valor duplicado no e-mail(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já cadastrado!\n Favor escolher outro Login para cadastrar!",
						"Mensagem", JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	// FIm do segundo editar
	
	
	// >>>>>>>>>>>EXCLUIR USUARIO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void excluirUsuario() {
		// Confirmação de Exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Usuario?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// codigo principal
			String delete = "delete from usuarios where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}

				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão Negada. \nFavor Verificar com Administrador do sistema.");

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// >>>>> LIMPAR CAMPOS >>>>>>>>>>>>>>
	private void limpar() {
		txtUsuario.setText(null);
		txtId.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem(null);
		// Limpar a tabela
		((DefaultTableModel) table2.getModel()).setRowCount(0);
		// gerenciar os botões
		btnSalvarU.setEnabled(true);
		btnEditarU.setEnabled(false);
		btnExcluirU.setEnabled(false);
	}
}
