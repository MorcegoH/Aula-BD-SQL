package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

public class Servico extends JDialog {
	private JTextField txtPesquisa;
	private JTable table;

	// Variavel de apoio ao uso do chackbox
	private String tipo;

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
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Pesquisa de Servi\u00E7o");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servico.class.getResource("/img/pc.png")));
		setBounds(100, 100, 824, 568);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(473, 23, 145, 20);
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(628, 11, 32, 32);
		lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/img/pesquisar.png")));
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(473, 57, 282, 127);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 282, 127);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarId();
			}
		});
		scrollPane.setViewportView(table);

		txtId = new JTextField();
		txtId.setBounds(691, 23, 64, 20);
		txtId.setEditable(false);
		txtId.setColumns(10);
		getContentPane().add(txtId);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(670, 26, 20, 14);
		getContentPane().add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 453, 164);
		panel.setBorder(new TitledBorder(null, "O.S", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);

		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(10, 22, 99, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("DATA");
		lblNewLabel_2.setBounds(172, 25, 35, 14);
		panel.add(lblNewLabel_2);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(212, 22, 183, 20);
		panel.add(txtData);

		chkOrcamento = new JCheckBox("Or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		buttonGroup.add(chkOrcamento);
		chkOrcamento.setBounds(10, 75, 97, 23);
		panel.add(chkOrcamento);

		chkServico = new JCheckBox("Servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		buttonGroup.add(chkServico);
		chkServico.setBounds(109, 75, 97, 23);
		panel.add(chkServico);

		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(
				new String[] { "", "bancada", "aguardando aprova\u00E7\u00E3o", "retirado", "em concerto" }));
		cboStatus.setBounds(212, 75, 183, 22);
		panel.add(cboStatus);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(212, 54, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Equipamento:");
		lblNewLabel_4.setBounds(10, 212, 91, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_4);

		txtEquip = new JTextField();
		txtEquip.setBounds(10, 231, 234, 20);
		getContentPane().add(txtEquip);
		txtEquip.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("T\u00E9cnico");
		lblNewLabel_4_1.setBounds(281, 212, 83, 14);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Defeito:");
		lblNewLabel_4_1_1.setBounds(10, 278, 52, 14);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_2 = new JLabel("Valor:");
		lblNewLabel_4_2.setBounds(506, 212, 91, 14);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_4_2);

		txtVal = new JTextField();
		txtVal.setBounds(506, 233, 154, 39);
		txtVal.setColumns(10);
		getContentPane().add(txtVal);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPesquisar.setForeground(Color.WHITE);
		btnPesquisar.setBounds(38, 482, 89, 23);
		btnPesquisar.setBackground(Color.DARK_GRAY);
		getContentPane().add(btnPesquisar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOS();
			}
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBounds(209, 482, 89, 23);
		btnEditar.setBackground(Color.BLUE);
		getContentPane().add(btnEditar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarOs();
			}
		});
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setBounds(506, 482, 89, 23);
		btnAdicionar.setBackground(Color.GREEN);
		getContentPane().add(btnAdicionar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBounds(666, 482, 89, 23);
		btnExcluir.setBackground(Color.RED);
		getContentPane().add(btnExcluir);

		txtDefeito = new JTextArea();
		txtDefeito.setMargin(new Insets(5, 5, 5, 5));
		txtDefeito.setSize(new Dimension(1, 1));
		txtDefeito.setBounds(10, 303, 744, 136);
		getContentPane().add(txtDefeito);

		txtTecnico = new JTextField();
		txtTecnico.setColumns(10);
		txtTecnico.setBounds(281, 231, 166, 20);
		getContentPane().add(txtTecnico);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Servico.class.getResource("/img/print.png")));
		btnNewButton.setBounds(372, 454, 64, 64);
		getContentPane().add(btnNewButton);

	} // Construtor

	DAO dao = new DAO();
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtId;
	private JTextField txtEquip;
	private JTextField txtVal;
	private JTextField txtTecnico;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea txtDefeito;
	private JComboBox cboStatus;
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;

	private void pesquisarCliente() {

		String read = "select idcli as ID, nome as Cliente, fone as Fone from clientes where nome like ?";
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

	private void setarId() {
		int setar = table.getSelectedRow();
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
	}

	/**
	 * Metodo responsavel pela pesquisa OS
	 */

	private void pesquisarOs() {
		// tecnica usada para capturar
		String numOs = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from tbos where os=" + numOs;
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// A linha abaixo, ResultSet, trás a info do banco de dados
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				if (tipo == "Serviço") {
					chkServico.setSelected(true);
					tipo = "Serviço";
				} else {
					chkOrcamento.setSelected(true);
					tipo = "Orçamento";
				}
				txtId.setText(rs.getString(9));
				txtOs.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtEquip.setText(rs.getString(5));
				txtTecnico.setText(rs.getString(7));
				txtVal.setText(rs.getString(8));
				txtDefeito.setText(rs.getString(6));
				cboStatus.setSelectedItem(rs.getString(4).toString());
				txtPesquisa.setEnabled(false);

			} else {
				JOptionPane.showMessageDialog(null, "O.S Não Localizada!!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				limpar();
			}

		} catch (Exception e) {
			System.out.println(e);

		}

	}
	// >>>>>>>>>>>>>>>>> Fim do Pesquisar O.S

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// Editar O.S

	private void editarOS() {

		if (txtEquip.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Equipamento!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtEquip.requestFocus();
		} else if (txtTecnico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Técnico.", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtTecnico.requestFocus();
		} else if (txtVal.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Valor do Serviço.", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtVal.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Descreva o Defeito.", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtDefeito.requestFocus();
		}

		String update = "update tbos set tipo=?,statusos=?,equipamento=?,defeito=?,tecnico=?,valor=? where idcli=?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, tipo);
			pst.setString(2, cboStatus.getSelectedItem().toString());
			pst.setString(3, txtEquip.getText());
			pst.setString(4, txtDefeito.getText());
			pst.setString(5, txtTecnico.getText());
			pst.setString(6, txtVal.getText());
			pst.setString(7, txtId.getText());

			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "O.S Atualizada com Sucesso!!", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
			}
			con.close();
			limpar();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// >>>>>>>>>>ADICIONAR OS <<<<<<<<<<<<<<<<<<<<<
		private void adicionarOs() {
			if (txtEquip.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o Equipamento", "Atenção!!",
						JOptionPane.ERROR_MESSAGE);
				txtEquip.requestFocus();
			//} else if (txtTecnico.getText().isEmpty()) {
				//JOptionPane.showMessageDialog(null, "Informe o Técnico!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				//txtTecnico.requestFocus();
			} else if (txtDefeito.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Descreva o Defeito!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				txtDefeito.requestFocus();
			} else if (cboStatus.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(null, "Selecione o tipo de O.S!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				cboStatus.requestFocus();

			} else {
				String create = "insert  into tbos (tipo,statusos,equipamento,defeito,idcli) values (?,?,?,?,?);";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(create);
					pst.setString(1, tipo);
					pst.setString(2, cboStatus.getSelectedItem().toString());
					pst.setString(3, txtEquip.getText());
					pst.setString(4, txtDefeito.getText());
					pst.setString(5, txtId.getText());
					int confirma = pst.executeUpdate();
					if (confirma == 1) {
						JOptionPane.showMessageDialog(null, "O.S gerada com Sucesso!!", "Mensagem",
								JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
					limpar();
				}  catch (Exception e) {
					System.out.println(e);
			}
			
		}
		}
		
		//>>>>>>>>>>>>>> EXCLUIR OS <<<<<<<<<<<<<<<<<<<<<<
		private void excluirOs() {
			// Confirmação de Exclusão
			int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desta O.S?", "Atenção!",
					JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				// codigo principal
				String delete = "delete from tbos where os=?";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(delete);
					pst.setString(1, txtOs.getText());
					int excluir = pst.executeUpdate();
					if (excluir == 1) {
						limpar();
						JOptionPane.showMessageDialog(null, "O.S excluída com sucesso!", "Mensagem",
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
	
	// >>>>>>>>>>>>>>LIMPAR CAMPOS<<<<<<<<<<<<<<<<>
		private void limpar() {
			txtEquip.setText(null);
			txtTecnico.setText(null);
			txtVal.setText(null);
			txtData.setText(null);
			txtOs.setText(null);
			txtId.setText(null);
			txtDefeito.setText(null);
			cboStatus.setSelectedItem(null);
			buttonGroup.clearSelection();
			// Limpar a tabela
			((DefaultTableModel) table.getModel()).setRowCount(0);

		}
}
