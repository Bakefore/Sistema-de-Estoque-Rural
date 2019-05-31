package business;

import java.util.List;

import dao.HistoricoFuncionarioDAO;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoFuncionario;

public class HistoricoFuncionarioBusiness {
	private HistoricoFuncionarioDAO historicoFuncionarioDAO;
	
	public HistoricoFuncionarioBusiness() {
		historicoFuncionarioDAO = new HistoricoFuncionarioDAO();
	}
	
	public void cadastrar(HistoricoFuncionario historicoFuncionario){
		historicoFuncionarioDAO.cadastrar(historicoFuncionario);
	}
	
	public List<HistoricoFuncionario> pesquisarPersonalizado(String query, String parametro) throws FuncionarioNaoEncontradoException{
		try {
			return historicoFuncionarioDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new FuncionarioNaoEncontradoException("Não foram encontrados históricos com o filtro inserido!");
		}
	}
}
