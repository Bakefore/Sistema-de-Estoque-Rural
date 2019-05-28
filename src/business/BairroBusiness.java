package business;

import dao.BairroDAO;
import model.AssociassaoDeTabelasException;
import model.Bairro;
import model.CampoVazioException;

public class BairroBusiness {
	private BairroDAO bairroDAO;
	
	public BairroBusiness() {
		bairroDAO = new BairroDAO();
	}
	
	public Bairro cadastrar(Bairro bairro) throws CampoVazioException, AssociassaoDeTabelasException{
		//Verifica se o Bairro já esxiste no banco de dados
		if(verificarBancoDeDados(bairro) == null){
			if(validar(bairro)){
				return bairroDAO.cadastrar(bairro);
			}
			return new Bairro();
		}
		return verificarBancoDeDados(bairro);
	}
	
	public boolean validar(Bairro bairro) throws CampoVazioException, AssociassaoDeTabelasException{
		if(bairro.getNome().equals("")){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		
		if(bairro.getCidade().getId() == 0){
			throw new AssociassaoDeTabelasException("Erro ao passar ID de Cidade!");
		}
		return true;
	}
	
	public Bairro verificarBancoDeDados(Bairro bairro){
		//Caso o Bairro não tenha sido encontrado, retorna nulo
		try {
			return bairroDAO.pesquisar(bairro);
		} catch (Exception e) {
			return null;
		}
	}
}
