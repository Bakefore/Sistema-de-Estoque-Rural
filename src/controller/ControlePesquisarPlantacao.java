package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.App;
import business.HistoricoPlantacaoBusiness;
import business.PlantacaoBusiness;
import model.AnimalNaoEncontradoException;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoAnimal;
import model.HistoricoPlantacao;
import model.NenhumaLinhaSelecionadaException;
import model.Plantacao;
import model.PlantacaoNaoEncontradaException;
import model.TabelaGenerica;
import view.EditarAnimal;
import view.EditarPlantacao;
import view.ExibirDetalhesAnimal;
import view.ExibirDetalhesPlantacao;
import view.Mensagem;
import view.PesquisarAnimal;
import view.PesquisarPlantacao;

public class ControlePesquisarPlantacao implements ActionListener{
	
	private PesquisarPlantacao pesquisarPlantacao;
	private PlantacaoBusiness plantacaoBusiness;
	private TabelaGenerica tabelaGenerica;
	private HistoricoPlantacao historicoPlantacao;
	private HistoricoPlantacaoBusiness historicoPlantacaoBusiness;
	private Plantacao plantacao;
	
	public ControlePesquisarPlantacao(PesquisarPlantacao pesquisarPlantacao) {
		this.pesquisarPlantacao = pesquisarPlantacao;
		
		plantacaoBusiness = new PlantacaoBusiness();
		historicoPlantacaoBusiness = new HistoricoPlantacaoBusiness();
		String colunas[] = {"Tipo de Cultura", "Área de Cultivo", "Titularidade do Terreno", "Porte da Plantação"};
		tabelaGenerica = new TabelaGenerica(colunas);
		
		pesquisarPlantacao.getEditarPlantacaoButton().addActionListener(this);
		pesquisarPlantacao.getExibirDetalhesPlantacaoButton().addActionListener(this);
		pesquisarPlantacao.getBuscarButton().addActionListener(this);
		pesquisarPlantacao.getDesativarButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pesquisarPlantacao.getEditarPlantacaoButton()){
			//Controla a opção de abrir a tela de edição da plantação
			try {
				if(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from plantacao where id = :PARAMETRO";
					String parametro = pesquisarPlantacao.getResultadoBuscaTable().getModel().getValueAt(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, parametro)); 
					
					EditarPlantacao editarPlantacao = new EditarPlantacao(pesquisarPlantacao.getPertenceDesktop().getDesktopPane().getSize(), 
							pesquisarPlantacao.getPertenceDesktop(), pesquisarPlantacao.getPlantacoes().get(0));
					ControleEditarPlantacao controleEditarPlantacao = new ControleEditarPlantacao(editarPlantacao);
					pesquisarPlantacao.getPertenceDesktop().getDesktopPane().add(editarPlantacao);
					editarPlantacao.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhuma plantação foi selecionada na tabela!");
				}
			} catch (PlantacaoNaoEncontradaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		else if(e.getSource() == pesquisarPlantacao.getExibirDetalhesPlantacaoButton()){
			//Controla a opção de abrir a tela que irá exibir mais informações sobre a plantação
			try {
				if(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from plantacao where id = :PARAMETRO";
					String parametro = pesquisarPlantacao.getResultadoBuscaTable().getModel().getValueAt(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, parametro));
				
					ExibirDetalhesPlantacao exibirDetalhesPlantacao = new ExibirDetalhesPlantacao(pesquisarPlantacao.getPertenceDesktop().getDesktopPane().getSize(), pesquisarPlantacao.getPertenceDesktop(), pesquisarPlantacao.getPlantacoes().get(0));
					ControleExibirDetalhesPlantacao controleExibirDetalhesPlantacao = new ControleExibirDetalhesPlantacao(exibirDetalhesPlantacao);
					pesquisarPlantacao.getPertenceDesktop().getDesktopPane().add(exibirDetalhesPlantacao);
					exibirDetalhesPlantacao.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhuma plantação foi selecionada na tabela!");
				}
			} catch (PlantacaoNaoEncontradaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		else if(e.getSource() == pesquisarPlantacao.getDesativarButton()){
			//Controla a opção de desativar a plantação selecionada
			try {
				if(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow() != -1){
					int id = (int) pesquisarPlantacao.getResultadoBuscaTable().getModel().getValueAt(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow(), 4);
					
					plantacao = plantacaoBusiness.pesquisarPersonalizado("select * from plantacao where id = :PARAMETRO and ativo = true", id+"").get(0);
					historicoPlantacao = new HistoricoPlantacao(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Desativou", App.funcionarioLogado, plantacao);
					historicoPlantacaoBusiness.cadastrar(historicoPlantacao);
					
					plantacaoBusiness.desativar(id);
					tabelaGenerica.remover(pesquisarPlantacao.getResultadoBuscaTable().getSelectedRow());
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhuma plantação foi selecionada na tabela!");
				}
				Mensagem mensagem = new Mensagem("A plantação foi desativada com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (PlantacaoNaoEncontradaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (ParseException e1) {
				Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}	
		else{
			//Controla a ação de pesquisar as plantações com o filtro inserido
			try {
				String query = new String();
				
				switch (pesquisarPlantacao.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "Área de Cultivo":{
					query = "select * from plantacao where area like :PARAMETRO and ativo = true";	
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, "%"+pesquisarPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Porte da Plantação":{					
					query = "select * from plantacao where porte like :PARAMETRO and ativo = true";	
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, "%"+pesquisarPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
					
				case "Tipo de Cultura":{
					query = "select * from plantacao where cultura like :PARAMETRO and ativo = true";
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, "%"+pesquisarPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Titularidade do Terreno":{
					query = "select * from plantacao where titularidade_terreno like :PARAMETRO and ativo = true";
					pesquisarPlantacao.setPlantacoes(plantacaoBusiness.pesquisarPersonalizado(query, "%"+pesquisarPlantacao.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}

				default:
					break;
				}
			} catch (PlantacaoNaoEncontradaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} 
		}
	}
	
	public void editarTabela(){	
		List<Object> listaAnimais = new ArrayList<>();

		for(int i = 0; i < pesquisarPlantacao.getPlantacoes().size(); i++){
			listaAnimais.add(new Object[]{pesquisarPlantacao.getPlantacoes().get(i).getCultura(), 
				pesquisarPlantacao.getPlantacoes().get(i).getArea(), pesquisarPlantacao.getPlantacoes().get(i).getTitularidade_terreno(), 
				pesquisarPlantacao.getPlantacoes().get(i).getPorte(), pesquisarPlantacao.getPlantacoes().get(i).getId()});
		}			
		
		pesquisarPlantacao.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaAnimais);
	}
}
