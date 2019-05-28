package business;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Query;

import dao.AnimalDAO;
import model.Animal;
import model.AnimalNaoEncontradoException;
import model.CPFJaCadastradoException;
import model.CPFinvalidoException;
import model.CampoVazioException;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import model.LoginJaCadastradoException;
import model.SenhasDivergentesException;
import model.TamanhoDaSenhaException;

public class AnimalBusiness {
	private AnimalDAO animalDAO;
	
	public AnimalBusiness() {
		animalDAO = new AnimalDAO();
	}
	
	public void cadastrar(Animal animal) throws CampoVazioException{
		if(validar(animal)){
			animalDAO.cadastrar(animal);
		}
	}
	
	public List<Animal> pesquisarPersonalizado(String query, String parametro) throws AnimalNaoEncontradoException{
		try {
			return animalDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new AnimalNaoEncontradoException("Não foram encontrados animais com o filtro inserido!");
		}
	}
	
	public Animal pesquisarUltimoInserido() throws AnimalNaoEncontradoException{
		try {
			return animalDAO.pesquisarUltimoInserido();
		} catch (Exception e) {
			throw new AnimalNaoEncontradoException("Não foram encontrados animais com o filtro inserido!");
		}
	}
	
	public void editarAnimal(Animal animal) throws CampoVazioException{
		if(validar(animal)){		
			animalDAO.editarAnimal(animal);
		}
	}
	
	public void desativar(int id){
		animalDAO.desativar(id);
	}
	
	public boolean validar(Animal animal) throws CampoVazioException{
		if((animal.getCategoria().equals("")) || (animal.getRaca_especie().equals("")) || 
				(animal.getSexo().equals("")) || (animal.getData_nascimento() == null) ||
				(animal.getPeso_medio() == 0) || (animal.getAltura_media() == 0) || 
				(animal.getRegime_alimentar().equals("")) || (animal.getFinalidade().equals("")) ||
				(animal.getQuantidade() == 0)){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		return true;
	}
}
