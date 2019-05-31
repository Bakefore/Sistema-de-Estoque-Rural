package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.HistoricoFuncionario;
import model.HistoricoPlantacao;

public class HistoricoPlantacaoDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public HistoricoPlantacaoDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(HistoricoPlantacao historicoPlantacao){
		entityManager.getTransaction().begin();
		entityManager.merge(historicoPlantacao);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public List<HistoricoPlantacao> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, HistoricoPlantacao.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
}
