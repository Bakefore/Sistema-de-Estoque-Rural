package business;

import dao.EnderecoDAO;
import model.AssociassaoDeTabelasException;
import model.CampoVazioException;
import model.Endereco;

public class EnderecoBusiness {
	private EnderecoDAO enderecoDAO;
	
	public EnderecoBusiness() {
		enderecoDAO = new EnderecoDAO();
	}
	
	public Endereco cadastrar(Endereco endereco) throws CampoVazioException, AssociassaoDeTabelasException{
		//Verifica se o Endereço já esxiste no banco de dados
		if(verificarBancoDeDados(endereco) == null){
			if(validar(endereco)){
				return enderecoDAO.cadastrar(endereco);
			}
			return new Endereco();
		}
		return verificarBancoDeDados(endereco);
	}
	
	public boolean validar(Endereco endereco) throws CampoVazioException, AssociassaoDeTabelasException{
		if(endereco.getRua().equals("")){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		
		if(endereco.getNumero() == 0){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		
		if(endereco.getBairro().getId() == 0){
			throw new AssociassaoDeTabelasException("Erro ao passar ID de Bairro!");
		}
		return true;
	}
	
	public Endereco verificarBancoDeDados(Endereco endereco){
		//Caso o Endereço não tenha sido encontrado, retorna nulo
		try {
			return enderecoDAO.pesquisar(endereco);
		} catch (Exception e) {
			return null;
		}
	}
}
