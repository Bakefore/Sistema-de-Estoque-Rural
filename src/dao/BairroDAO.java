package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Bairro;

public class BairroDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public BairroDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public Bairro cadastrar(Bairro bairro){
		entityManager.getTransaction().begin();
		bairro = entityManager.merge(bairro);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return bairro;
	}
	
	public Bairro pesquisar(Bairro bairro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from bairro where nome = :NOME and cidade_id = :CIDADE", Bairro.class);
		consulta.setParameter("NOME", bairro.getNome());
		consulta.setParameter("CIDADE", bairro.getCidade().getId());
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Bairro) consulta.getSingleResult();
	}
}
