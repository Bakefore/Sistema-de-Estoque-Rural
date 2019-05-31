package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Cidade;

public class CidadeDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public CidadeDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public Cidade cadastrar(Cidade cidade){
		entityManager.getTransaction().begin();
		cidade = entityManager.merge(cidade);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return cidade;
	}
	
	public Cidade pesquisar(Cidade cidade){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from cidade where nome = :NOME and estado_id = :ESTADO", Cidade.class);
		consulta.setParameter("NOME", cidade.getNome());
		consulta.setParameter("ESTADO", cidade.getEstado().getId());
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Cidade) consulta.getSingleResult();
	}
}
