package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Mensagem;

public class ControleMensagem implements ActionListener{
	
	private Mensagem mensagem;
	
	public ControleMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
		mensagem.getConfirmarButton().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		mensagem.dispose();
		System.gc();
	}

}
