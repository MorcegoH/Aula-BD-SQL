package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão com o Banco de Dados
 * 
 * @author Aluno Heder Santos
 * @version 1.0
 */
public class DAO {

	// parametros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.110:3306/loja";
	private String user = "dba";
	private String password = "123@Senac";

	/**
	 * Metodo responsavel pela conexao com o banco
	 * 
	 * @return con
	 */
	public Connection conectar() {
		// A linha abaixo cria um objeto de nome con
		Connection con = null;
		// Tratamento de exceções
		try {
			// as duas linhas abaixo estabelecem da conexão
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
