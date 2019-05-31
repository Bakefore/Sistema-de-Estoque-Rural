package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import app.App;
import view.AlterarSenha;
import view.CadastrarAnimal;
import view.CadastrarFuncionario;
import view.CadastrarPlantacao;
import view.DesktopPrincipal;
import view.EditarAnimal;
import view.Entrar;
import view.ExibirHistoricoAnimal;
import view.ExibirHistoricoFuncionario;
import view.ExibirHistoricoPlantacao;
import view.Mensagem;
import view.PesquisarAnimal;
import view.PesquisarFuncionario;
import view.PesquisarPlantacao;

public class ControleDesktop implements ActionListener{
	
	private DesktopPrincipal desktopPrincipal;
		
	public ControleDesktop(DesktopPrincipal desktopPrincipal) {
		this.desktopPrincipal = desktopPrincipal;
		
		desktopPrincipal.getEntrarItem().addActionListener(this);
		desktopPrincipal.getSairItem().addActionListener(this);
		desktopPrincipal.getCadastrarAnimal().addActionListener(this);
		desktopPrincipal.getPesquisarAnimal().addActionListener(this);
		desktopPrincipal.getPesquisarPlantacao().addActionListener(this);
		desktopPrincipal.getCadastrarFuncionario().addActionListener(this);
		desktopPrincipal.getPesquisarFuncionario().addActionListener(this);
		desktopPrincipal.getCadastrarPlantacao().addActionListener(this);
		desktopPrincipal.getAlterarSenha().addActionListener(this);
		desktopPrincipal.getHistoricoFuncionarioItem().addActionListener(this);
		desktopPrincipal.getHistoricoAnimalItem().addActionListener(this);
		desktopPrincipal.getHistoricoPlantacaoitem().addActionListener(this);
		
		abrirTelaDeLogin();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == desktopPrincipal.getEntrarItem()){
			//Controla a ação de Abrir a tela de login
			abrirTelaDeLogin();
		}
		else if(e.getSource() == desktopPrincipal.getSairItem()){
			//Controla a ação para deslogar do sistema
			desktopPrincipal.getFuncionarioMenu().setVisible(false);
			desktopPrincipal.getAnimalMenu().setVisible(false);
			desktopPrincipal.getPlantaMenu().setVisible(false);
			desktopPrincipal.getAlterarSenha().setVisible(false);
			desktopPrincipal.getSairItem().setVisible(false);
			desktopPrincipal.getEntrarItem().setVisible(true);
			desktopPrincipal.getHistoricoMenu().setVisible(false);
			
			App.funcionarioLogado = null;
			
			Mensagem mensagem = new Mensagem("Sessão encerrada!", 300);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		}
		else if(e.getSource() == desktopPrincipal.getCadastrarAnimal()){
			//Controla a ação de Abrir a tela para cadastrar animais			
			CadastrarAnimal cadastrarAnimal = new CadastrarAnimal(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleCadastrarAnimal controleCadastrarAnimal = new ControleCadastrarAnimal(cadastrarAnimal);
			desktopPrincipal.getDesktopPane().add(cadastrarAnimal);
			cadastrarAnimal.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getPesquisarAnimal()){
			//Controla a ação de abrir a tela para pesquisar animais
			PesquisarAnimal pesquisarAnimal = new PesquisarAnimal(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControlePesquisarAnimal controlePesquisarAnimal = new ControlePesquisarAnimal(pesquisarAnimal);
			desktopPrincipal.getDesktopPane().add(pesquisarAnimal);
			pesquisarAnimal.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getPesquisarPlantacao()){
			//Controla a ação de abrir a tela para pesquisar plantações
			PesquisarPlantacao pesquisarPlantacao = new PesquisarPlantacao(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControlePesquisarPlantacao controlePesquisarPlantacao = new ControlePesquisarPlantacao(pesquisarPlantacao);
			desktopPrincipal.getDesktopPane().add(pesquisarPlantacao);
			pesquisarPlantacao.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getCadastrarFuncionario()){
			//Controla a ação de abrir a tela para cadastrar funcionário
			CadastrarFuncionario cadastrarFuncionario = new CadastrarFuncionario(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleCadastrarFuncionario controleCadastrarFuncionario = new ControleCadastrarFuncionario(cadastrarFuncionario);
			desktopPrincipal.getDesktopPane().add(cadastrarFuncionario);
			cadastrarFuncionario.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getPesquisarFuncionario()){
			//Controla a ação de abrir a tela para pesquisar funcionários
			PesquisarFuncionario pesquisarFuncionario = new PesquisarFuncionario(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControlePesquisarFuncionario controlePesquisarFuncionario = new ControlePesquisarFuncionario(pesquisarFuncionario);
			desktopPrincipal.getDesktopPane().add(pesquisarFuncionario);
			pesquisarFuncionario.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getCadastrarPlantacao()){
			//Controla a ação de abrir a tela para cadastrar uma plantação
			CadastrarPlantacao cadastrarPlantacao = new CadastrarPlantacao(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleCadastrarPlantacao controleCadastrarPlantacao = new ControleCadastrarPlantacao(cadastrarPlantacao);
			desktopPrincipal.getDesktopPane().add(cadastrarPlantacao);
			cadastrarPlantacao.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getAlterarSenha()){
			//Controla a ação de abrir a tela para alterar a senha do usuário
			AlterarSenha alterarSenha = new AlterarSenha(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleAlterarSenha controleAlterarSenha = new ControleAlterarSenha(alterarSenha);
			desktopPrincipal.getDesktopPane().add(alterarSenha);
			alterarSenha.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getHistoricoFuncionarioItem()){
			//Controla a ação de abrir a tela de exibição do histórico de funcionários
			ExibirHistoricoFuncionario exibirHistoricoFuncionario = new ExibirHistoricoFuncionario(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleExibirHistoricoFuncionario controleExibirHistoricoFuncionario = new ControleExibirHistoricoFuncionario(exibirHistoricoFuncionario);
			desktopPrincipal.getDesktopPane().add(exibirHistoricoFuncionario);
			exibirHistoricoFuncionario.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getHistoricoAnimalItem()){
			//Controla a ação de abrir a tela de exibição do histórico de animais
			ExibirHistoricoAnimal exibirHistoricoAnimal = new ExibirHistoricoAnimal(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleExibirHistoricoAnimal controleExibirHistoricoAnimal = new ControleExibirHistoricoAnimal(exibirHistoricoAnimal);
			desktopPrincipal.getDesktopPane().add(exibirHistoricoAnimal);
			exibirHistoricoAnimal.toFront();
		}
		else if(e.getSource() == desktopPrincipal.getHistoricoPlantacaoitem()){
			//Controla a ação de exibir o histórico de plantações
			ExibirHistoricoPlantacao exibirHistoricoPlantacao = new ExibirHistoricoPlantacao(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
			ControleExibirHistoricoPlantacao controleExibirHistoricoPlantacao = new ControleExibirHistoricoPlantacao(exibirHistoricoPlantacao);
			desktopPrincipal.getDesktopPane().add(exibirHistoricoPlantacao);
			exibirHistoricoPlantacao.toFront();
		}
	}
	
	public void abrirTelaDeLogin(){
		Entrar entrar = new Entrar(desktopPrincipal.getDesktopPane().getSize(), desktopPrincipal);
		ControleEntrar controleEntrar = new ControleEntrar(entrar);
		desktopPrincipal.getDesktopPane().add(entrar);
		entrar.toFront();
	}
}
