package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControleMensagem;

public class CadastrarFuncionario extends TelaInterna{
	
	private JLabel nomeLabel, cpfLabel, nascimentoLabel, cidadeLabel, bairroLabel, enderecoRuaLabel, estadoLabel, cepLabel, emailLabel, 
	telefone1Label, celularLabel, enderecoNumeroLabel, sexoLabel, tipoFuncionarioLabel, complementoEnderecoLabel, loginLabel, 
	senhaLabel, confirmarSenhaLabel;
	private JTextField nomeField, cidadeField, bairroField, enderecoRuaField, enderecoNumeroField, 
	emailField, complementoEnderecoField, loginField;
	private JComboBox<String> estadoComboBox, tipoFuncionarioComboBox;
	private JPasswordField senhaField, confirmarSenhaField;
	private JFormattedTextField nascimentoField, cpfField, cepField, telefone1Field, celularField;	
	private JRadioButton masculinoRadioButton, femininoRadioButton;
	private ButtonGroup sexoGroup;
	private JButton cadastrarFuncionarioButton;
	private JPanel painel1, subPainel1de1, subPainel2de1, painel2, painel3, painel4, painel5, painel6, 
	painel7, painel8, subPainel1de8, subPainel2de8, painel9, subPainel1de9, subPainel2de9, painel10, 
	painel11, painel12, subPainel1de12, subPainel2de12, painel13, subPainel1de13, subPainel2de13, 
	painel14;

	public CadastrarFuncionario(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Cadastrar Funcionário", 1000, 620, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(14, 1));		
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	public void instanciarObjetos(){
		nomeLabel = new JLabel("Nome *");									//Adicionado
		cpfLabel = new JLabel("CPF *");										//Adicionado
		nascimentoLabel = new JLabel("Data de Nascimento *");				//Adicionado
		cidadeLabel = new JLabel("Cidade *");								//Adicionado
		bairroLabel = new JLabel("Bairro *");								//Adicionado
		enderecoRuaLabel = new JLabel("Rua *");								//Adicionado
		estadoLabel = new JLabel("UF *");								//Adicionado
		cepLabel = new JLabel("CEP *");										//Adicionado
		emailLabel = new JLabel("E-mail");									//Adicionado
		telefone1Label = new JLabel("Telefone");							//Adicionado
		celularLabel = new JLabel("Celular");								//Adicionado
		masculinoRadioButton = new JRadioButton("Masculino", true);			//Adicionado
		femininoRadioButton = new JRadioButton("Feminino");					//Adicionado
		enderecoNumeroLabel = new JLabel("Número *");						//Adicionado
		sexoLabel = new JLabel("Sexo *");									//Adicionado
		tipoFuncionarioLabel = new JLabel("Tipo de Funcionário *");			//Adicionado
		complementoEnderecoLabel = new JLabel("Complemento");				//Adicionado
		cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");	//Adicionado
		loginLabel = new JLabel("Login *");									//Adicionado
		senhaLabel = new JLabel("Senha *");									//Adicionado
		confirmarSenhaLabel = new JLabel("Confirmar Senha *");				//Adicionado
		
		sexoGroup = new ButtonGroup();
				
		try {
			nascimentoField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			cepField = new JFormattedTextField(new MaskFormatter("#####-###"));
			cpfField = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			telefone1Field = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
			celularField = new JFormattedTextField(new MaskFormatter("(##) 9 ####-####"));
		} catch (ParseException e) {
			Mensagem mensagem = new Mensagem("Apenas números são permitidos!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		}		
		
		loginField = new JTextField();								//Adicionado
		senhaField = new JPasswordField();							//Adicionado
		confirmarSenhaField = new JPasswordField();					//Adicionado
		complementoEnderecoField = new JTextField();				//Adicionado
		nomeField = new JTextField();								//Adicionado																
		cidadeField = new JTextField();								//Adicionado
		bairroField = new JTextField();								//Adicionado
		enderecoRuaField = new JTextField();						//Adicionado
		estadoComboBox = new JComboBox<>();								//Adicionado
		tipoFuncionarioComboBox = new JComboBox<>();
		emailField = new JTextField();								//Adicionado
		enderecoNumeroField = new JTextField();						//Adicionado
		
		//JPanel
		painel1 = new JPanel(new GridLayout(1, 2));
		subPainel1de1 = new JPanel(new GridLayout(1, 2));
		subPainel2de1 = new JPanel(new GridLayout(1, 3));
		
		painel2 = new JPanel(new GridLayout(1, 2));
		painel3 = new JPanel(new GridLayout(1, 2));		
		painel4 = new JPanel(new GridLayout(1, 2));
		painel5 = new JPanel(new GridLayout(1, 2));	
		painel6 = new JPanel(new GridLayout(1, 4));
		painel7 = new JPanel(new GridLayout(1, 4));
		
		painel8 = new JPanel(new GridLayout(1, 2));
		subPainel1de8 = new JPanel(new GridLayout(1, 1));
		subPainel2de8 = new JPanel(new GridLayout(1, 2));
		
		painel9 = new JPanel(new GridLayout(1, 2));
		subPainel1de9 = new JPanel(new GridLayout(1, 1));
		subPainel2de9 = new JPanel(new GridLayout(1, 2));
		
		painel10 = new JPanel(new GridLayout(1, 2));
		painel11 = new JPanel(new GridLayout(1, 2));	
		
		painel12 = new JPanel(new GridLayout(1, 2));
		subPainel1de12 = new JPanel(new GridLayout(1, 1));
		subPainel2de12 = new JPanel(new GridLayout(1, 2));
		
		painel13 = new JPanel(new GridLayout(1, 2));
		subPainel1de13 = new JPanel(new GridLayout(1, 1));
		subPainel2de13 = new JPanel(new GridLayout(1, 2));
		
		painel14 = new JPanel(new GridLayout(1, 3));
	}
	
	public void editarObjetos(){
		estadoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"AC", "AL", 
				"AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", 
				"PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" }));
		estadoComboBox.setSelectedItem("PE");
		
		tipoFuncionarioComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Administrador", 
				"Setor de Animais", "Setor de Plantações", "Super Administrador"}));
		
		sexoGroup.add(masculinoRadioButton);
		sexoGroup.add(femininoRadioButton);		
		
		tipoFuncionarioLabel.setFont(getFonteNegrito());		
		tipoFuncionarioComboBox.setFont(getFonteNormal());		
		sexoLabel.setFont(getFonteNegrito());		
		masculinoRadioButton.setFont(getFonteNormal());
		femininoRadioButton.setFont(getFonteNormal());
		nomeLabel.setFont(getFonteNegrito());
		emailLabel.setFont(getFonteNegrito());
		nomeField.setFont(getFonteNormal());
		emailField.setFont(getFonteNormal());
		cpfLabel.setFont(getFonteNegrito());
		nascimentoLabel.setFont(getFonteNegrito());
		cpfField.setFont(getFonteNormal());
		nascimentoField.setFont(getFonteNormal());
		cepLabel.setFont(getFonteNegrito());
		estadoLabel.setFont(getFonteNegrito());
		cidadeLabel.setFont(getFonteNegrito());
		bairroLabel.setFont(getFonteNegrito());
		cepField.setFont(getFonteNormal());
		estadoComboBox.setFont(getFonteNormal());
		cidadeField.setFont(getFonteNormal());
		bairroField.setFont(getFonteNormal());
		enderecoRuaLabel.setFont(getFonteNegrito());
		enderecoNumeroLabel.setFont(getFonteNegrito());
		complementoEnderecoLabel.setFont(getFonteNegrito());
		enderecoRuaField.setFont(getFonteNormal());
		enderecoNumeroField.setFont(getFonteNormal());
		complementoEnderecoField.setFont(getFonteNormal());
		telefone1Label.setFont(getFonteNegrito());
		celularLabel.setFont(getFonteNegrito());
		telefone1Field.setFont(getFonteNormal());
		celularField.setFont(getFonteNormal());
		loginLabel.setFont(getFonteNegrito());
		senhaLabel.setFont(getFonteNegrito());
		confirmarSenhaLabel.setFont(getFonteNegrito());
		loginField.setFont(getFonteNormal());
		senhaField.setFont(getFonteNormal());
		confirmarSenhaField.setFont(getFonteNormal());
		cadastrarFuncionarioButton.setFont(getFonteNegrito());
			
		//subPainel1de1
		subPainel1de1.add(tipoFuncionarioLabel);
		subPainel1de1.add(tipoFuncionarioComboBox);
		//subPainel2de1
		subPainel2de1.add(sexoLabel);
		subPainel2de1.add(masculinoRadioButton);
		subPainel2de1.add(femininoRadioButton);
		//painel1
		painel1.add(subPainel1de1);
		painel1.add(subPainel2de1);
		
		//painel2
		painel2.add(nomeLabel);
		painel2.add(emailLabel);
		
		//painel3
		painel3.add(nomeField);
		painel3.add(emailField);
		
		//painel4
		painel4.add(cpfLabel);
		painel4.add(nascimentoLabel);
		
		//painel5
		painel5.add(cpfField);
		painel5.add(nascimentoField);
		
		//painel6
		painel6.add(cepLabel);
		painel6.add(estadoLabel);
		painel6.add(cidadeLabel);
		painel6.add(bairroLabel);
		
		//painel7
		painel7.add(cepField);
		painel7.add(estadoComboBox);
		painel7.add(cidadeField);
		painel7.add(bairroField);
		
		//subPainel1de8
		subPainel1de8.add(enderecoRuaLabel);
		//subPainel2de8
		subPainel2de8.add(enderecoNumeroLabel);
		subPainel2de8.add(complementoEnderecoLabel);
		//painel8
		painel8.add(subPainel1de8);
		painel8.add(subPainel2de8);
		
		//subPainel1de9
		subPainel1de9.add(enderecoRuaField);
		//subPainel2de9
		subPainel2de9.add(enderecoNumeroField);
		subPainel2de9.add(complementoEnderecoField);
		//painel9
		painel9.add(subPainel1de9);
		painel9.add(subPainel2de9);
		
		//painel10
		painel10.add(telefone1Label);
		painel10.add(celularLabel);
		
		//painel11
		painel11.add(telefone1Field);
		painel11.add(celularField);
		
		//subPainel1de12
		subPainel1de12.add(loginLabel);
		//subPainel2de12
		subPainel2de12.add(senhaLabel);
		subPainel2de12.add(confirmarSenhaLabel);
		//painel12
		painel12.add(subPainel1de12);
		painel12.add(subPainel2de12);
		
		//subPainel1de13
		subPainel1de13.add(loginField);
		//subPainel2de13
		subPainel2de13.add(senhaField);
		subPainel2de13.add(confirmarSenhaField);
		//painel13
		painel13.add(subPainel1de13);
		painel13.add(subPainel2de13);
		
		//painel14
		painel14.add(new Label(""));
		painel14.add(cadastrarFuncionarioButton);
		painel14.add(new Label(""));
	}
	
	public void adicionarObjetos(){
		add(painel1);
		add(painel2);
		add(painel3);
		add(painel4);
		add(painel5);
		add(painel6);
		add(painel7);
		add(painel8);
		add(painel9);
		add(painel10);
		add(painel11);
		add(painel12);
		add(painel13);
		add(painel14);
	}
	public JTextField getNomeField() {
		return nomeField;
	}
	public JTextField getCidadeField() {
		return cidadeField;
	}
	public JTextField getBairroField() {
		return bairroField;
	}
	public JTextField getEnderecoRuaField() {
		return enderecoRuaField;
	}
	public JTextField getEnderecoNumeroField() {
		return enderecoNumeroField;
	}
	public JTextField getEmailField() {
		return emailField;
	}
	public JTextField getComplementoEnderecoField() {
		return complementoEnderecoField;
	}
	public JTextField getLoginField() {
		return loginField;
	}
	public JPasswordField getSenhaField() {
		return senhaField;
	}
	public JPasswordField getConfirmarSenhaField() {
		return confirmarSenhaField;
	}
	public JFormattedTextField getNascimentoField() {
		return nascimentoField;
	}
	public JFormattedTextField getCpfField() {
		return cpfField;
	}
	public JFormattedTextField getCepField() {
		return cepField;
	}
	public JFormattedTextField getTelefone1Field() {
		return telefone1Field;
	}
	public JFormattedTextField getCelularField() {
		return celularField;
	}
	public JRadioButton getMasculinoRadioButton() {
		return masculinoRadioButton;
	}
	public JRadioButton getFemininoRadioButton() {
		return femininoRadioButton;
	}
	public JButton getCadastrarFuncionarioButton() {
		return cadastrarFuncionarioButton;
	}
	public JComboBox<String> getEstadoComboBox() {
		return estadoComboBox;
	}
	public JComboBox<String> getTipoFuncionarioComboBox() {
		return tipoFuncionarioComboBox;
	}		
}