package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Mensagem extends Tela{
	
	private String mensagem;
	private JLabel mensagemLabel;
	private JButton confirmarButton;
	private JPanel mensagemPanel, confirmarPanel;

	public Mensagem(String mensagem, int largura) {
		super("Mensagem", largura, 120, false);
		setLayout(new GridLayout(2, 1));
		
		this.mensagem = mensagem;
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){
		confirmarButton = new JButton("Ok");
		mensagemLabel = new JLabel(getMensagem());
		
		mensagemPanel = new JPanel(new FlowLayout());
		confirmarPanel = new JPanel(new FlowLayout());
	}
	
	public void editarObjetos(){
		confirmarButton.setFont(getFonteNegrito());
		confirmarButton.setForeground(Color.BLACK);
		
		mensagemLabel.setFont(getFonteNormal());
		mensagemLabel.setForeground(Color.BLACK);
		mensagemLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		mensagemPanel.add(mensagemLabel);
		confirmarPanel.add(confirmarButton);;
	}
	
	public void adicionarObjetos(){
		add(mensagemPanel);
		add(confirmarPanel);
	}

	public String getMensagem() {
		return mensagem;
	}

	public JButton getConfirmarButton() {
		return confirmarButton;
	}	
}
