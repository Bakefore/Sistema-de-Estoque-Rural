package controller;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import app.App;
import business.BairroBusiness;
import business.CidadeBusiness;
import business.EnderecoBusiness;
import business.EstadoBusiness;
import business.FuncionarioBusiness;
import business.HistoricoFuncionarioBusiness;
import model.AssociassaoDeTabelasException;
import model.Bairro;
import model.CPFJaCadastradoException;
import model.CPFinvalidoException;
import model.CampoVazioException;
import model.Cidade;
import model.Endereco;
import model.Estado;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoFuncionario;
import model.LoginJaCadastradoException;
import model.SenhasDivergentesException;
import model.TabelaAbstrata;
import model.TabelaGenerica;
import model.TamanhoDaSenhaException;
import model.ValidaCPF;
import model.ViaCEP;
import model.ViaCEPBuscar;
import view.EditarFuncionario;
import view.Mensagem;
import view.PesquisarFuncionario;

public class ControleEditarFuncionario implements ActionListener{
	
	private EditarFuncionario editarFuncionario;	
	private EnderecoBusiness enderecoBusiness;
	private EstadoBusiness estadoBusiness;
	private CidadeBusiness cidadeBusiness;
	private BairroBusiness bairroBusiness;
	private Funcionario funcionario;
	private FuncionarioBusiness funcionarioBusiness;
	private boolean clicouNoCEP;
	private HistoricoFuncionarioBusiness historicoFuncionarioBusiness;
	private HistoricoFuncionario historicoFuncionario;
		
	public ControleEditarFuncionario(EditarFuncionario editarFuncionario) {
		this.editarFuncionario = editarFuncionario;
		clicouNoCEP = false;
		
		//Instancia os business para fazer a edição do funcionário
		funcionarioBusiness = new FuncionarioBusiness();
		estadoBusiness = new EstadoBusiness();
		cidadeBusiness = new CidadeBusiness();
		bairroBusiness = new BairroBusiness();
		enderecoBusiness = new EnderecoBusiness();
		historicoFuncionarioBusiness = new HistoricoFuncionarioBusiness();
		
		editarFuncionario.getEditarFuncionarioButton().addActionListener(this);
		setarControleTecladoParaCEP();
	}
	
	public void actionPerformed(ActionEvent e) {
		try {			
			//chama os business para cadastrar o endereço do funcionário
			Estado estado = new Estado(editarFuncionario.getEstadoComboBox().getSelectedItem().toString());			
			Cidade cidade = new Cidade(editarFuncionario.getCidadeField().getText(), estadoBusiness.cadastrar(estado));
			Bairro bairro = new Bairro(editarFuncionario.getBairroField().getText(), cidadeBusiness.cadastrar(cidade));
			Endereco endereco = new Endereco(editarFuncionario.getEnderecoRuaField().getText(), Integer.parseInt(editarFuncionario.getEnderecoNumeroField().getText()), 
					editarFuncionario.getComplementoEnderecoField().getText(), bairroBusiness.cadastrar(bairro));
						
			//instancia o funcionário com os dados do formulário		
			//A senha é passada dessa forma, para que o validador não barre a senha, mas ela não será alterada
			funcionario = new Funcionario(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(editarFuncionario.getFuncionario().getData_cadastro())), 
				editarFuncionario.getTipoFuncionarioComboBox().getSelectedItem().toString(), 
				editarFuncionario.getMasculinoRadioButton().isSelected()?"Masculino":"Feminino", editarFuncionario.getNomeField().getText(), 
				editarFuncionario.getEmailField().getText(), editarFuncionario.getCpfField().getText(), 
				new SimpleDateFormat("dd/MM/yyyy").parse(editarFuncionario.getNascimentoField().getText()), editarFuncionario.getTelefone1Field().getText(), 
				editarFuncionario.getCelularField().getText(), editarFuncionario.getLoginField().getText(), 
				"12345678", enderecoBusiness.cadastrar(endereco));
			funcionario.setId(editarFuncionario.getFuncionario().getId());
			
			funcionarioBusiness.editarFuncionario(funcionario, "12345678");
			
			historicoFuncionario = new HistoricoFuncionario(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Editou", App.funcionarioLogado, funcionario);
			historicoFuncionarioBusiness.cadastrar(historicoFuncionario);
						
			Mensagem mensagem = new Mensagem("O Funcionário foi editado com Sucesso!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		} catch (CampoVazioException e2) {
			Mensagem mensagem = new Mensagem(e2.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		} catch (CPFinvalidoException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (ParseException e1) {
			Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (SenhasDivergentesException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (AssociassaoDeTabelasException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (TamanhoDaSenhaException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CPFJaCadastradoException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (LoginJaCadastradoException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (NumberFormatException e2) {
			Mensagem mensagem = new Mensagem("Insira um valor inteiro no campo Número!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}
	}
	
	public void setarControleTecladoParaCEP(){
		//Inicia a verificação do CEP após clicar em ENTER ou TAB
		editarFuncionario.getCepField().addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				editarFuncionario.getCepField().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					verificarCep();
				}
				if(e.getKeyCode() == KeyEvent.VK_TAB){
					verificarCep();
				}
			}
		});
		//Inicia a verificação do CEP após ter tirado o mouse do campo de CEP
		editarFuncionario.getCepField().addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				if(clicouNoCEP == true){
					verificarCep();					
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				clicouNoCEP = true;
			}
		});
	}
	
	//Faz a verificação do CEP que foi informado no campo
	public void verificarCep(){
		try {
			clicouNoCEP = false;
			ViaCEP viaCEP = new ViaCEPBuscar().run(editarFuncionario.getCepField().getText());
			editarFuncionario.getCidadeField().setText(viaCEP.getLocalidade());
			editarFuncionario.getBairroField().setText(viaCEP.getBairro());
			editarFuncionario.getEnderecoRuaField().setText(viaCEP.getLogradouro());
			editarFuncionario.getEstadoComboBox().setSelectedItem(viaCEP.getUf());
		} catch (ArrayIndexOutOfBoundsException e2) {
			Mensagem mensagem = new Mensagem("Não foi possível encontrar o CEP: "+editarFuncionario.getCepField().getText()
					+ ". Certifique-se se o CEP inserido está correto.", 700);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		}
	}	
	
	public void editarTabela(PesquisarFuncionario pesquisarF){	
		String colunas[] = {"Nome", "CPF", "Data de Nascimento", "Sexo"};		
		ArrayList listaFuncionarios = new ArrayList();
		
		DateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada;
		
		for(int i = 0; i < pesquisarF.getFuncionarios().size(); i++){
			dataFormatada = formatoBrasileiro.format(pesquisarF.getFuncionarios().get(i).getData_nascimento());
			listaFuncionarios.add(new Object[]{pesquisarF.getFuncionarios().get(i).getNome(), pesquisarF.getFuncionarios().get(i).getCpf(), dataFormatada, pesquisarF.getFuncionarios().get(i).getSexo(), pesquisarF.getFuncionarios().get(i).getId()});
		}		
		
		pesquisarF.getResultadoBuscaTable().setModel(new TabelaAbstrata(listaFuncionarios, colunas));
	}

}
