package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import business.FuncionarioBusiness;
import model.SenhasDivergentesException;
import model.TamanhoDaSenhaException;
import view.AlterarSenha;
import view.Mensagem;

public class ControleAlterarSenha implements ActionListener{
	
	private AlterarSenha alterarSenha;
	private FuncionarioBusiness funcionarioBusiness;
		
	public ControleAlterarSenha(AlterarSenha alterarSenha) {
		this.alterarSenha = alterarSenha;
		
		funcionarioBusiness = new FuncionarioBusiness();
		
		alterarSenha.getAlterarSenhaButton().addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		//Controla a ação de alterar a senha do funcionário
		try {
			funcionarioBusiness.alterarSenha(new String(alterarSenha.getNovaSenhaField().getPassword()), new String(alterarSenha.getConfirmarSenhaField().getPassword()));
			
			Mensagem mensagem = new Mensagem("Senha alterada com sucesso!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SenhasDivergentesException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} catch (TamanhoDaSenhaException e1) {
			Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		}
	}
	
}
