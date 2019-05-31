package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import business.BairroBusiness;
import business.CidadeBusiness;
import business.EnderecoBusiness;
import business.EstadoBusiness;
import business.FuncionarioBusiness;
import controller.ControleMensagem;
import model.Bairro;
import model.Cidade;
import model.Endereco;
import model.Estado;
import model.Funcionario;

public class ExibirDetalhesFuncionario extends TelaInterna{
	
	private JLabel nomeLabel, cpfLabel, nascimentoLabel, cidadeLabel, bairroLabel, enderecoRuaLabel, estadoLabel, cepLabel, emailLabel, 
	telefone1Label, celularLabel, enderecoNumeroLabel, sexoLabel, tipoFuncionarioLabel, complementoEnderecoLabel, loginLabel, 
	senhaLabel, dataDeCadastroLabel;
	private JTextField nomeField, cidadeField, bairroField, enderecoRuaField, enderecoNumeroField, 
	emailField, complementoEnderecoField, loginField, dataDeCadastroField, estadoField, 
	tipoFuncionarioField, sexoField;
	private JPasswordField senhaField;
	private JFormattedTextField nascimentoField, cpfField, cepField, telefone1Field, celularField;
	private Funcionario funcionario;
	private JButton editarFuncionarioButton;
	private JPanel painel1, subPainel1de1, subPainel2de1, painel2, painel3, painel4, painel5, painel6, 
	painel7, painel8, subPainel1de8, subPainel2de8, painel9, subPainel1de9, subPainel2de9, painel10, 
	painel11, painel12, painel13, painel14, painel15, painel16;

	public ExibirDetalhesFuncionario(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop, Funcionario funcionario) {
		super("Detalhes do Funcionário", 1000, 700, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(16, 1));		
		
		this.funcionario = funcionario;
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	public void instanciarObjetos(){
		
		nomeLabel = new JLabel("Nome");									//Adicionado
		cpfLabel = new JLabel("CPF");										//Adicionado
		nascimentoLabel = new JLabel("Data de Nascimento");				//Adicionado
		cidadeLabel = new JLabel("Cidade");								//Adicionado
		bairroLabel = new JLabel("Bairro");								//Adicionado
		enderecoRuaLabel = new JLabel("Rua");								//Adicionado
		estadoLabel = new JLabel("UF");								//Adicionado
		cepLabel = new JLabel("CEP");										//Adicionado
		emailLabel = new JLabel("E-mail");									//Adicionado
		telefone1Label = new JLabel("Telefone");							//Adicionado
		celularLabel = new JLabel("Celular");								//Adicionado		
		enderecoNumeroLabel = new JLabel("Número");						//Adicionado
		sexoLabel = new JLabel("Sexo");									//Adicionado
		tipoFuncionarioLabel = new JLabel("Tipo de Funcionário");			//Adicionado
		complementoEnderecoLabel = new JLabel("Complemento");				//Adicionado
		editarFuncionarioButton = new JButton("Editar Funcionário");	//Adicionado
		loginLabel = new JLabel("Login");									//Adicionado
		senhaLabel = new JLabel("Senha");									//Adicionado
		dataDeCadastroLabel = new JLabel("Data de Cadastro");
				
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
		complementoEnderecoField = new JTextField();				//Adicionado
		nomeField = new JTextField();								//Adicionado																
		cidadeField = new JTextField();								//Adicionado
		bairroField = new JTextField();								//Adicionado
		enderecoRuaField = new JTextField();						//Adicionado
		estadoField = new JTextField();								//Adicionado
		tipoFuncionarioField = new JTextField();
		emailField = new JTextField();								//Adicionado
		enderecoNumeroField = new JTextField();						//Adicionado
		dataDeCadastroField = new JTextField();
		sexoField = new JTextField();			//Adicionado
		
		//JPanel
		painel1 = new JPanel(new GridLayout(1, 2));
		subPainel1de1 = new JPanel(new GridLayout(1, 2));
		subPainel2de1 = new JPanel(new GridLayout(1, 2));
		
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
		
		painel13 = new JPanel(new GridLayout(1, 2));		
		painel14 = new JPanel(new GridLayout(1, 3));
		painel15 = new JPanel(new GridLayout(1, 3));
		painel16 = new JPanel(new GridLayout(1, 3));
	}
	
	public void editarObjetos(){				
		
		dataDeCadastroField.setEditable(false);		
		loginField.setEditable(false);
		senhaField.setEditable(false);
		complementoEnderecoField.setEditable(false);
		nomeField.setEditable(false);							
		cidadeField.setEditable(false);
		bairroField.setEditable(false);
		enderecoRuaField.setEditable(false); 
		estadoField.setEditable(false);
		tipoFuncionarioField.setEditable(false); 
		emailField.setEditable(false);
		enderecoNumeroField.setEditable(false);
		dataDeCadastroField.setEditable(false);
		sexoField.setEditable(false);
		nascimentoField.setEditable(false);
		cepField.setEditable(false);
		cpfField.setEditable(false);
		telefone1Field.setEditable(false);
		celularField.setEditable(false);
		
		editarFuncionarioButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		
		//Atribui os valores aos campos das variáveis
		//Formata data de nascimento do funcionário
		DateFormat formatoAmericano = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatoAmericano.format(funcionario.getData_nascimento());
		
		loginField.setText(funcionario.getLogin());
		senhaField.setText(funcionario.getSenha());
		complementoEnderecoField.setText(funcionario.getEndereco().getComplemento());
		nomeField.setText(funcionario.getNome());
		cidadeField.setText(funcionario.getEndereco().getBairro().getCidade().getNome());
		bairroField.setText(funcionario.getEndereco().getBairro().getNome());
		enderecoRuaField.setText(funcionario.getEndereco().getRua());
		estadoField.setText(funcionario.getEndereco().getBairro().getCidade().getEstado().getUf());
		tipoFuncionarioField.setText(funcionario.getTipo_funcionario());
		emailField.setText(funcionario.getEmail());
		enderecoNumeroField.setText(funcionario.getEndereco().getNumero()+"");
		nascimentoField.setText(dataFormatada);
		
		//Formata o valor da data de cadastro
		dataFormatada = formatoAmericano.format(funcionario.getData_cadastro());	
		
		dataDeCadastroField.setText(dataFormatada);
		sexoField.setText(funcionario.getSexo());				
		cpfField.setText(funcionario.getCpf());
		telefone1Field.setText(funcionario.getTelefone());
		celularField.setText(funcionario.getCelular());
				
		dataDeCadastroLabel.setFont(getFonteNegrito());
		dataDeCadastroField.setFont(getFonteNormal());	
		tipoFuncionarioLabel.setFont(getFonteNegrito());
		tipoFuncionarioField.setFont(getFonteNormal());
		sexoLabel.setFont(getFonteNegrito());
		sexoField.setFont(getFonteNormal());
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
		estadoField.setFont(getFonteNormal());
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
		loginField.setFont(getFonteNormal());
		senhaField.setFont(getFonteNormal());
		editarFuncionarioButton.setFont(getFonteNegrito());
		
		//subPainel1de1
		subPainel1de1.add(tipoFuncionarioLabel);
		subPainel1de1.add(tipoFuncionarioField);
		//subPainel2de1
		subPainel2de1.add(sexoLabel);
		subPainel2de1.add(sexoField);
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
		painel7.add(estadoField);
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
		
		//painel12
		painel12.add(loginLabel);
		painel12.add(senhaLabel);
		
		//painel13
		painel13.add(loginField);
		painel13.add(senhaField);
		
		//painel14
		painel14.add(new Label(""));
		painel14.add(editarFuncionarioButton);
		painel14.add(new Label(""));
		
		//painel15
		painel15.add(dataDeCadastroLabel);
		painel15.add(new Label(""));
		painel15.add(new Label(""));
		
		//painel16
		painel16.add(dataDeCadastroField);
		painel16.add(new Label(""));
		painel16.add(new Label(""));
	}
	
	public void adicionarObjetos(){
		add(painel15);
		add(painel16);
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
	public JTextField getSexoField() {
		return sexoField;
	}	
	public JButton getEditarFuncionarioButton() {
		return editarFuncionarioButton;
	}
	public JTextField getEstadoComboBox() {
		return estadoField;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}	
}