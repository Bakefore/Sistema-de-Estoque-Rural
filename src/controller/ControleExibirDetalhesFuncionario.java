package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.EditarFuncionario;
import view.ExibirDetalhesFuncionario;

public class ControleExibirDetalhesFuncionario implements ActionListener{
	
	private ExibirDetalhesFuncionario exibirDetalhesFuncionario;
		
	public ControleExibirDetalhesFuncionario(ExibirDetalhesFuncionario exibirDetalhesFuncionario) {
		this.exibirDetalhesFuncionario = exibirDetalhesFuncionario;
		
		exibirDetalhesFuncionario.getEditarFuncionarioButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		EditarFuncionario editarFuncionario = new EditarFuncionario(exibirDetalhesFuncionario.getPertenceDesktop().getDesktopPane().getSize(), exibirDetalhesFuncionario.getPertenceDesktop(), exibirDetalhesFuncionario.getFuncionario());
		ControleEditarFuncionario controleEditarFuncionario = new ControleEditarFuncionario(editarFuncionario);
		exibirDetalhesFuncionario.getPertenceDesktop().getDesktopPane().add(editarFuncionario);
		editarFuncionario.toFront();
		
		exibirDetalhesFuncionario.dispose();
		System.gc();
	}
}
