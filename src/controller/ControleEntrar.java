package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import app.App;
import business.FuncionarioBusiness;
import dao.FuncionarioDAO;
import model.Funcionario;
import model.FuncionarioNaoEncontradoException;
import view.Entrar;
import view.Mensagem;

public class ControleEntrar implements ActionListener{
	private Entrar telaDeLogin;
	private FuncionarioBusiness funcionarioBusiness;
	
	public ControleEntrar(Entrar telaDeLogin) {
		this.telaDeLogin = telaDeLogin;
		
		funcionarioBusiness = new FuncionarioBusiness();
		
		telaDeLogin.getEntrarButton().addActionListener(this);
		
		telaDeLogin.getLoginField().addKeyListener(new KeyListener() {			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					fazerLogin();
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		
		telaDeLogin.getSenhaField().addKeyListener(new KeyListener() {			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					fazerLogin();
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == telaDeLogin.getEntrarButton()){
			fazerLogin();
		}	
	}
	
	public void fazerLogin(){	
		Funcionario funcionario;
		try {
			funcionario = funcionarioBusiness.realizarLogin(telaDeLogin.getLoginField().getText(), new String(telaDeLogin.getSenhaField().getPassword()));
			App.funcionarioLogado = funcionario;
			if(funcionario != null){
				switch (funcionario.getTipo_funcionario()) {
				case "Administrador": {
					exibirAcessoAdmin();
					break;
				}
				case "Setor de Animais" : {
					telaDeLogin.getPertenceDesktop().getAnimalMenu().setVisible(true);
					exibirBotaoLogin();
					break;
				}
				case "Setor de Plantações" : {
					telaDeLogin.getPertenceDesktop().getPlantaMenu().setVisible(true);
					exibirBotaoLogin();
					break;		
				}	
				case "Super Administrador" : {					
					exibirAcessoAdmin();
					telaDeLogin.getPertenceDesktop().getHistoricoMenu().setVisible(true);
					break;
				}
				default:
					break;
				}
				
				telaDeLogin.dispose();
				
				Mensagem mensagem = new Mensagem("Bem Vindo "+funcionario.getNome()+"!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		} catch (FuncionarioNaoEncontradoException e) {
			Mensagem mensagem = new Mensagem(e.getMessage(), 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);
		} 			
	}
	
	public void exibirBotaoLogin(){
		telaDeLogin.getPertenceDesktop().getAlterarSenha().setVisible(true);
		telaDeLogin.getPertenceDesktop().getSairItem().setVisible(true);
		telaDeLogin.getPertenceDesktop().getEntrarItem().setVisible(false);
	}
	
	public void exibirAcessoAdmin(){
		telaDeLogin.getPertenceDesktop().getFuncionarioMenu().setVisible(true);
		telaDeLogin.getPertenceDesktop().getAnimalMenu().setVisible(true);
		telaDeLogin.getPertenceDesktop().getPlantaMenu().setVisible(true);
		exibirBotaoLogin();
	}
}
