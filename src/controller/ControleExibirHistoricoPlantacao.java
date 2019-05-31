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
import business.HistoricoPlantacaoBusiness;
import model.FuncionarioNaoEncontradoException;
import model.PlantacaoNaoEncontradaException;
import model.TabelaGenerica;
import view.ExibirHistoricoFuncionario;
import view.ExibirHistoricoPlantacao;
import view.Mensagem;

public class ControleExibirHistoricoPlantacao implements ActionListener{
	
	private ExibirHistoricoPlantacao exibirHistoricoPlantacao;
	private TabelaGenerica tabelaGenerica;
	private HistoricoPlantacaoBusiness historicoPlantacaoBusiness;
		
	public ControleExibirHistoricoPlantacao(ExibirHistoricoPlantacao exibirHistoricoPlantacao) {
		this.exibirHistoricoPlantacao = exibirHistoricoPlantacao;
		
		historicoPlantacaoBusiness = new HistoricoPlantacaoBusiness();
		String colunas[] = {"Data", "Funcionário Responsável", "Ação Realizada", "Cultura Alterada"};
		tabelaGenerica = new TabelaGenerica(colunas);
		
		exibirHistoricoPlantacao.getBuscarButton().addActionListener(this);
		exibirHistoricoPlantacao.getOpcoesBuscaComboBox().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exibirHistoricoPlantacao.getOpcoesBuscaComboBox()){
			//Muda as máscaras do campo de busca de acordo com a opção selecionada
			exibirHistoricoPlantacao.getBuscaField().setValue(null);
			
			if(exibirHistoricoPlantacao.getOpcoesBuscaComboBox().getSelectedItem().toString().equalsIgnoreCase("Data")){
				try {
					exibirHistoricoPlantacao.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
				} catch (ParseException e1) {
					Mensagem mensagem = new Mensagem("Erro ao Alterar a Máscara da data!", 400);
					ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
				}
			}
			else{				
				exibirHistoricoPlantacao.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter()));
				exibirHistoricoPlantacao.getBuscaField().setFormatterFactory(null);
			}
		}
		else{
			//Controla a opção de pesquisar os históricos de funcionários			
			try {
				String query = new String();
				
				switch (exibirHistoricoPlantacao.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "Ação Realizada":{
					query = "select * from historico_plantacao where acao like :PARAMETRO";	
					exibirHistoricoPlantacao.setHistoricosList(historicoPlantacaoBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Data":{
					//Converte o valor pego em date
					Date dataRealizacao =  new SimpleDateFormat("dd/MM/yyyy").parse(exibirHistoricoPlantacao.getBuscaField().getText());
				
					//Depois formata data
					DateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
					String dataFormatada = formatoAmericano.format(dataRealizacao);
					
					query = "select * from historico_plantacao where data_realizacao like :PARAMETRO";	
					exibirHistoricoPlantacao.setHistoricosList(historicoPlantacaoBusiness.pesquisarPersonalizado(query, "%"+dataFormatada+"%"));
					editarTabela();
					break;
				}
					
				case "Cultura Alterada":{				
					query = "select * from historico_plantacao as hp where plantacao_id =(select id from plantacao as p where cultura like :PARAMETRO and hp.plantacao_id = p.id)";	
					exibirHistoricoPlantacao.setHistoricosList(historicoPlantacaoBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoPlantacao.getBuscaField().getText()+"%"));				
					editarTabela();
					break;
				}
				
				case "Responsável":{
					query = "select * from historico_plantacao as hp where funcionarioLogado_id =(select id from funcionario as f where nome like :PARAMETRO and hp.funcionarioLogado_id = f.id)";	
					exibirHistoricoPlantacao.setHistoricosList(historicoPlantacaoBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}

				default:
					break;
				}
			} catch (PlantacaoNaoEncontradaException e1) {
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

		for(int i = 0; i < exibirHistoricoPlantacao.getHistoricosList().size(); i++){
			dataFormatada = formatoBrasileiro.format(exibirHistoricoPlantacao.getHistoricosList().get(i).getData_realizacao());
			listaHistoricos.add(new Object[]{dataFormatada, exibirHistoricoPlantacao.getHistoricosList().get(i).getFuncionarioLogado().getNome(), exibirHistoricoPlantacao.getHistoricosList().get(i).getAcao(), 
					exibirHistoricoPlantacao.getHistoricosList().get(i).getPlantacao().getCultura()});
		}			
		
		exibirHistoricoPlantacao.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaHistoricos);
	}
}
