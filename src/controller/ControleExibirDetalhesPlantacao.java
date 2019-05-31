package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.EditarPlantacao;
import view.ExibirDetalhesPlantacao;

public class ControleExibirDetalhesPlantacao implements ActionListener{
	
	private ExibirDetalhesPlantacao exibirDetalhesPlantacao;
		
	public ControleExibirDetalhesPlantacao(ExibirDetalhesPlantacao exibirDetalhesPlantacao) {	
		this.exibirDetalhesPlantacao = exibirDetalhesPlantacao;
		
		exibirDetalhesPlantacao.getEditarPlantacaoButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		EditarPlantacao editarPlantacao = new EditarPlantacao(exibirDetalhesPlantacao.getPertenceDesktop().getDesktopPane().getSize(), exibirDetalhesPlantacao.getPertenceDesktop(), exibirDetalhesPlantacao.getPlantacao());
		ControleEditarPlantacao controleEditarPlantacao = new ControleEditarPlantacao(editarPlantacao);
		exibirDetalhesPlantacao.getPertenceDesktop().getDesktopPane().add(editarPlantacao);
		editarPlantacao.toFront();
		
		exibirDetalhesPlantacao.dispose();
		System.gc();
	}
}
