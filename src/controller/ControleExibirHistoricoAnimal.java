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
import business.HistoricoAnimalBusiness;
import business.HistoricoFuncionarioBusiness;
import model.AnimalNaoEncontradoException;
import model.FuncionarioNaoEncontradoException;
import model.TabelaGenerica;
import view.ExibirHistoricoAnimal;
import view.ExibirHistoricoFuncionario;
import view.Mensagem;

public class ControleExibirHistoricoAnimal implements ActionListener{
	
	private ExibirHistoricoAnimal exibirHistoricoAnimal;
	private TabelaGenerica tabelaGenerica;
	private HistoricoAnimalBusiness historicoAnimalBusiness;
		
	public ControleExibirHistoricoAnimal(ExibirHistoricoAnimal exibirHistoricoAnimal) {
		this.exibirHistoricoAnimal = exibirHistoricoAnimal;
		
		historicoAnimalBusiness = new HistoricoAnimalBusiness();
		String colunas[] = {"Data", "Funcionário Responsável", "Ação Realizada", "Raça Alterada"};
		tabelaGenerica = new TabelaGenerica(colunas);
		
		exibirHistoricoAnimal.getBuscarButton().addActionListener(this);
		exibirHistoricoAnimal.getOpcoesBuscaComboBox().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exibirHistoricoAnimal.getOpcoesBuscaComboBox()){
			//Muda as máscaras do campo de busca de acordo com a opção selecionada
			exibirHistoricoAnimal.getBuscaField().setValue(null);
			
			if(exibirHistoricoAnimal.getOpcoesBuscaComboBox().getSelectedItem().toString().equalsIgnoreCase("Data")){
				try {
					exibirHistoricoAnimal.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
				} catch (ParseException e1) {
					Mensagem mensagem = new Mensagem("Erro ao Alterar a Máscara da data!", 400);
					ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
				}
			}
			else{				
				exibirHistoricoAnimal.getBuscaField().setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter()));
				exibirHistoricoAnimal.getBuscaField().setFormatterFactory(null);
			}
		}
		else{
			//Controla a opção de pesquisar os históricos de funcionários			
			try {
				String query = new String();
				
				switch (exibirHistoricoAnimal.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "Ação Realizada":{
					query = "select * from historico_animal where acao like :PARAMETRO";	
					exibirHistoricoAnimal.setHistoricosList(historicoAnimalBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Data":{
					//Converte o valor pego em date
					Date dataRealizacao =  new SimpleDateFormat("dd/MM/yyyy").parse(exibirHistoricoAnimal.getBuscaField().getText());
				
					//Depois formata data
					DateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
					String dataFormatada = formatoAmericano.format(dataRealizacao);
					
					query = "select * from historico_animal where data_realizacao like :PARAMETRO";	
					exibirHistoricoAnimal.setHistoricosList(historicoAnimalBusiness.pesquisarPersonalizado(query, "%"+dataFormatada+"%"));
					editarTabela();
					break;
				}
					
				case "Raça Alterada":{				
					query = "select * from historico_animal as ha where animalAlvo_id =(select id from animal as a where raca_especie like :PARAMETRO and ha.animalAlvo_id = a.id)";	
					exibirHistoricoAnimal.setHistoricosList(historicoAnimalBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoAnimal.getBuscaField().getText()+"%"));				
					editarTabela();
					break;
				}
				
				case "Responsável":{
					query = "select * from historico_animal as ha where funcionarioLogado_id =(select id from funcionario as f where nome like :PARAMETRO and ha.funcionarioLogado_id = f.id)";	
					exibirHistoricoAnimal.setHistoricosList(historicoAnimalBusiness.pesquisarPersonalizado(query, "%"+exibirHistoricoAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}

				default:
					break;
				}
			} catch (AnimalNaoEncontradoException e1) {
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

		for(int i = 0; i < exibirHistoricoAnimal.getHistoricosList().size(); i++){
			dataFormatada = formatoBrasileiro.format(exibirHistoricoAnimal.getHistoricosList().get(i).getData_realizacao());
			listaHistoricos.add(new Object[]{dataFormatada, exibirHistoricoAnimal.getHistoricosList().get(i).getFuncionarioLogado().getNome(), exibirHistoricoAnimal.getHistoricosList().get(i).getAcao(), 
					exibirHistoricoAnimal.getHistoricosList().get(i).getAnimalAlvo().getRaca_especie()});
		}			
		
		exibirHistoricoAnimal.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaHistoricos);
	}
}
