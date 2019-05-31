package business;

import java.util.List;

import dao.PlantacaoDAO;
import model.Animal;
import model.AnimalNaoEncontradoException;
import model.CampoVazioException;
import model.FuncionarioNaoEncontradoException;
import model.Plantacao;
import model.PlantacaoNaoEncontradaException;

public class PlantacaoBusiness {
	private PlantacaoDAO plantacaoDAO;
	
	public PlantacaoBusiness() {
		plantacaoDAO = new PlantacaoDAO();
	}
	
	public void cadastrar(Plantacao plantacao) throws CampoVazioException{
		if(validar(plantacao)){
			plantacaoDAO.cadastrar(plantacao);
		}
	}
	
	public List<Plantacao> pesquisarPersonalizado(String query, String parametro) throws PlantacaoNaoEncontradaException{
		try {
			return plantacaoDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new PlantacaoNaoEncontradaException("Não foi encontrado nenhuma plantação com o filtro inserido!");
		}
	}
	
	public Plantacao pesquisarUltimoInserido() throws PlantacaoNaoEncontradaException{
		try {
			return plantacaoDAO.pesquisarUltimoInserido();
		} catch (Exception e) {
			throw new PlantacaoNaoEncontradaException("Não foi encontrada nenhuma plantação com o filtro inserido!");
		}
	}
	
	public void editarPlantacao(Plantacao plantacao) throws CampoVazioException{
		if(validar(plantacao)){		
			plantacaoDAO.editarPlantacao(plantacao);
		}
	}
	
	public void desativar(int id){
		plantacaoDAO.desativar(id);
	}
	
	public boolean validar(Plantacao plantacao) throws CampoVazioException{
		if((plantacao.getData_inicio() == null) || (plantacao.getPrevisao_colheita() == null) ||
				(plantacao.getCultura().equals("")) || (plantacao.getVariedade().equals("")) ||
				(plantacao.getArea() == 0) || (plantacao.getSolo().equals("")) || 
				(plantacao.getPorte().equals("")) || (plantacao.getTitularidade_terreno().equals("")) ||
				(plantacao.getTempo_de_vida().equals("")) || (plantacao.getSistema_de_irrigacao().equals("")) ||
				(plantacao.getSistema_de_adubacao().equals("")) || (plantacao.getDefensivo_agricola().equals(""))){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		return true;
	}
}
