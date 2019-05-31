package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.EditarAnimal;
import view.ExibirDetalhesAnimal;

public class ControleExibirDetalhesAnimal implements ActionListener{
	
	private ExibirDetalhesAnimal exibirDetalhesAnimal;	
	
	public ControleExibirDetalhesAnimal(ExibirDetalhesAnimal exibirDetalhesAnimal) {
		this.exibirDetalhesAnimal = exibirDetalhesAnimal;
		
		exibirDetalhesAnimal.getEditarAnimalButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		//Controla a ação de editar o animal
		EditarAnimal editarAnimal = new EditarAnimal(exibirDetalhesAnimal.getPertenceDesktop().getDesktopPane().getSize(), exibirDetalhesAnimal.getPertenceDesktop(), exibirDetalhesAnimal.getAnimal());
		ControleEditarAnimal controleEditarAnimal = new ControleEditarAnimal(editarAnimal);
		exibirDetalhesAnimal.getPertenceDesktop().getDesktopPane().add(editarAnimal);
		editarAnimal.toFront();
		
		exibirDetalhesAnimal.dispose();
		System.gc();
	}

}
