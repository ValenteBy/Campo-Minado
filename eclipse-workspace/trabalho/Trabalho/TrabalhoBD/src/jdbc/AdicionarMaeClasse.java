package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdicionarMaeClasse {

	public static String adicionarMae() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Informe o nome da mãe: ");
		String nome = entrada.next();
		
		Connection conexao = FabricaConexao.getConexao();
		
		String sql = "INSERT INTO mae (nome) VALUES (?)";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.execute();
		
		System.out.println("Mãe colocada na tabela com sucesso!");
		//entrada.close();
		
		return null;
	}
	
	public static void corrigirNome() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Informe o nome corrigido: ");
		String nome = entrada.nextLine();
		
		System.out.println("Informe o código da mãe que deseja corrigir: ");
		int cod = entrada.nextInt();
		
		Connection conexao = FabricaConexao.getConexao();
		
		String sql = "UPDATE mae set nome = ? where codigo = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		stmt.setInt(2, cod);
		stmt.execute();
		
		System.out.println("Nome atualizado!!");
		//entrada.close();
		
	}
	
	public static void pesquisarFilhos() throws SQLException {
		Scanner entrada = new Scanner(System.in);
		
		Connection conexao = FabricaConexao.getConexao();
		String sql = "SELECT * FROM filhos WHERE nome like ?";
		
		System.out.println("Informe o nome para pesquisar: ");
		String valor = entrada.nextLine();
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, "%" + valor + "%");
		ResultSet resultado = stmt.executeQuery();
		
		List<Filho> filhos = new ArrayList<>();
		
		while(resultado.next()) {
			int codigo = resultado.getInt("cod");
			String nome = resultado.getString("nome");
			int mae = resultado.getInt("mae_cod");
			filhos.add(new Filho(codigo, nome, mae));
		}
		
		for(Filho f: filhos) {
			System.out.println(f.getCodigo() + "===>" + f.getNome());
		}
		stmt.close();
		conexao.close();
		//entrada.close();
	}
}
