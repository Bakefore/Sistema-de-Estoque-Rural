package business;

import java.util.List;

import dao.HistoricoPlantacaoDAO;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoAnimal;
import model.HistoricoFuncionario;
import model.HistoricoPlantacao;
import model.PlantacaoNaoEncontradaException;

public class HistoricoPlantacaoBusiness {
	private HistoricoPlantacaoDAO historicoPlantacaoDAO;
	
	public HistoricoPlantacaoBusiness() {
		historicoPlantacaoDAO = new HistoricoPlantacaoDAO();
	}
	
	public void cadastrar(HistoricoPlantacao historicoPlantacao){
		historicoPlantacaoDAO.cadastrar(historicoPlantacao);
	}
	
	public List<HistoricoPlantacao> pesquisarPersonalizado(String query, String parametro) throws PlantacaoNaoEncontradaException{
		try {
			return historicoPlantacaoDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new PlantacaoNaoEncontradaException("Não foram encontrados históricos com o filtro inserido!");
		}
	}
}
