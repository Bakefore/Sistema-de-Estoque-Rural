package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Endereco;

public class EnderecoDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EnderecoDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public Endereco cadastrar(Endereco endereco){
		entityManager.getTransaction().begin();
		endereco = entityManager.merge(endereco);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		return endereco;
	}
	
	public Endereco pesquisar(Endereco endereco){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from endereco where rua = :RUA and numero = :NUMERO and complemento = :COMPLEMENTO and bairro_id = :BAIRRO", Endereco.class);
		consulta.setParameter("RUA", endereco.getRua());
		consulta.setParameter("NUMERO", endereco.getNumero());
		consulta.setParameter("COMPLEMENTO", endereco.getComplemento());
		consulta.setParameter("BAIRRO", endereco.getBairro().getId());
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Endereco) consulta.getSingleResult();
	}
}
