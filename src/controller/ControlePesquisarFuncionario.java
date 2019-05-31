package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import app.App;
import business.FuncionarioBusiness;
import business.HistoricoFuncionarioBusiness;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoFuncionario;
import model.NenhumaLinhaSelecionadaException;
import model.TabelaAbstrata;
import model.TabelaGenerica;
import view.EditarAnimal;
import view.EditarFuncionario;
import view.ExibirDetalhesAnimal;
import view.ExibirDetalhesFuncionario;
import view.Mensagem;
import view.PesquisarAnimal;
import view.PesquisarFuncionario;

public class ControlePesquisarFuncionario implements ActionListener{
	
	private PesquisarFuncionario pesquisarFuncionario;
	private FuncionarioBusiness funcionarioBusiness;
	private TabelaGenerica tabelaGenerica;
	private HistoricoFuncionarioBusiness historicoFuncionarioBusiness;
	private HistoricoFuncionario historicoFuncionario;
	private Funcionario funcionario;
	
	public ControlePesquisarFuncionario(PesquisarFuncionario pesquisarFuncionario) {
		this.pesquisarFuncionario = pesquisarFuncionario;
				
		funcionarioBusiness = new FuncionarioBusiness();
		historicoFuncionarioBusiness = new HistoricoFuncionarioBusiness();
		String colunas[] = {"Nome", "CPF", "Data de Nascimento", "Sexo"};	
		tabelaGenerica = new TabelaGenerica(colunas);
		
		pesquisarFuncionario.getOpcoesBuscaComboBox().addActionListener(this);
		pesquisarFuncionario.getEditarFuncionarioButton().addActionListener(this);
		pesquisarFuncionario.getExibirDetalhesFuncionarioButton().addActionListener(this);
		pesquisarFuncionario.getBuscarButton().addActionListener(this);
		pesquisarFuncionario.getResetarSenhaButton().addActionListener(this);
		pesquisarFuncionario.getDesativarButton().addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pesquisarFuncionario.getOpcoesBuscaComboBox()){	
			//Muda as máscaras do campo de busca de acordo com a opção selecionada
			pesquisarFuncionario.getBuscaField().setValue(null);
			
			if(pesquisarFuncionario.getOpcoesBuscaComboBox().getSelectedItem().toString().equalsIgnoreCase("cpf")){
				try {
					pesquisarFuncionario.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
				} catch (ParseException e1) {
					Mensagem mensagem = new Mensagem("Erro ao Alterar a Máscara do CPF!", 400);
					ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
				}
			}
			else if(pesquisarFuncionario.getOpcoesBuscaComboBox().getSelectedItem().toString().equalsIgnoreCase("Data de Nascimento")){
				try {					
					pesquisarFuncionario.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
				} catch (ParseException e1) {
					Mensagem mensagem = new Mensagem("Erro ao Alterar a Máscara da Data de Nascimento!", 400);
					ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
				}
			}
			else{				
				pesquisarFuncionario.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter()));
				pesquisarFuncionario.getBuscaField().setFormatterFactory(null);
			}
		}
		
		else if(e.getSource() == pesquisarFuncionario.getEditarFuncionarioButton()){
			//Controla a ação de abrir a tela de edição de funcionários
			try {
				if(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from funcionario where id = :PARAMETRO";
					String parametro = pesquisarFuncionario.getResultadoBuscaTable().getModel().getValueAt(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarFuncionario.setFuncionarios(funcionarioBusiness.pesquisarPersonalizado(query, parametro)); 
					
					EditarFuncionario editarFuncionario = new EditarFuncionario(pesquisarFuncionario.getPertenceDesktop().getDesktopPane().getSize(), 
						pesquisarFuncionario.getPertenceDesktop(), pesquisarFuncionario.getFuncionarios().get(0));
					ControleEditarFuncionario controleEditarFuncionario = new ControleEditarFuncionario(editarFuncionario);
					pesquisarFuncionario.getPertenceDesktop().getDesktopPane().add(editarFuncionario);
					editarFuncionario.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Funcionário foi selecionado na tabela!");
				}
			} catch (FuncionarioNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}			
		}
		
		else if(e.getSource() == pesquisarFuncionario.getExibirDetalhesFuncionarioButton()){
			//Controla a ação de abrir a tela de detalhes do funcionário			
			try {
				if(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from funcionario where id = :PARAMETRO";
					String parametro = pesquisarFuncionario.getResultadoBuscaTable().getModel().getValueAt(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarFuncionario.setFuncionarios(funcionarioBusiness.pesquisarPersonalizado(query, parametro));
				
					ExibirDetalhesFuncionario exibirDetalhesFuncionario = new ExibirDetalhesFuncionario(pesquisarFuncionario.getPertenceDesktop().getDesktopPane().getSize(), pesquisarFuncionario.getPertenceDesktop(), pesquisarFuncionario.getFuncionarios().get(0));
					ControleExibirDetalhesFuncionario controleExibirDetalhesFuncionario = new ControleExibirDetalhesFuncionario(exibirDetalhesFuncionario);
					pesquisarFuncionario.getPertenceDesktop().getDesktopPane().add(exibirDetalhesFuncionario);
					exibirDetalhesFuncionario.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Funcionário foi selecionado na tabela!");
				}
			} catch (FuncionarioNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}			
		}
		
		else if(e.getSource() == pesquisarFuncionario.getDesativarButton()){
			//Controla a ação de desativar o funcionário selecionado
			try {
				if(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow() != -1){
					int id = (int) pesquisarFuncionario.getResultadoBuscaTable().getModel().getValueAt(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow(), 4);
					
					funcionario = funcionarioBusiness.pesquisarPersonalizado("select * from funcionario where id = :PARAMETRO and ativo = true", id+"").get(0);
					historicoFuncionario = new HistoricoFuncionario(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Desativou", App.funcionarioLogado, funcionario);
					historicoFuncionarioBusiness.cadastrar(historicoFuncionario);
					
					funcionarioBusiness.desativar(id);
					tabelaGenerica.remover(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow());					
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Funcionário foi selecionado na tabela!");
				}
				Mensagem mensagem = new Mensagem("O Funcionário foi desativado com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
			} catch (NenhumaLinhaSelecionadaException e2) {
				Mensagem mensagem = new Mensagem(e2.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (FuncionarioNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (ParseException e1) {
				Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		
		else if(e.getSource() == pesquisarFuncionario.getResetarSenhaButton()){
			//Controla a ação de resetar a senha do funcionário
			try {
				if(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow() != -1){
					int id = (int) pesquisarFuncionario.getResultadoBuscaTable().getModel().getValueAt(pesquisarFuncionario.getResultadoBuscaTable().getSelectedRow(), 4);
					funcionarioBusiness.resetarSenha(id);
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Funcionário foi selecionado na tabela!");
				}
				Mensagem mensagem = new Mensagem("A senha do funcionário foi resetada com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
			} catch (NenhumaLinhaSelecionadaException e2) {
				Mensagem mensagem = new Mensagem(e2.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else{
			//Controla a opção de pesquisar o funcionário com base no parâmetro escolhido			
			try {
				String query = new String();
				
				switch (pesquisarFuncionario.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "CPF":{
					query = "select * from funcionario where cpf like :PARAMETRO and ativo = true";	
					pesquisarFuncionario.setFuncionarios(funcionarioBusiness.pesquisarPersonalizado(query, "%"+pesquisarFuncionario.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Data de Nascimento":{
					//Converte o valor pego em date
					Date dataNascimento =  new SimpleDateFormat("dd/MM/yyyy").parse(pesquisarFuncionario.getBuscaField().getText());
				
					//Depois formata data
					DateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
					String dataFormatada = formatoAmericano.format(dataNascimento);
					
					query = "select * from funcionario where data_nascimento like :PARAMETRO and ativo = true";	
					pesquisarFuncionario.setFuncionarios(funcionarioBusiness.pesquisarPersonalizado(query, "%"+dataFormatada+"%"));
					editarTabela();
					break;
				}
					
				case "Nome":{
					query = "select * from funcionario where nome like :PARAMETRO and ativo = true";
					pesquisarFuncionario.setFuncionarios(funcionarioBusiness.pesquisarPersonalizado(query, "%"+pesquisarFuncionario.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}

				default:
					break;
				}
			} catch (FuncionarioNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (ParseException e1) {
				Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
	}
	
	public void editarTabela(){	
		List<Object> listaFuncionarios = new ArrayList<>();
		DateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada;

		for(int i = 0; i < pesquisarFuncionario.getFuncionarios().size(); i++){
			dataFormatada = formatoBrasileiro.format(pesquisarFuncionario.getFuncionarios().get(i).getData_nascimento());
			listaFuncionarios.add(new Object[]{pesquisarFuncionario.getFuncionarios().get(i).getNome(), pesquisarFuncionario.getFuncionarios().get(i).getCpf(), dataFormatada, pesquisarFuncionario.getFuncionarios().get(i).getSexo(), pesquisarFuncionario.getFuncionarios().get(i).getId()});
		}		
		
		pesquisarFuncionario.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaFuncionarios);
	}

	public TabelaGenerica getTabelaGenerica() {
		return tabelaGenerica;
	}

	public PesquisarFuncionario getPesquisarFuncionario() {
		return pesquisarFuncionario;
	}

	public FuncionarioBusiness getFuncionarioBusiness() {
		return funcionarioBusiness;
	}	
}
