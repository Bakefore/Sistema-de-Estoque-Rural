package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Animal;
import model.Funcionario;
import model.HistoricoFuncionario;

public class HistoricoFuncionarioDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public HistoricoFuncionarioDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(HistoricoFuncionario historicoFuncionario){
		entityManager.getTransaction().begin();
		entityManager.merge(historicoFuncionario);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public List<HistoricoFuncionario> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, HistoricoFuncionario.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
}
