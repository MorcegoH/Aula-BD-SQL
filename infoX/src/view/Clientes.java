package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
	private JTable tabela;

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
		scrollPane.setViewportView(table);
		
		txtIdCli = new JTextField();
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
		lblNewLabel_2_2.setBounds(10, 270, 21, 18);
		getContentPane().add(lblNewLabel_2_2);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(36, 270, 108, 20);
		getContentPane().add(txtCep);
		
		JButton btnCep = new JButton("Pesquisar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(154, 269, 99, 23);
		getContentPane().add(btnCep);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(336, 270, 333, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(36, 310, 68, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(202, 310, 252, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(513, 310, 156, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(59, 341, 194, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(433, 341, 64, 64);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnEditar.setBounds(523, 341, 64, 64);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(607, 341, 64, 64);
		getContentPane().add(btnExcluir);
		
		JLabel lblNewLabel_3 = new JLabel("*Endereço");
		lblNewLabel_3.setBounds(263, 273, 63, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("*Nº");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 313, 21, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Complemento");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(114, 312, 89, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("*Bairro");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1.setBounds(458, 312, 45, 14);
		getContentPane().add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("*Cidade");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1_1.setBounds(10, 343, 46, 14);
		getContentPane().add(lblNewLabel_5_1_1);
		
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUf.setBounds(314, 340, 42, 22);
		getContentPane().add(cboUf);
		
		JLabel lblNewLabel_2_3 = new JLabel("*UF");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(288, 341, 21, 18);
		getContentPane().add(lblNewLabel_2_3);
	} // Fim do construtor
	
	private void buscarCep() {
		String logradouro="";
		String	tipoLogradouro="";
		String resultado=null;
		String cep=txtCep.getText();
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
			        	//	lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked.png")));
			        		
			        	} else {
			        		JOptionPane.showMessageDialog(null, "CEP n�o encontrado!!");
			        	}
			        		
			        	}
			 }
			 }
			 //Setar o campo endereco
			 txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
}
	
	// Pesquisa Clientes >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
		// Criando um objeto de acesso a classe DAO
		DAO dao = new DAO();
		private JTable table;
		
			private void pesquisarCliente() {
				// ? -> parametro
				String read = "select idcli as ID, nome as Cliente, cep as Cep, endereco as Endereço, numero as Nº, complemento as Complemento,\r\n"
						+ " bairro as Bairro, uf as UF, fone as Fone, email as Email from clientes where nome like ?";
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

