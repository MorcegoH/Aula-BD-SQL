package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtNome;
	private JTextField txtId;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

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

		txtNome = new JTextField();
		txtNome.setBounds(235, 190, 336, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(40, 190, 144, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

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

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(52, 236, 176, 20);
		getContentPane().add(txtEmail);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(279, 236, 292, 20);
		getContentPane().add(txtSenha);

		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(10, 239, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(235, 239, 46, 14);
		getContentPane().add(lblNewLabel_4);

		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/adicionar.png")));
		btnSalvar.setBounds(40, 267, 80, 80);
		getContentPane().add(btnSalvar);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/editar.png")));
		btnEditar.setBounds(260, 267, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/excluir.png")));
		btnExcluir.setBounds(486, 267, 80, 80);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 561, 115);
		getContentPane().add(scrollPane);

		JDesktopPane desktopPane = new JDesktopPane();
		scrollPane.setRowHeaderView(desktopPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// evenyos disparado ao clicar na linha da tabela
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);

		// uso da biblioteca Atxy2k para validações
		RestrictedTextField nome = new RestrictedTextField(this.txtNome);
		nome.setLimit(50);

		RestrictedTextField email = new RestrictedTextField(this.txtEmail);
		email.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
		senha.setLimit(250);

	} // Fim do construtor
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// Criando um objeto de acesso a classe DAO
	DAO dao = new DAO();
	private JTable table;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnEditar;

	/**
	 * Metodo responsavel pela pesquisa de cliente com uso da biblioteca RS2XML
	 */

	private void pesquisarCliente() {
		// ? -> parametro
		String read = "select idcli as ID, nome as cliente, email as Email, senha as Senha from clientes where nome like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 -> parametro (?)
			pst.setString(1, txtPesquisar.getText() + "%");
			// Executar a Query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Metodo responsavel por adicionar cliente no banco de dados
	 */
	private void adicionarCliente() {

		// validação de campos obrigatorios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o E-mail!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtEmail.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else {
			// inserir o cliente no banco de dados
			String create = "insert into clientes (nome,email,senha) values (?,?,md5(?))";
			try {
				// Abrir conexão com o banco
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtSenha.getText());
				// Criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente do banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
				// o catch abaixo se refere ao valor duplicado no e-mail(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "E-mail já cadastrado!\n Favor escolher outro e-mail para cadastrar!", "Mensagem",
						JOptionPane.WARNING_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Metodo responsavel por setear os campos da tabela nas caixas no formulario
	 * 
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] ...
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 2).toString());
		// txtSenha.setText(table.getModel().getValueAt(setar, 3). toString());

		// Gerenciar os botões
		btnSalvar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		// Segunda possibilidade de recuperar a senha, quando a senha estiver oculta
		// O editar funciona mesmo quando não puxar a coluna, no entanto, sempre quando
		// acessar o editar, será necessário substituir a senha
		// Pois senão ela permanecerá em branco.

		// 3ª possibilidade é criar um novo metodo
	}

	/**
	 * Metodo especifico para setar a senha
	 */

	private void setarSenha() {
		String read2 = "select senha from clientes where idcli=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Limpar os campos
	private void limpar() {
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtSenha.setText(null);
		// Limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botões
		btnSalvar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

}
