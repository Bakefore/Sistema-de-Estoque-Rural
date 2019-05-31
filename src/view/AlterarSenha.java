package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class AlterarSenha extends TelaInterna{
	
	private JLabel novaSenhaLabel, confirmarSenhaLabel;
	private JPasswordField novaSenhaField, confirmarSenhaField;
	private JButton alterarSenhaButton;

	public AlterarSenha(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Alterar Senha", 506, 180, false, true, false, dimensaoTela, pertenceDesktop);
		setLayout(null);		
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){
		novaSenhaLabel = new JLabel("Nova Senha *");
		confirmarSenhaLabel = new JLabel("Confirmar Senha *");
		
		novaSenhaField = new JPasswordField();
		confirmarSenhaField = new JPasswordField();
		
		alterarSenhaButton = new JButton("Alterar Senha");
	}
	
	public void editarObjetos(){
		novaSenhaLabel.setBounds(10, 0, 200, 40); 
		novaSenhaLabel.setFont(getFonteNegrito());
		
		confirmarSenhaLabel.setBounds(258, 0, 200, 40); 
		confirmarSenhaLabel.setFont(getFonteNegrito());
		
		novaSenhaField.setBounds(8, 40, 229, 40);
		novaSenhaField.setFont(getFonteNormal());
		
		confirmarSenhaField.setBounds(255, 40, 231, 40);
		confirmarSenhaField.setFont(getFonteNormal());
		
		alterarSenhaButton.setBounds(103, 100, 300, 40);
		alterarSenhaButton.setFont(getFonteNegrito());
	}
	
	public void adicionarObjetos(){
		add(novaSenhaLabel);
		add(confirmarSenhaLabel);
		add(novaSenhaField);
		add(confirmarSenhaField);
		add(alterarSenhaButton);
	}

	public JPasswordField getNovaSenhaField() {
		return novaSenhaField;
	}

	public JPasswordField getConfirmarSenhaField() {
		return confirmarSenhaField;
	}

	public JButton getAlterarSenhaButton() {
		return alterarSenhaButton;
	}	
}