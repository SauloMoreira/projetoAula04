package Interfaces;

import java.util.List;

import entities.Funcionario;

public interface IFuncionarioRepository {
	
	void create(Funcionario funcionario)throws Exception;
	void update(Funcionario funcionario)throws Exception;
	void delete(Funcionario funcionario)throws Exception;
	List<Funcionario> findall() throws Exception; 
	Funcionario findById(Integer idFuncionario)throws Exception;
}
