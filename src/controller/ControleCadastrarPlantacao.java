package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import business.HistoricoAnimalBusiness;
import business.HistoricoPlantacaoBusiness;
import business.PlantacaoBusiness;
import model.CampoVazioException;
import model.HistoricoAnimal;
import model.HistoricoPlantacao;
import model.Plantacao;
import model.PlantacaoNaoEncontradaException;
import view.CadastrarAnimal;
import view.CadastrarPlantacao;
import view.Mensagem;

public class ControleCadastrarPlantacao implements ActionListener{
	
	private CadastrarPlantacao cadastrarPlantacao;
	private PlantacaoBusiness plantacaoBusiness;
	private Plantacao plantacao;
	private HistoricoPlantacao historicoPlantacao;
	private HistoricoPlantacaoBusiness historicoPlantacaoBusiness;
	
	public ControleCadastrarPlantacao(CadastrarPlantacao cadastrarPlantacao) {
		this.cadastrarPlantacao = cadastrarPlantacao;
		
		plantacaoBusiness = new PlantacaoBusiness();
		historicoPlantacaoBusiness = new HistoricoPlantacaoBusiness();
		
		cadastrarPlantacao.getCadastrarPlantacaoButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {		
		//Controla a ação de cadastrar a plantação na base de dados
		try {
			plantacao = new Plantacao(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), new SimpleDateFormat("dd/MM/yyyy").parse(cadastrarPlantacao.getDataInicioDaPlantacaoField().getText()), 
				new SimpleDateFormat("dd/MM/yyyy").parse(cadastrarPlantacao.getDataPrevisaoDaColheitaField().getText()), 
				cadastrarPlantacao.getTipoDeCulturaField().getText(), cadastrarPlantacao.getVariedadeField().getText(), 
				Float.parseFloat(cadastrarPlantacao.getAreaCultivoField().getText()), cadastrarPlantacao.getTipoDeSoloComboBox().getSelectedItem().toString(), 
				cadastrarPlantacao.getPortePlantacaoComboBox().getSelectedItem().toString(), cadastrarPlantacao.getTitularidadeTerrenoComboBox().getSelectedItem().toString(), 
				cadastrarPlantacao.getTempoDeVidaComboBox().getSelectedItem().toString(), cadastrarPlantacao.getSistemaDeIrrigacaoComboBox().getSelectedItem().toString(), 
				cadastrarPlantacao.getAdubacaoComboBox().getSelectedItem().toString(), cadastrarPlantacao.getDefensivoAgricolaComboBox().getSelectedItem().toString());
			
			plantacaoBusiness.cadastrar(plantacao);
			
			plantacao = plantacaoBusiness.pesquisarUltimoInserido();
			historicoPlantacao = new HistoricoPlantacao(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Cadastrou", App.funcionarioLogado, plantacao);
			historicoPlantacaoBusiness.cadastrar(historicoPlantacao);
			
			Mensagem mensagem = new Mensagem("A plantação foi cadastrada com Sucesso!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		} catch (NumberFormatException e1) {
			Mensagem mensagem = new Mensagem("Insira um valor numérico para área de cultivo!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (ParseException e1) {
			Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (CampoVazioException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (PlantacaoNaoEncontradaException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}
		
	}
}
