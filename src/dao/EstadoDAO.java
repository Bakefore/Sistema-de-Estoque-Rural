package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Estado;
import model.Funcionario;

public class EstadoDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EstadoDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public Estado cadastrar(Estado estado){
		entityManager.getTransaction().begin();
		estado = entityManager.merge(estado);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return estado;
	}
	
	public Estado pesquisar(Estado estado){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from estado where uf = :UF", Estado.class);
		consulta.setParameter("UF", estado.getUf());
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Estado) consulta.getSingleResult();
	}
}
