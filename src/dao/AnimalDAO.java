package dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Animal;
import model.Cidade;
import model.Funcionario;

public class AnimalDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public AnimalDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(Animal animal){
		entityManager.getTransaction().begin();
		entityManager.merge(animal);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public List<Animal> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, Animal.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
	
	public Animal pesquisarUltimoInserido(){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from animal where id in (select max(id) from animal)", Animal.class);	
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Animal)consulta.getSingleResult();
	}
	
	public void editarAnimal(Animal animal){		
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update animal set altura_media = :ALTURA_MEDIA, "
			+ "categoria = :CATEGORIA, data_nascimento = :DATA_NASCIMENTO, finalidade = :FINALIDADE, peso_medio = :PESO_MEDIO,"
			+ "quantidade = :QUANTIDADE, raca_especie = :RACA_ESPECIE, regime_alimentar = :REGIME_ALIMENTAR, sexo = :SEXO"
			+ " where id = :ID", Animal.class);
		consulta.setParameter("ID", animal.getId());
		consulta.setParameter("ALTURA_MEDIA", animal.getAltura_media());
		consulta.setParameter("CATEGORIA", animal.getCategoria());
		consulta.setParameter("DATA_NASCIMENTO", animal.getData_nascimento());
		consulta.setParameter("FINALIDADE", animal.getFinalidade());
		consulta.setParameter("PESO_MEDIO", animal.getPeso_medio());
		consulta.setParameter("QUANTIDADE", animal.getQuantidade());
		consulta.setParameter("RACA_ESPECIE", animal.getRaca_especie());
		consulta.setParameter("REGIME_ALIMENTAR", animal.getRegime_alimentar());
		consulta.setParameter("SEXO", animal.getSexo());		
		
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public void desativar(int id){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update animal set ativo = :ATIVO where id = :ID", Animal.class);
		consulta.setParameter("ATIVO", false);
		consulta.setParameter("ID", id);
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
}
