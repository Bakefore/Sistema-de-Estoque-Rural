package business;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import app.App;
import dao.FuncionarioDAO;
import model.CPFJaCadastradoException;
import model.CPFinvalidoException;
import model.CampoVazioException;
import model.Endereco;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import model.LoginJaCadastradoException;
import model.SenhasDivergentesException;
import model.TamanhoDaSenhaException;
import model.ValidaCPF;

public class FuncionarioBusiness {
	
	private FuncionarioDAO funcionarioDAO;
	
	public FuncionarioBusiness() {
		funcionarioDAO = new FuncionarioDAO();
	}	
	
	public void cadastrar(Funcionario funcionario, String confirmarSenha) throws SenhasDivergentesException, CampoVazioException, CPFinvalidoException, TamanhoDaSenhaException, NoSuchAlgorithmException, UnsupportedEncodingException, CPFJaCadastradoException, LoginJaCadastradoException{
		if(validar(funcionario, confirmarSenha)){				
			funcionarioDAO.cadastrar(funcionario);
		}		
	}
	
	public Funcionario realizarLogin(String login, String senha) throws FuncionarioNaoEncontradoException{		
		try {
			return funcionarioDAO.realizarLogin(login, senha);
		} catch (Exception e) {
			throw new FuncionarioNaoEncontradoException("Login e/ou senha incorreto(s)!");
		}
	}
	
	public List<Funcionario> pesquisarPersonalizado(String query, String parametro) throws FuncionarioNaoEncontradoException{
		try {
			return funcionarioDAO.pesquisarPersonalizado(query, parametro);
		} catch (Exception e) {
			throw new FuncionarioNaoEncontradoException("Não foram encontrados funcionários com o filtro inserido!");
		}
	}
	
	public void alterarSenha(String senha, String confirmarSenha) throws SenhasDivergentesException, TamanhoDaSenhaException, NoSuchAlgorithmException, UnsupportedEncodingException{
		if(validarSenha(senha, confirmarSenha)){
			funcionarioDAO.alterarSenha(senha);
			App.funcionarioLogado.setSenha(senha);
		}	
	}
	
	public void resetarSenha(int id) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		funcionarioDAO.resetarSenha(id);
	}
	
	public void editarFuncionario(Funcionario funcionario, String confirmarSenha) throws NoSuchAlgorithmException, UnsupportedEncodingException, SenhasDivergentesException, CampoVazioException, CPFinvalidoException, TamanhoDaSenhaException, CPFJaCadastradoException, LoginJaCadastradoException{
		if(validar(funcionario, confirmarSenha)){		
			funcionarioDAO.editarFuncionario(funcionario);
		}
	}
	
	public void desativar(int id){
		funcionarioDAO.desativar(id);
	}
	
	public boolean validar(Funcionario funcionario, String confirmarSenha) throws SenhasDivergentesException, CampoVazioException, CPFinvalidoException, TamanhoDaSenhaException, LoginJaCadastradoException, CPFJaCadastradoException{
		if(funcionario.getNome().equalsIgnoreCase("") || funcionario.getCpf().equalsIgnoreCase("") ||
				funcionario.getData_nascimento() == null|| funcionario.getCelular().equalsIgnoreCase("") ||
				funcionario.getLogin().equalsIgnoreCase("") || funcionario.getSenha().equalsIgnoreCase("")){
			throw new CampoVazioException("Preencha todos os campos obrigatórios!");
		}
		
		if(!ValidaCPF.isCPF(funcionario.getCpf())){
			throw new CPFinvalidoException("O CPF inserido não existe!");
		}		
		
		if(verificarCPF(funcionario)){
			throw new CPFJaCadastradoException("O CPF inserido já está cadastrado!");
		}
		if(verificarLogin(funcionario)){
			throw new LoginJaCadastradoException("O Login inserido já está cadastrado!");
		}
		
		validarSenha(funcionario.getSenha(), confirmarSenha);	
		
		return true;
	}
	
	public boolean verificarCPF(Funcionario funcionario){
		//Caso o funcionário não tenha sido encontrado, retorna nulo
		try {
			if(funcionarioDAO.pesquisarPorCPF(funcionario) == null){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean verificarLogin(Funcionario funcionario){
		//Caso o funcionário não tenha sido encontrado, retorna nulo
		try {
			if(funcionarioDAO.pesquisarPorLogin(funcionario) == null){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean validarSenha(String senha, String confirmarSenha) throws SenhasDivergentesException, TamanhoDaSenhaException{
				
		if(senha.length() > 11){
			throw new TamanhoDaSenhaException("A senha deve ter no máximo 11 caracteres!");
		}
		
		if(senha.length() < 6){
			throw new TamanhoDaSenhaException("A senha deve ter no mínimo 6 caracteres!");
		}
		
		if(!senha.equals(confirmarSenha)){
			throw new SenhasDivergentesException("As Senhas Precisam Ser Iguais!");
		}
		
		return true;
	}
}
