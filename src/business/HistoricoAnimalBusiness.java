package business;

import java.util.List;

import dao.HistoricoAnimalDAO;
import model.AnimalNaoEncontradoException;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoAnimal;
import model.HistoricoFuncionario;

public class HistoricoAnimalBusiness {
	private HistoricoAnimalDAO historicoAnimalDAO;
	
	public HistoricoAnimalBusiness() {
		historicoAnimalDAO = new HistoricoAnimalDAO();
	}
	
	public void cadastrar(HistoricoAnimal historicoAnimal){
		historicoAnimalDAO.cadastrar(historicoAnimal);
	}
	
	public List<HistoricoAnimal> pesquisarPersonalizado(String query, String parametro) throws AnimalNaoEncontradoException{
		try {
			return historicoAnimalDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new AnimalNaoEncontradoException("Não foram encontrados históricos com o filtro inserido!");
		}
	}
}
