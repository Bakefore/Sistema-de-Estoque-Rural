package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Animal;

public class ExibirDetalhesAnimal extends TelaInterna{
	
	private JLabel categoriaLabel, racaLabel, sexoLabel, nascimentoLabel, pesoLabel, finalidadeLabel, 
	quantidadeLabel, regimeAlimentarLabel, alturaLabel, dataDeCadastroLabel;
	private JTextField nascimentoField, pesoField, quantidadeField, alturaField, dataDeCadastroField,
	categoriaField, racaField, sexoField, finalidadeField, regimeAlimentarField;
	private JButton editarAnimalButton;
	private Animal animal;
	private JPanel painel1, painel2, painel3, painel4, painel5, painel6, painel7, painel8, painel9; 
	
	public ExibirDetalhesAnimal(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop, Animal animal) {
		super("Detalhes do Animal", 748, 420, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(9, 1));	
		
		this.animal = animal;
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){	
		categoriaLabel = new JLabel("Categoria");
		racaLabel = new JLabel("Raça/Espécie");
		sexoLabel = new JLabel("Sexo");
		nascimentoLabel = new JLabel("Data de Nascimento");
		pesoLabel = new JLabel("Peso Médio em Quilos");
		finalidadeLabel = new JLabel("Finalidade");
		quantidadeLabel = new JLabel("Quantidade");
		regimeAlimentarLabel = new JLabel("Regime Alimentar");
		alturaLabel = new JLabel("Altura em Metros");
		dataDeCadastroLabel = new JLabel("Data de Cadastro");
		
		categoriaField = new JTextField();
		racaField = new JTextField();
		sexoField = new JTextField();
		finalidadeField = new JTextField();
		regimeAlimentarField = new JTextField();		
		nascimentoField = new JTextField();
		pesoField = new JTextField();
		quantidadeField = new JTextField();		
		alturaField = new JTextField();
		dataDeCadastroField = new JTextField();
		
		editarAnimalButton = new JButton("Editar");
		
		//JPanel
		painel1 = new JPanel(new GridLayout(1, 3));
		painel2 = new JPanel(new GridLayout(1, 3));
		painel3 = new JPanel(new GridLayout(1, 3));
		painel4 = new JPanel(new GridLayout(1, 3));
		painel5 = new JPanel(new GridLayout(1, 3));
		painel6 = new JPanel(new GridLayout(1, 3));
		painel7 = new JPanel(new GridLayout(1, 3));
		painel8 = new JPanel(new GridLayout(1, 3));
		painel9 = new JPanel(new GridLayout(1, 3));
	}
	
	public void editarObjetos(){		
		categoriaField.setEditable(false);
		racaField.setEditable(false);
		sexoField.setEditable(false);
		finalidadeField.setEditable(false);
		regimeAlimentarField.setEditable(false);
		nascimentoField.setEditable(false);
		pesoField.setEditable(false);
		quantidadeField.setEditable(false);
		alturaField.setEditable(false);
		dataDeCadastroField.setEditable(false);
		
		editarAnimalButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		
		//Atribui os valores aos campos das variáveis
		//Formata data de nascimento do animal
		DateFormat formatoAmericano = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatoAmericano.format(animal.getData_nascimento());
		
		categoriaField.setText(animal.getCategoria());
		racaField.setText(animal.getRaca_especie());
		sexoField.setText(animal.getSexo());
		finalidadeField.setText(animal.getFinalidade());
		regimeAlimentarField.setText(animal.getRegime_alimentar());
		nascimentoField.setText(dataFormatada);
		pesoField.setText(animal.getPeso_medio()+"");
		quantidadeField.setText(animal.getQuantidade()+"");
		alturaField.setText(animal.getAltura_media()+"");
		
		//Formata o valor da data de cadastro
		dataFormatada = formatoAmericano.format(animal.getData_cadastro());	
		dataDeCadastroField.setText(dataFormatada);
		
//		dataDeCadastroLabel.setBounds(10, 0, 150, 40);
		dataDeCadastroLabel.setFont(getFonteNegrito());
		
//		dataDeCadastroField.setBounds(8, 40, 229, 40);
		dataDeCadastroField.setFont(getFonteNormal());		
		
//		especieLabel.setBounds(10, 80, 150, 40);
		categoriaLabel.setFont(getFonteNegrito());	
		
//		racaLabel.setBounds(258, 80, 200, 40);		
		racaLabel.setFont(getFonteNegrito());
		
//		sexoLabel.setBounds(507, 80, 200, 40);
		sexoLabel.setFont(getFonteNegrito());
		
//		especieField.setBounds(8, 120, 229, 40);
		categoriaField.setFont(getFonteNormal());
		
//		racaField.setBounds(255, 120, 231, 40);
		racaField.setFont(getFonteNormal());
		
//		sexoField.setBounds(505, 120, 223, 40);
		sexoField.setFont(getFonteNormal());
		
//		nascimentoLabel.setBounds(10, 160, 200, 40);
		nascimentoLabel.setFont(getFonteNegrito());
		
//		pesoLabel.setBounds(258, 160, 200, 40);
		pesoLabel.setFont(getFonteNegrito());
		
//		alturaLabel.setBounds(507, 160, 200, 40);
		alturaLabel.setFont(getFonteNegrito());
		
//		nascimentoField.setBounds(8, 200, 229, 40);
		nascimentoField.setFont(getFonteNormal());
		
//		pesoField.setBounds(255, 200, 231, 40);
		pesoField.setFont(getFonteNormal());
		
//		alturaField.setBounds(505, 200, 223, 40);
		alturaField.setFont(getFonteNormal());
		
//		regimeAlimentarLabel.setBounds(10, 240, 200, 40);
		regimeAlimentarLabel.setFont(getFonteNegrito());
		
//		finalidadeLabel.setBounds(258, 240, 200, 40);
		finalidadeLabel.setFont(getFonteNegrito());	
		
//		quantidadeLabel.setBounds(507, 240, 200, 40);
		quantidadeLabel.setFont(getFonteNegrito());
		
//		regimeAlimentarField.setBounds(8, 280, 229, 40);
		regimeAlimentarField.setFont(getFonteNormal());
		
//		finalidadeField.setBounds(255, 280, 231, 40);
		finalidadeField.setFont(getFonteNormal());		
		
//		quantidadeField.setBounds(505, 280, 223, 40);
		quantidadeField.setFont(getFonteNormal());
		
//		editarAnimalButton.setBounds(274, 340, 200, 40);
		editarAnimalButton.setFont(getFonteNegrito());
		
		//painel1
		painel1.add(categoriaLabel);
		painel1.add(racaLabel);
		painel1.add(sexoLabel);
		//painel2
		painel2.add(categoriaField);
		painel2.add(racaField);
		painel2.add(sexoField);
		//painel3
		painel3.add(nascimentoLabel);
		painel3.add(pesoLabel);
		painel3.add(alturaLabel);
		//painel4
		painel4.add(nascimentoField);
		painel4.add(pesoField);
		painel4.add(alturaField);
		//painel5
		painel5.add(regimeAlimentarLabel);
		painel5.add(finalidadeLabel);
		painel5.add(quantidadeLabel);
		//painel6
		painel6.add(regimeAlimentarField);
		painel6.add(finalidadeField);
		painel6.add(quantidadeField);
		//painel7
		painel7.add(new JLabel(""));
		painel7.add(editarAnimalButton);
		painel7.add(new JLabel(""));
		//painel8
		painel8.add(dataDeCadastroLabel);
		painel8.add(new JLabel(""));
		painel8.add(new JLabel(""));
		//painel9
		painel9.add(dataDeCadastroField);
		painel9.add(new JLabel(""));
		painel9.add(new JLabel(""));
	}
	
	public void adicionarObjetos(){
		add(painel8);
		add(painel9);
		add(painel1);
		add(painel2);
		add(painel3);
		add(painel4);
		add(painel5);
		add(painel6);
		add(painel7);	
//		add(categoriaLabel);
//		add(categoriaField);
//		add(racaLabel);
//		add(racaField);
//		add(sexoLabel);
//		add(sexoField);
//		add(nascimentoLabel);
//		add(nascimentoField);
//		add(pesoLabel);
//		add(pesoField);
//		add(finalidadeLabel);
//		add(finalidadeField);
//		add(quantidadeLabel);
//		add(quantidadeField);
//		add(regimeAlimentarLabel);
//		add(regimeAlimentarField);
//		add(editarAnimalButton);
//		add(alturaLabel);
//		add(alturaField);
//		add(dataDeCadastroLabel);
//		add(dataDeCadastroField);
	}

	public JTextField getCategoriaField() {
		return categoriaField;
	}

	public JTextField getRacaField() {
		return racaField;
	}

	public JTextField getSexoField() {
		return sexoField;
	}

	public JTextField getFinalidadeField() {
		return finalidadeField;
	}

	public JTextField getRegimeAlimentarField() {
		return regimeAlimentarField;
	}

	public JTextField getNascimentoField() {
		return nascimentoField;
	}

	public JTextField getPesoField() {
		return pesoField;
	}

	public JTextField getQuantidadeField() {
		return quantidadeField;
	}

	public JTextField getAlturaField() {
		return alturaField;
	}

	public JButton getEditarAnimalButton() {
		return editarAnimalButton;
	}

	public Animal getAnimal() {
		return animal;
	}	
}
