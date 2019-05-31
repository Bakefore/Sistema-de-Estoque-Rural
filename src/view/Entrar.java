package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Entrar extends TelaInterna{
	
	private JLabel loginLabel, senhaLabel, imagemLabel;
	private JTextField loginField;
	private JPasswordField senhaField;
	private JButton entrarButton;
	private DesktopPrincipal pertenceDesktop;

	public Entrar(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Login", 400, 400, false, true, false, dimensaoTela, pertenceDesktop);
		setLayout(null);		
		this.pertenceDesktop = pertenceDesktop;
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){		
		loginLabel = new JLabel("Login");
		senhaLabel = new JLabel("Senha");
		loginField = new JTextField();
		senhaField = new JPasswordField();
		entrarButton = new JButton("Entrar");
		imagemLabel = new JLabel(new ImageIcon("resource/img/fazenda100px.png"));
	}
	
	public void editarObjetos(){
		imagemLabel.setBounds(150, 50, 100, 100);
		
		loginLabel.setBounds(10, 200, 100, 40);
		loginLabel.setFont(getFonteNegrito());
		
		loginField.setBounds(110, 200, 270, 40);
		loginField.setFont(getFonteNormal());
		
		senhaLabel.setBounds(10, 260, 100, 40);
		senhaLabel.setFont(getFonteNegrito());
		
		senhaField.setBounds(110, 260, 270, 40);
		senhaField.setFont(getFonteNormal());
		
		entrarButton.setBounds(10, 320, 370, 40);
		entrarButton.setFont(getFonteNegrito());
	}
	
	public void adicionarObjetos(){
		add(imagemLabel);
		add(loginLabel);
		add(loginField);
		add(senhaLabel);
		add(senhaField);
		add(entrarButton);
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public JLabel getSenhaLabel() {
		return senhaLabel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public JPasswordField getSenhaField() {
		return senhaField;
	}

	public JButton getEntrarButton() {
		return entrarButton;
	}	
}
