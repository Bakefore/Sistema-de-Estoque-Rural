package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.HistoricoAnimal;
import model.HistoricoFuncionario;

public class HistoricoAnimalDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public HistoricoAnimalDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(HistoricoAnimal historicoAnimal){
		entityManager.getTransaction().begin();
		entityManager.merge(historicoAnimal);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public List<HistoricoAnimal> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, HistoricoAnimal.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
}
