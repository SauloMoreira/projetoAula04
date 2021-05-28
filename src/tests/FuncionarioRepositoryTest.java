package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Interfaces.IFuncionarioRepository;
import entities.Funcionario;
import fatcrories.ConnectionFactory;
import Repositories.*;

class FuncionarioRepositoryTest {
	private IFuncionarioRepository FuncionarioRepository;
	private Funcionario funcionario;

	@BeforeEach
	void setUp() throws Exception {
		funcionario = new Funcionario();
		funcionario.setNome("Saulo Moreira");
		funcionario.setCPF("014.029.027-35");
		funcionario.setMatricula("Coti001-01");
		funcionario.setSalario(1000.0);
		
		ConnectionFactory factory = new ConnectionFactory();
		FuncionarioRepository = new FuncionarioRepository(factory.getConnection());

	}

	@Test
	void testCreate() throws Exception {
			
		FuncionarioRepository.create(funcionario);
		
		
		/*
		 * Criterio de teste
		 * verificar se depois de cadastrado gerou o id no banco
		 */
		
		assertNotNull(funcionario.getIdFuncionario());
		Funcionario registro = FuncionarioRepository.findById(funcionario.getIdFuncionario());
		
		
		// comparando se funcionarios são iguais
		assertEquals(funcionario, registro);
		
		
	}
	@Test
	void testUpdate() throws Exception {
		FuncionarioRepository.create(funcionario);
		funcionario.setNome("Sergio Mendes");
		funcionario.setCPF("234.654.789-00");
		funcionario.setMatricula("COTI-2-21-002");
		funcionario.setSalario(2000.0);
		FuncionarioRepository.update(funcionario);
		
		Funcionario registro = FuncionarioRepository.findById(funcionario.getIdFuncionario());
		
		assertEquals(funcionario, registro);
	}
	@Test
	void testDelete() throws Exception {
		FuncionarioRepository.create(funcionario);
		FuncionarioRepository.delete(funcionario);
		Funcionario registro = FuncionarioRepository.findById(funcionario.getIdFuncionario());
		assertNull(registro);
	}
	@Test
	void testFindAll() throws Exception {
		FuncionarioRepository.create(funcionario);
		List<Funcionario> registros = FuncionarioRepository.findall();
		assertTrue(registros.size()>0);
		
	}
}
