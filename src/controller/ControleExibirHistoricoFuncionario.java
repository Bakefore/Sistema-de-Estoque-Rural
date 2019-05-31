package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import business.FuncionarioBusiness;
import business.HistoricoFuncionarioBusiness;
import model.FuncionarioNaoEncontradoException;
import model.TabelaGenerica;
import view.ExibirHistoricoFuncionario;
import view.Mensagem;

public class ControleExibirHistoricoFuncionario implements ActionListener{
	
	private ExibirHistoricoFuncionario exibirHistoricoFuncionario;
	private TabelaGenerica tabelaGenerica;
	private HistoricoFuncionarioBusiness historicoFuncionarioBusiness;
		
	public ControleExibirHistoricoFuncionario(ExibirHistoricoFuncionario exibirHistoricoFuncionario) {
		this.exibirHistoricoFuncionario = exibirHistoricoFuncionario;
		
		historicoFuncionarioBusiness = new HistoricoFuncionarioBusiness();
		String colunas[] = {"Data", "Funcionário Responsável", "Ação Realizada", "Funcionário Alterado"};
		tabelaGenerica = new TabelaGenerica(colunas);
		
		exibirHistoricoFuncionario.getBuscarButton().addActionListener(this);
		exibirHistoricoFuncionario.getOpcoesBuscaComboBox().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exibirHistoricoFuncionario.getOpcoesBuscaComboBox()){
			//Muda as máscaras do campo de busca de acordo com a opção selecionada
			exibirHistoricoFuncionario.getBuscaField().setValue(null);
			
			if(exibirHistoricoFuncionario.getOpcoesBuscaComboBox().getSelectedItem().toString().equalsIgnoreCase("Data")){
				try {
					exibirHistoricoFuncionario.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
				} catch (ParseException e1) {
					Mensagem mensagem = new Mensagem("Erro ao Alterar a Máscara da data!", 400);
					ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
				}
			}
			else{				
				exibirHistoricoFuncionario.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter()));
				exibirHistoricoFuncionario.getBuscaField().setFormatterFactory(null);
			}
		}
		else{
			//Controla a opção de pesquisar os históricos de funcionários			
			try {
				String query = new String();
				
				switch (exibirHistoricoFuncionario.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "Ação Realizada":{
					query = "select * from historico_funcionario where acao like :PARAMETRO";	
					exibirHistoricoFuncionario.setHistoricosList(historicoFuncionarioBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoFuncionario.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Data":{
					//Converte o valor pego em date
					Date dataRealizacao =  new SimpleDateFormat("dd/MM/yyyy").parse(exibirHistoricoFuncionario.getBuscaField().getText());
				
					//Depois formata data
					DateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
					String dataFormatada = formatoAmericano.format(dataRealizacao);
					
					query = "select * from historico_funcionario where data_realizacao like :PARAMETRO";	
					exibirHistoricoFuncionario.setHistoricosList(historicoFuncionarioBusiness.pesquisarPersonalizado(query, "%"+dataFormatada+"%"));
					editarTabela();
					break;
				}
					
				case "Funcionário Alterado":{				
					query = "select * from historico_funcionario as hf where funcionarioAlvo_id =(select id from funcionario as f where nome like :PARAMETRO and hf.funcionarioAlvo_id = f.id)";	
					exibirHistoricoFuncionario.setHistoricosList(historicoFuncionarioBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoFuncionario.getBuscaField().getText()+"%"));				
					editarTabela();
					break;
				}
				
				case "Responsável":{
					query = "select * from historico_funcionario as hf where funcionarioLogado_id =(select id from funcionario as f where nome like :PARAMETRO and hf.funcionarioLogado_id = f.id)";	
					exibirHistoricoFuncionario.setHistoricosList(historicoFuncionarioBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoFuncionario.getBuscaField().getText()+"%"));
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
		List<Object> listaHistoricos = new ArrayList<>();
		DateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada;

		for(int i = 0; i < exibirHistoricoFuncionario.getHistoricosList().size(); i++){
			dataFormatada = formatoBrasileiro.format(exibirHistoricoFuncionario.getHistoricosList().get(i).getData_realizacao());
			listaHistoricos.add(new Object[]{dataFormatada, exibirHistoricoFuncionario.getHistoricosList().get(i).getFuncionarioLogado().getNome(), exibirHistoricoFuncionario.getHistoricosList().get(i).getAcao(), 
					exibirHistoricoFuncionario.getHistoricosList().get(i).getFuncionarioAlvo().getNome()});
		}			
		
		exibirHistoricoFuncionario.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaHistoricos);
	}
}
