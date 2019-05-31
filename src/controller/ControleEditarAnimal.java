package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import business.AnimalBusiness;
import business.HistoricoAnimalBusiness;
import model.Animal;
import model.CampoVazioException;
import model.HistoricoAnimal;
import view.EditarAnimal;
import view.Mensagem;

public class ControleEditarAnimal implements ActionListener{
	
	private EditarAnimal editarAnimal;	
	private AnimalBusiness animalBusiness;
	private Animal animal;
	private HistoricoAnimalBusiness historicoAnimalBusiness;
	private HistoricoAnimal historicoAnimal;
	
	public ControleEditarAnimal(EditarAnimal editarAnimal) {
		this.editarAnimal = editarAnimal;
		
		animalBusiness = new AnimalBusiness();
		historicoAnimalBusiness = new HistoricoAnimalBusiness();
			
		editarAnimal.getEspecieComboBox().addActionListener(this);
		editarAnimal.getEditarAnimalButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == editarAnimal.getEspecieComboBox()){
			//Controla a ação de clicar no botão de espécie	
			editarAnimal.editarOpcoes(editarAnimal);
		}
		else{
			//Controla a ação de editar o Animal
			try {
				animal = new Animal(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(editarAnimal.getAnimal().getData_cadastro())), editarAnimal.getEspecieComboBox().getSelectedItem().toString(), editarAnimal.getRacaComboBox().getSelectedItem().toString(), 
					editarAnimal.getSexoComboBox().getSelectedItem().toString(), new SimpleDateFormat("dd/MM/yyyy").parse(editarAnimal.getNascimentoField().getText()), 
					Float.parseFloat(editarAnimal.getPesoField().getText()), Float.parseFloat(editarAnimal.getAlturaField().getText()), 
					editarAnimal.getRegimeAlimentarComboBox().getSelectedItem().toString(), editarAnimal.getFinalidadeComboBox().getSelectedItem().toString(), 
					Integer.parseInt(editarAnimal.getQuantidadeField().getText()));
				animal.setId(editarAnimal.getAnimal().getId());
				
				animalBusiness.editarAnimal(animal);
				
				historicoAnimal = new HistoricoAnimal(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Editou", App.funcionarioLogado, animal);
				historicoAnimalBusiness.cadastrar(historicoAnimal);
				
				Mensagem mensagem = new Mensagem("O Animal foi editado com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NumberFormatException e1) {
				Mensagem mensagem = new Mensagem("Insira um valor numérico nos campos: peso, altura, quantidade!", 600);
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
}
