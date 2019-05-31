package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import business.HistoricoPlantacaoBusiness;
import business.PlantacaoBusiness;
import model.Animal;
import model.CampoVazioException;
import model.HistoricoPlantacao;
import model.Plantacao;
import view.EditarPlantacao;
import view.Mensagem;

public class ControleEditarPlantacao implements ActionListener{
	
	private EditarPlantacao editarPlantacao;
	private PlantacaoBusiness plantacaoBusiness;
	private Plantacao plantacao;
	private HistoricoPlantacao historicoPlantacao;
	private HistoricoPlantacaoBusiness historicoPlantacaoBusiness;
		
	public ControleEditarPlantacao(EditarPlantacao editarPlantacao) {
		this.editarPlantacao = editarPlantacao;
		
		plantacaoBusiness = new PlantacaoBusiness();
		historicoPlantacaoBusiness = new HistoricoPlantacaoBusiness();
		
		editarPlantacao.getEditarPlantacaoButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		//Controla a ação de editar a Plantação
		try {
			plantacao = new Plantacao(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(editarPlantacao.getPlantacao().getData_cadastro())), new SimpleDateFormat("dd/MM/yyyy").parse(editarPlantacao.getDataInicioDaPlantacaoField().getText()), 
				new SimpleDateFormat("dd/MM/yyyy").parse(editarPlantacao.getDataPrevisaoDaColheitaField().getText()), 
				editarPlantacao.getTipoDeCulturaField().getText(), editarPlantacao.getVariedadeField().getText(), 
				Float.parseFloat(editarPlantacao.getAreaCultivoField().getText()), editarPlantacao.getTipoDeSoloComboBox().getSelectedItem().toString(), 
				editarPlantacao.getPortePlantacaoComboBox().getSelectedItem().toString(), editarPlantacao.getTitularidadeTerrenoComboBox().getSelectedItem().toString(), 
				editarPlantacao.getTempoDeVidaDaPlantacaoComboBox().getSelectedItem().toString(), editarPlantacao.getSistemaDeIrrigacaoComboBox().getSelectedItem().toString(), 
				editarPlantacao.getAdubacaoComboBox().getSelectedItem().toString(), editarPlantacao.getDefensivoAgricolaComboBox().getSelectedItem().toString());
			plantacao.setId(editarPlantacao.getPlantacao().getId());
			
			plantacaoBusiness.editarPlantacao(plantacao);
			
			historicoPlantacao = new HistoricoPlantacao(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Editou", App.funcionarioLogado, plantacao);
			historicoPlantacaoBusiness.cadastrar(historicoPlantacao);
			
			Mensagem mensagem = new Mensagem("A plantação foi editada com Sucesso!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (NumberFormatException e1) {
			Mensagem mensagem = new Mensagem("Insira um valor numérico para área de cultivo!", 600);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (ParseException e1) {
			Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (CampoVazioException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}
	}

}
