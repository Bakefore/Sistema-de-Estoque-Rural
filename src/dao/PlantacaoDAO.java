package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Animal;
import model.Plantacao;

public class PlantacaoDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public PlantacaoDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistema_de_estoque_para_produtor_rural");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void cadastrar(Plantacao plantacao){
		entityManager.getTransaction().begin();
		entityManager.merge(plantacao);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public List<Plantacao> pesquisarPersonalizado(String query, String parametro){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery(query, Plantacao.class);
		consulta.setParameter("PARAMETRO", parametro);		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return consulta.getResultList();
	}
	
	public Plantacao pesquisarUltimoInserido(){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("select * from plantacao where id in (select max(id) from plantacao)", Plantacao.class);	
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
		
		return (Plantacao)consulta.getSingleResult();
	}
	
	public void editarPlantacao(Plantacao plantacao){		
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update plantacao set area = :AREA, cultura = :CULTURA, "
			+ "data_inicio = :DATA_INICIO, defensivo_agricola = :DEFENSIVO_AGRICOLA, porte = :PORTE, "
			+ "previsao_colheita = :PREVISAO_COLHEITA, sistema_de_adubacao = :SISTEMA_DE_ADUBACAO, "
			+ "sistema_de_irrigacao = :SISTEMA_DE_IRRIGACAO, solo = :SOLO, tempo_de_vida = :TEMPO_DE_VIDA, "
			+ "titularidade_terreno = :TITULARIDADE_TERRENO, variedade = :VARIEDADE where id = :ID", Plantacao.class);
		consulta.setParameter("ID", plantacao.getId());		
		consulta.setParameter("AREA", plantacao.getArea());
		consulta.setParameter("CULTURA", plantacao.getCultura());
		consulta.setParameter("DATA_INICIO", plantacao.getData_inicio());
		consulta.setParameter("DEFENSIVO_AGRICOLA", plantacao.getDefensivo_agricola());
		consulta.setParameter("PORTE", plantacao.getPorte());
		consulta.setParameter("PREVISAO_COLHEITA", plantacao.getPrevisao_colheita());
		consulta.setParameter("SISTEMA_DE_ADUBACAO", plantacao.getSistema_de_adubacao());
		consulta.setParameter("SISTEMA_DE_IRRIGACAO", plantacao.getSistema_de_irrigacao());
		consulta.setParameter("SOLO", plantacao.getSolo());
		consulta.setParameter("TEMPO_DE_VIDA", plantacao.getTempo_de_vida());
		consulta.setParameter("TITULARIDADE_TERRENO", plantacao.getTitularidade_terreno());
		consulta.setParameter("VARIEDADE", plantacao.getVariedade());
		
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
	
	public void desativar(int id){
		entityManager.getTransaction().begin();
		
		Query consulta = entityManager.createNativeQuery("update plantacao set ativo = :ATIVO where id = :ID", Plantacao.class);
		consulta.setParameter("ATIVO", false);
		consulta.setParameter("ID", id);
		consulta.executeUpdate();		
		
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
}
