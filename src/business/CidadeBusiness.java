package business;

import dao.CidadeDAO;
import model.AssociassaoDeTabelasException;
import model.CampoVazioException;
import model.Cidade;
import model.Estado;

public class CidadeBusiness {
	private CidadeDAO cidadeDAO;
	
	public CidadeBusiness() {
		cidadeDAO = new CidadeDAO();
	}
	
	public Cidade cadastrar(Cidade cidade) throws CampoVazioException, AssociassaoDeTabelasException{
		//Verifica se a Cidade já esxiste no banco de dados
		if(verificarBancoDeDados(cidade) == null){
			if(validar(cidade)){
				return cidadeDAO.cadastrar(cidade);
			}
			return new Cidade();
		}
		return verificarBancoDeDados(cidade);
	}
	
	public boolean validar(Cidade cidade) throws CampoVazioException, AssociassaoDeTabelasException{
		if(cidade.getNome().equals("")){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		
		if(cidade.getEstado().getId() == 0){
			throw new AssociassaoDeTabelasException("Erro ao passar ID de Estado!");
		}
		return true;
	}
	
	public Cidade verificarBancoDeDados(Cidade cidade){
		//Caso a Cidade não tenha sido encontrado, retorna nulo
		try {
			return cidadeDAO.pesquisar(cidade);
		} catch (Exception e) {
			return null;
		}
	}
}
