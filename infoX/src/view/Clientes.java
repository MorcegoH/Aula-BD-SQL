package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtIdCli;
	private JTextField txtNomeCli;
	private JTextField txtFoneCli;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/pc.png")));
		setBounds(100, 100, 705, 458);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 15, 243, 32);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(263, 15, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("*Campos Obrigat\u00F3rios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(458, 49, 148, 20);
		getContentPane().add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 80, 659, 118);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 659, 118);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		txtIdCli = new JTextField();
		txtIdCli.setEditable(false);
		txtIdCli.setBounds(36, 209, 108, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 212, 21, 18);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("*Nome");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(154, 210, 39, 18);
		getContentPane().add(lblNewLabel_2_1);

		txtNomeCli = new JTextField();
		txtNomeCli.setColumns(10);
		txtNomeCli.setBounds(199, 210, 224, 20);
		getContentPane().add(txtNomeCli);

		JLabel lblNewLabel_2_1_1 = new JLabel("*Fone");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBounds(433, 209, 46, 18);
		getContentPane().add(lblNewLabel_2_1_1);

		txtFoneCli = new JTextField();
		txtFoneCli.setColumns(10);
		txtFoneCli.setBounds(490, 209, 179, 20);
		getContentPane().add(txtFoneCli);

		JLabel lblNewLabel_2_2 = new JLabel("CEP");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(10, 307, 21, 18);
		getContentPane().add(lblNewLabel_2_2);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(36, 307, 108, 20);
		getContentPane().add(txtCep);

		JButton btnCep = new JButton("Pesquisar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(154, 306, 99, 23);
		getContentPane().add(btnCep);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(70, 270, 333, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(36, 337, 68, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(351, 307, 318, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(458, 270, 211, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(158, 338, 194, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(433, 341, 64, 64);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnEditar.setBounds(523, 341, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(607, 341, 64, 64);
		getContentPane().add(btnExcluir);

		JLabel lblNewLabel_3 = new JLabel("*Endereço");
		lblNewLabel_3.setBounds(10, 273, 63, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("*Nº");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 340, 21, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Complemento");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(263, 309, 89, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("*Bairro");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1.setBounds(413, 272, 45, 14);
		getContentPane().add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_1_1 = new JLabel("*Cidade");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1_1.setBounds(109, 340, 46, 14);
		getContentPane().add(lblNewLabel_5_1_1);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(387, 335, 42, 22);
		getContentPane().add(cboUf);

		JLabel lblNewLabel_2_3 = new JLabel("*UF");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(361, 336, 21, 18);
		getContentPane().add(lblNewLabel_2_3);

		JLabel lbl = new JLabel("Email");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setBounds(10, 248, 32, 14);
		getContentPane().add(lbl);

		txtEmailCli = new JTextField();
		txtEmailCli.setBounds(46, 243, 377, 19);
		getContentPane().add(txtEmailCli);
		txtEmailCli.setColumns(10);

		RestrictedTextField nome = new RestrictedTextField(this.txtNomeCli);
		nome.setLimit(50);
		RestrictedTextField cep = new RestrictedTextField(this.txtCep);
		cep.setLimit(9);
		RestrictedTextField endereco = new RestrictedTextField(this.txtEndereco);
		endereco.setLimit(50);
		RestrictedTextField numero = new RestrictedTextField(this.txtNumero);
		numero.setLimit(12);
		RestrictedTextField complemento = new RestrictedTextField(this.txtComplemento);
		complemento.setLimit(30);
		RestrictedTextField bairro = new RestrictedTextField(this.txtBairro);
		bairro.setLimit(50);
		RestrictedTextField cidade = new RestrictedTextField(this.txtCidade);
		cidade.setLimit(50);
		RestrictedTextField fone = new RestrictedTextField(this.txtFoneCli);
		fone.setLimit(15);
		RestrictedTextField email = new RestrictedTextField(this.txtEmailCli);
		email.setLimit(100);
	} // Fim do construtor

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
					if (element.getQualifiedName().equals("resultado")) {
						resultado = element.getText();
						if (resultado.equals("1")) {
							// lblStatus.setIcon(new
							// javax.swing.ImageIcon(getClass().getResource("/img/checked.png")));

						} else {
							JOptionPane.showMessageDialog(null, "CEP n�o encontrado!!");
						}

					}
				}
			}
			// Setar o campo endereco
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Pesquisa Clientes
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// Criando um objeto de acesso a classe DAO
	DAO dao = new DAO();
	private JTable table;
	private JTextField txtEmailCli;
	private JButton btnExcluir;
	private JButton btnAdicionar;
	private JButton btnEditar;

	private void pesquisarCliente() {
		// ? -> parametro
		String read = "select idcli as ID, nome as Cliente, cep as Cep, endereco as Endereço, numero as Nº, complemento as Complemento,\r\n"
				+ " bairro as Bairro, uf as UF, fone as Fone, email as Email, cidade as Cidade from clientes where nome like ?";
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

	private void adicionarCliente()

	{
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtNomeCli.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtCidade.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Telefone!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtFoneCli.requestFocus();
		
		} else {
			String create = "insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email,cidade) values (?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, cboUf.getSelectedItem().toString());
				pst.setString(8, txtFoneCli.getText());
				pst.setString(9, txtEmailCli.getText());
				pst.setString(10, txtCidade.getText());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "E-mail já cadastrado!\n Favor escolher outro e-mail para cadastrar!", "Mensagem",
						JOptionPane.WARNING_MESSAGE);
				txtEmailCli.setText(null);
				txtEmailCli.requestFocus();
			
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void setarCampos() {
		int setar = table.getSelectedRow();
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNomeCli.setText(table.getModel().getValueAt(setar, 1).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 2).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 5).toString());
		txtBairro.setText(table.getModel().getValueAt(setar, 6).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar, 7).toString());
		txtFoneCli.setText(table.getModel().getValueAt(setar, 8).toString());
		txtEmailCli.setText(table.getModel().getValueAt(setar, 9).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 10).toString());

		// Geren Btn
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	private void limpar()

	{
		txtPesquisar.setText(null);
		txtIdCli.setText(null);
		txtNomeCli.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		cboUf.setSelectedItem(null);
		txtFoneCli.setText(null);
		txtEmailCli.setText(null);
		txtCidade.setText(null);

		// Limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// geren btn
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

}
