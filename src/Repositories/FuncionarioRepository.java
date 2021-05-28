package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IFuncionarioRepository;
import entities.Funcionario;

public class FuncionarioRepository implements IFuncionarioRepository {
	private Connection connection;



	public FuncionarioRepository(Connection connection) {
		
		this.connection = connection;
	}

	@Override
	public void create(Funcionario funcionario) throws Exception {

		String sql = "insert into funcionario (nome,cpf,matricula,salario)" 
		              + "values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1,funcionario.getNome());
		statement.setString(2,funcionario.getCPF());
		statement.setString(3,funcionario.getMatricula());
		statement.setDouble(4,funcionario.getSalario());
		
		statement.execute();
		// lendo a chave gerada no banco depois de executar
		ResultSet result = statement.getGeneratedKeys();
		if (result.next()) {
			funcionario.setIdFuncionario(result.getInt(1));//posição da coluna
		}
		statement.close();
	}

	@Override
	public void update(Funcionario funcionario) throws Exception {

		String sql = "update funcionario set nome = ?, cpf = ?, matricula = ?, salario = ? "
				   + "where idfuncionario = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, funcionario.getNome());
		statement.setString(2, funcionario.getCPF());
		statement.setString(3, funcionario.getMatricula());
		statement.setDouble(4, funcionario.getSalario());
		statement.setInt(5, funcionario.getIdFuncionario());
		
		statement.execute();
		statement.close();

	}

	@Override
	public void delete(Funcionario funcionario) throws Exception {
	String sql = "delete from funcionario where idfuncionario = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setDouble(1, funcionario.getIdFuncionario());
		
		statement.execute();
		statement.close();

	}

	@Override
	public List<Funcionario> findall() throws Exception {
	String sql = "select * from funcionario";
		
		PreparedStatement statement = connection.prepareStatement(sql);		
		ResultSet result = statement.executeQuery();

		List<Funcionario> lista = new ArrayList<Funcionario>();
		
		while(result.next()) {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setIdFuncionario(result.getInt("idfuncionario"));
			funcionario.setNome(result.getString("nome"));
			funcionario.setCPF(result.getString("cpf"));
			funcionario.setMatricula(result.getString("matricula"));
			funcionario.setSalario(result.getDouble("salario"));
			
			lista.add(funcionario);
		}
		
		return lista;
	}
	@Override
	public Funcionario findById(Integer idFuncionario) throws Exception {

		String sql = "select * from funcionario where idfuncionario = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idFuncionario);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(result.getInt("idfuncionario"));
			funcionario.setNome(result.getString("nome"));
			funcionario.setCPF(result.getString("cpf"));
			funcionario.setMatricula(result.getString("matricula"));
			funcionario.setSalario(result.getDouble("salario"));
			return funcionario;
		}
		
		
		return null;
	}

}
