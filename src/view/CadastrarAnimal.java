package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastrarAnimal extends TelaInterna{
	
	private JLabel categoriaLabel, racaLabel, sexoLabel, nascimentoLabel, pesoLabel, finalidadeLabel, 
	quantidadeLabel, regimeAlimentarLabel, alturaLabel;
	private JComboBox<String> categoriaComboBox, racaComboBox, sexoComboBox, finalidadeComboBox, 
	regimeAlimentarComboBox;
	private JTextField pesoField, quantidadeField, alturaField;
	private JFormattedTextField nascimentoField;
	private JButton cadastrarAnimalButton;
	private JPanel painel1, painel2, painel3, painel4, painel5, painel6, painel7; 
	
	public CadastrarAnimal(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Cadastrar Animais", 748, 340, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(7, 1));		
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){	
		categoriaLabel = new JLabel("Categoria *");
		racaLabel = new JLabel("Raça/Espécie *");
		sexoLabel = new JLabel("Sexo *");
		nascimentoLabel = new JLabel("Data de Nascimento *");
		pesoLabel = new JLabel("Peso Médio em Quilos *");
		finalidadeLabel = new JLabel("Finalidade *");
		quantidadeLabel = new JLabel("Quantidade *");
		regimeAlimentarLabel = new JLabel("Regime Alimentar *");
		alturaLabel = new JLabel("Altura em Metros *");
		
		categoriaComboBox = new JComboBox<String>();
		racaComboBox = new JComboBox<String>();
		sexoComboBox = new JComboBox<String>();
		finalidadeComboBox = new JComboBox<String>();
		regimeAlimentarComboBox = new JComboBox<String>();
		
		try {
			nascimentoField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pesoField = new JTextField();
		quantidadeField = new JTextField();		
		alturaField = new JTextField();
		
		cadastrarAnimalButton = new JButton("Cadastrar");
		
		//JPanel
		painel1 = new JPanel(new GridLayout(1, 3));
		painel2 = new JPanel(new GridLayout(1, 3));
		painel3 = new JPanel(new GridLayout(1, 3));
		painel4 = new JPanel(new GridLayout(1, 3));
		painel5 = new JPanel(new GridLayout(1, 3));
		painel6 = new JPanel(new GridLayout(1, 3));
		painel7 = new JPanel(new GridLayout(1, 3));
	}
	
	public void editarObjetos(){
		categoriaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Ave", "Asinino", 
			"Bovino", "Bufalino", "Camarão de Água Doce", "Camarão de Água Salgada", "Caprino", "Equino", 
			"Ovino", "Peixe", "Suíno"}));
				
		racaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Abacot Ranger",
			"Alyesbury", "Ancona", "Ardósia", "Australorp", "Bantam", "Black Neck", "Blue Neck",
			"Blue Swedish", "Brahma", "Branco de Beltsville", "Branco gigante", "Bronzeado americano",
			"Bronzeado gigante", "Buff Orpington", "Call duck", "Cayuga", "Cochin", "Combatente Asil",
			"Combatente Shamo", "Combatente Sumatra", "Cornish", "Golden Cascade",
			"Holandês branco", "Jersey Giant", "Khaki Campbell", "Kromsnaveleend", "Leghorn",
			"Marreco de Topete", "Minorca", "New Hampshire", "Orpington", "Pato Bravo",
			"Pato Mandarim", "Pato Mergulhão", "Pato Selvagem", "Pato cristado",
			"Pato de crista", "Pato do ártico", "Pato-Mudo", "Pekin", "Plymouth Rock Barrada",
			"Plymouth Rock Branca", "Plymouth Rock", "Raça negra", "Red Neck", "Rhode Island Red",
			"Rhode Island", "Rouen", "Silver Appleyard", "Sussex", "Swedish Yellow", "Turken",
			"Vermelho bourbon", "Welsh Harlequin"}));		
		
		sexoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Macho", "Fêmea"}));
		finalidadeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
			"Produção de Ovos"}));
		regimeAlimentarComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária extensiva",
			"Pecuária intensiva", "Pecuária semi-extensiva"}));
		
		categoriaLabel.setFont(getFonteNegrito());			
		racaLabel.setFont(getFonteNegrito());
		sexoLabel.setFont(getFonteNegrito());
		categoriaComboBox.setFont(getFonteNormal());
		racaComboBox.setFont(getFonteNormal());
		sexoComboBox.setFont(getFonteNormal());
		nascimentoLabel.setFont(getFonteNegrito());
		pesoLabel.setFont(getFonteNegrito());
		alturaLabel.setFont(getFonteNegrito());
		nascimentoField.setFont(getFonteNormal());
		pesoField.setFont(getFonteNormal());
		alturaField.setFont(getFonteNormal());
		regimeAlimentarLabel.setFont(getFonteNegrito());
		finalidadeLabel.setFont(getFonteNegrito());	
		quantidadeLabel.setFont(getFonteNegrito());
		regimeAlimentarComboBox.setFont(getFonteNormal());
		finalidadeComboBox.setFont(getFonteNormal());	
		quantidadeField.setFont(getFonteNormal());
		cadastrarAnimalButton.setFont(getFonteNegrito());
		
		//painel1
		painel1.add(categoriaLabel);
		painel1.add(racaLabel);
		painel1.add(sexoLabel);
		//painel2
		painel2.add(categoriaComboBox);
		painel2.add(racaComboBox);
		painel2.add(sexoComboBox);
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
		painel6.add(regimeAlimentarComboBox);
		painel6.add(finalidadeComboBox);
		painel6.add(quantidadeField);
		//painel7
		painel7.add(new JLabel(""));
		painel7.add(cadastrarAnimalButton);
		painel7.add(new JLabel(""));
	}
	
	public void adicionarObjetos(){
		add(painel1);
		add(painel2);
		add(painel3);
		add(painel4);
		add(painel5);
		add(painel6);
		add(painel7);		
	}

	public JComboBox<String> getCategoriaComboBox() {
		return categoriaComboBox;
	}

	public JComboBox<String> getRacaComboBox() {
		return racaComboBox;
	}

	public JComboBox<String> getSexoComboBox() {
		return sexoComboBox;
	}

	public JComboBox<String> getFinalidadeComboBox() {
		return finalidadeComboBox;
	}

	public JComboBox<String> getRegimeAlimentarComboBox() {
		return regimeAlimentarComboBox;
	}

	public JFormattedTextField getNascimentoField() {
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

	public JButton getCadastrarAnimalButton() {
		return cadastrarAnimalButton;
	}	
}
