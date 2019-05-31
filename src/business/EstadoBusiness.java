package business;

import dao.EstadoDAO;
import model.CampoVazioException;
import model.Estado;
import model.EstadoJaExistenteException;

public class EstadoBusiness {
	private EstadoDAO estadoDAO;
	
	public EstadoBusiness() {
		estadoDAO = new EstadoDAO();
	}
	
	public Estado cadastrar(Estado estado) throws CampoVazioException{	
		//Verifica se o Estado já esxiste no banco de dados
		if(verificarBancoDeDados(estado) == null){
			if(validarCampos(estado)){
				return estadoDAO.cadastrar(estado);
			}
			return new Estado();			
		}
		return verificarBancoDeDados(estado);
	}
	
	public boolean validarCampos(Estado estado) throws CampoVazioException{		
		if(estado.getUf().equals("")){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}		
		
		return true;
	}
	
	public Estado verificarBancoDeDados(Estado estado){
		//Caso o Estado não tenha sido encontrado, retorna nulo
		try {
			return estadoDAO.pesquisar(estado);
		} catch (Exception e) {
			return null;
		}
	}
}
