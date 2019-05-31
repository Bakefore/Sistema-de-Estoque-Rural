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
import java.util.Collections;
import java.util.Date;

import app.App;
import business.BairroBusiness;
import business.CidadeBusiness;
import business.EnderecoBusiness;
import business.EstadoBusiness;
import business.FuncionarioBusiness;
import business.HistoricoFuncionarioBusiness;
import dao.FuncionarioDAO;
import javafx.util.converter.DateTimeStringConverter;
import jdk.nashorn.internal.parser.DateParser;
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
import model.TamanhoDaSenhaException;
import model.ValidaCPF;
import model.ViaCEP;
import model.ViaCEPBuscar;
import view.CadastrarFuncionario;
import view.Mensagem;

public class ControleCadastrarFuncionario implements ActionListener{
	
	private CadastrarFuncionario cadastrarFuncionario;
	private boolean clicouNoCEP;
	private Funcionario funcionario;
	private FuncionarioBusiness funcionarioBusiness;
	private EstadoBusiness estadoBusiness;
	private CidadeBusiness cidadeBusiness;
	private BairroBusiness bairroBusiness;
	private EnderecoBusiness enderecoBusiness;
	private int idRetorno;
	private HistoricoFuncionarioBusiness historicoFuncionarioBusiness;
	private HistoricoFuncionario historicoFuncionario;
		
	public ControleCadastrarFuncionario(CadastrarFuncionario cadastrarFuncionario) {
		this.cadastrarFuncionario = cadastrarFuncionario;
		
		//Instancia os business para fazer o cadastro do funcionário
		funcionarioBusiness = new FuncionarioBusiness();
		estadoBusiness = new EstadoBusiness();
		cidadeBusiness = new CidadeBusiness();
		bairroBusiness = new BairroBusiness();
		enderecoBusiness = new EnderecoBusiness();
		historicoFuncionarioBusiness = new HistoricoFuncionarioBusiness();
		
		clicouNoCEP = false;
		
		cadastrarFuncionario.getCadastrarFuncionarioButton().addActionListener(this);
		setarControleTecladoParaCEP();
	}
	
	public void actionPerformed(ActionEvent e) {
		try {			
			//chama os business para cadastrar o endereço do funcionário
			Estado estado = new Estado(cadastrarFuncionario.getEstadoComboBox().getSelectedItem().toString());			
			Cidade cidade = new Cidade(cadastrarFuncionario.getCidadeField().getText(), estadoBusiness.cadastrar(estado));
			Bairro bairro = new Bairro(cadastrarFuncionario.getBairroField().getText(), cidadeBusiness.cadastrar(cidade));
			Endereco endereco = new Endereco(cadastrarFuncionario.getEnderecoRuaField().getText(), Integer.parseInt(cadastrarFuncionario.getEnderecoNumeroField().getText()), 
				cadastrarFuncionario.getComplementoEnderecoField().getText(), bairroBusiness.cadastrar(bairro));
						
			//instancia o funcionário com os dados do formulário			
			funcionario = new Funcionario(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), 
				cadastrarFuncionario.getTipoFuncionarioComboBox().getSelectedItem().toString(), 
				cadastrarFuncionario.getMasculinoRadioButton().isSelected()?"Masculino":"Feminino", cadastrarFuncionario.getNomeField().getText(), 
				cadastrarFuncionario.getEmailField().getText(), cadastrarFuncionario.getCpfField().getText(), 
				new SimpleDateFormat("dd/MM/yyyy").parse(cadastrarFuncionario.getNascimentoField().getText()), cadastrarFuncionario.getTelefone1Field().getText(), 
				cadastrarFuncionario.getCelularField().getText(), cadastrarFuncionario.getLoginField().getText(), 
				new String(cadastrarFuncionario.getSenhaField().getPassword()), enderecoBusiness.cadastrar(endereco));
			
			funcionarioBusiness.cadastrar(funcionario, cadastrarFuncionario.getConfirmarSenhaField().getText());
			
			funcionario = funcionarioBusiness.pesquisarPersonalizado("select * from funcionario where cpf = :PARAMETRO and ativo = true", funcionario.getCpf()).get(0);
			historicoFuncionario = new HistoricoFuncionario(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Cadastrou", App.funcionarioLogado, funcionario);
			historicoFuncionarioBusiness.cadastrar(historicoFuncionario);
			
			Mensagem mensagem = new Mensagem("O Funcionário foi inserido com Sucesso!", 400);
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
		} catch (FuncionarioNaoEncontradoException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}
	}
	
	public void setarControleTecladoParaCEP(){
		//Inicia a verificação do CEP após clicar em ENTER ou TAB
		cadastrarFuncionario.getCepField().addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				cadastrarFuncionario.getCepField().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					verificarCep();
				}
				if(e.getKeyCode() == KeyEvent.VK_TAB){
					verificarCep();
				}
			}
		});
		//Inicia a verificação do CEP após ter tirado o mouse do campo de CEP
		cadastrarFuncionario.getCepField().addMouseListener(new MouseListener() {
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
			ViaCEP viaCEP = new ViaCEPBuscar().run(cadastrarFuncionario.getCepField().getText());
			cadastrarFuncionario.getCidadeField().setText(viaCEP.getLocalidade());
			cadastrarFuncionario.getBairroField().setText(viaCEP.getBairro());
			cadastrarFuncionario.getEnderecoRuaField().setText(viaCEP.getLogradouro());
			cadastrarFuncionario.getEstadoComboBox().setSelectedItem(viaCEP.getUf());
		} catch (ArrayIndexOutOfBoundsException e2) {
			Mensagem mensagem = new Mensagem("Não foi possível encontrar o CEP: "+cadastrarFuncionario.getCepField().getText()
					+ ". Certifique-se se o CEP inserido está correto.", 700);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		}
	}

}
