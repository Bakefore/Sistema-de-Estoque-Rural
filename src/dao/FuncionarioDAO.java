package dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import app.App;
import model.CPFJaCadastradoException;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;

public class FuncionarioDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public FuncionarioDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(Funcionario funcionario) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//Cripstografa a senha de funcionário antes de salvar na base de dados
		funcionario.setSenha(Funcionario.criptografar(funcionario.getSenha()));
		entityManager.getTransaction().begin();
		entityManager.merge(funcionario);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public Funcionario realizarLogin(String login, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//Criptografa a senha para comparar com a salva no banco de dados
		senha = Funcionario.criptografar(senha);
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from funcionario where login = :LOGIN and senha = :SENHA and ativo = true", Funcionario.class);
		consulta.setParameter("LOGIN", login);
		consulta.setParameter("SENHA", senha);
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Funcionario) consulta.getSingleResult();
	}
	
	public Funcionario pesquisarPorCPF(Funcionario funcionario){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from funcionario where cpf = :CPF and ativo = true", Funcionario.class);
		consulta.setParameter("CPF", funcionario.getCpf());		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		Funcionario funcionarioEncontrado = (Funcionario) consulta.getSingleResult();
		
		if(funcionarioEncontrado.getId() == funcionario.getId()){
			return null;
		}		
		return funcionarioEncontrado;
	}
	
	public Funcionario pesquisarPorLogin(Funcionario funcionario){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from funcionario where login = :LOGIN and ativo = true", Funcionario.class);
		consulta.setParameter("LOGIN", funcionario.getLogin());		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		Funcionario funcionarioEncontrado = (Funcionario) consulta.getSingleResult();
		
		if(funcionarioEncontrado.getId() == funcionario.getId()){
			return null;
		}		
		return funcionarioEncontrado;
	}
	
	public List<Funcionario> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, Funcionario.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
	
	public void alterarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		senha = Funcionario.criptografar(senha);
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update funcionario set senha = :SENHA where id = :ID", Funcionario.class);
		consulta.setParameter("SENHA", senha);
		consulta.setParameter("ID", App.funcionarioLogado.getId());
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public void resetarSenha(int id) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String senha = Funcionario.criptografar("123456");
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update funcionario set senha = :SENHA where id = :ID", Funcionario.class);
		consulta.setParameter("SENHA", senha);
		consulta.setParameter("ID", id);
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}	
	
	public void editarFuncionario(Funcionario funcionario) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//Cripstografa a senha de funcionário antes de salvar na base de dados
		funcionario.setSenha(Funcionario.criptografar(funcionario.getSenha()));
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update funcionario set celular = :CELULAR, "
			+ "cpf = :CPF, data_nascimento = :DATA_NASCIMENTO, email = :EMAIL, login = :LOGIN,"
			+ "nome = :NOME, sexo = :SEXO, telefone = :TELEFONE, tipo_funcionario = :TIPO_FUNCIONARIO,"
			+ "endereco_id = :ENDERECO_ID where id = :ID", Funcionario.class);
		consulta.setParameter("ID", funcionario.getId());
		consulta.setParameter("CELULAR", funcionario.getCelular());
		consulta.setParameter("CPF", funcionario.getCpf());
		consulta.setParameter("DATA_NASCIMENTO", funcionario.getData_nascimento());
		consulta.setParameter("EMAIL", funcionario.getEmail());
		consulta.setParameter("LOGIN", funcionario.getLogin());
		consulta.setParameter("NOME", funcionario.getNome());
		consulta.setParameter("SEXO", funcionario.getSexo());
		consulta.setParameter("TELEFONE", funcionario.getTelefone());
		consulta.setParameter("TIPO_FUNCIONARIO", funcionario.getTipo_funcionario());
		consulta.setParameter("ENDERECO_ID", funcionario.getEndereco().getId());		
		
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public void desativar(int id){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update funcionario set ativo = :ATIVO where id = :ID", Funcionario.class);
		consulta.setParameter("ATIVO", false);
		consulta.setParameter("ID", id);
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
}
