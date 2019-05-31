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

public class CadastrarPlantacao extends TelaInterna{
	
	private JLabel dataInicioDaPlantacaoLabel, dataPrevisaoDaColheitaLabel, tipoDeCulturaLabel, variedadeLabel, areaCultivoLabel, 
	titularidadeTerrenoLabel, tempoDeVidaDaPlantacaoLabel, portePlantacaoLabel, tipoDeSoloLabel, sistemaDeIrrigacaoLabel, 
	adubacaoLabel, defensivoAgricolaLabel;
	private JComboBox<String> titularidadeTerrenoComboBox, portePlantacaoComboBox, tipoDeSoloComboBox, tempoDeVidaDaPlantacaoComboBox,
	sistemaDeIrrigacaoComboBox, adubacaoComboBox, defensivoAgricolaComboBox;
	private JTextField tipoDeCulturaField, variedadeField, areaCultivoField;
	private JFormattedTextField dataInicioDaPlantacaoField, dataPrevisaoDaColheitaField;
	private JButton cadastrarPlantacaoButton;
	private JPanel painel1, painel2, painel3, painel4, painel5, painel6, painel7, painel8, painel9; 
	
	public CadastrarPlantacao(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Cadastrar Plantação", 748, 420, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(9, 1));		
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){	
		dataInicioDaPlantacaoLabel = new JLabel("Início da Plantação *");
		dataPrevisaoDaColheitaLabel = new JLabel("Previsão para Colheita *");
		tipoDeCulturaLabel = new JLabel("Tipo de Cultura *");
		variedadeLabel = new JLabel("Variedade/Cultivar *");
		areaCultivoLabel = new JLabel("Área de Cultivo em Hectares*");
		titularidadeTerrenoLabel = new JLabel("Titularidade do Terreno *");
		tempoDeVidaDaPlantacaoLabel = new JLabel("Tempo de Vida *");
		portePlantacaoLabel = new JLabel("Porte da Plantação *");
		tipoDeSoloLabel = new JLabel("Tipo de Solo *");
		sistemaDeIrrigacaoLabel = new JLabel("Sistema de Irrigação *");
		adubacaoLabel = new JLabel("Adubação *");
		defensivoAgricolaLabel = new JLabel("Defensivo Agrícola *");
				
		titularidadeTerrenoComboBox = new JComboBox<String>();
		portePlantacaoComboBox = new JComboBox<String>();
		tempoDeVidaDaPlantacaoComboBox = new JComboBox<String>();
		tipoDeSoloComboBox = new JComboBox<String>();		
		sistemaDeIrrigacaoComboBox = new JComboBox<String>();
		adubacaoComboBox = new JComboBox<String>();
		defensivoAgricolaComboBox = new JComboBox<String>();
		
		variedadeField = new JTextField();
		areaCultivoField = new JTextField();
		
		try {
			dataInicioDaPlantacaoField = new JFormattedTextField(new MaskFormatter("##/##/####"));
			dataPrevisaoDaColheitaField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tipoDeCulturaField = new JTextField();		
		
		cadastrarPlantacaoButton = new JButton("Cadastrar");
		
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
		tipoDeSoloComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Aluvial", "Massapé",
			"Salmourão", "Terra Roxa" }));
		
		portePlantacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Arbustivo",
			"Arbóreo", "Subarbustivo", "Subarbóreo"}));		
		
		titularidadeTerrenoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Arrendado",
			"Próprio"}));
		titularidadeTerrenoComboBox.setSelectedIndex(1);
		
		tempoDeVidaDaPlantacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Perene",
			"Temporária"}));
		
		sistemaDeIrrigacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Fertirrigação",
			"Irrigação de Superfície", "Irrigação localizada", "Irrigação por aspersão", "Micro aspersão",
			"Não Irrigado", "Pivot central"}));
		
		adubacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Adubo Mineral",
			"Adubo Orgânico", "Sem Adubo"}));
		
		defensivoAgricolaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Acaricida",
			"Fungicida", "Herbicida", "Inseticida", "Nematicida", "Sem defensivo agrícola"}));
		
		dataInicioDaPlantacaoLabel.setFont(getFonteNegrito());			
		dataPrevisaoDaColheitaLabel.setFont(getFonteNegrito());
		tipoDeCulturaLabel.setFont(getFonteNegrito());
		dataInicioDaPlantacaoField.setFont(getFonteNormal());
		dataPrevisaoDaColheitaField.setFont(getFonteNormal());
		tipoDeCulturaField.setFont(getFonteNormal());
		variedadeLabel.setFont(getFonteNegrito());
		areaCultivoLabel.setFont(getFonteNegrito());
		tipoDeSoloLabel.setFont(getFonteNegrito());
		variedadeField.setFont(getFonteNormal());
		areaCultivoField.setFont(getFonteNormal());
		tipoDeSoloComboBox.setFont(getFonteNormal());
		portePlantacaoLabel.setFont(getFonteNegrito());
		titularidadeTerrenoLabel.setFont(getFonteNegrito());	
		tempoDeVidaDaPlantacaoLabel.setFont(getFonteNegrito());
		portePlantacaoComboBox.setFont(getFonteNormal());
		titularidadeTerrenoComboBox.setFont(getFonteNormal());		
		tempoDeVidaDaPlantacaoComboBox.setFont(getFonteNormal());
		sistemaDeIrrigacaoLabel.setFont(getFonteNegrito());
		adubacaoLabel.setFont(getFonteNegrito());
		defensivoAgricolaLabel.setFont(getFonteNegrito());
		sistemaDeIrrigacaoComboBox.setFont(getFonteNormal());
		adubacaoComboBox.setFont(getFonteNormal());
		defensivoAgricolaComboBox.setFont(getFonteNormal());
		cadastrarPlantacaoButton.setFont(getFonteNegrito());
		
		//painel1
		painel1.add(dataInicioDaPlantacaoLabel);
		painel1.add(dataPrevisaoDaColheitaLabel);
		painel1.add(tipoDeCulturaLabel);
		//painel2
		painel2.add(dataInicioDaPlantacaoField);
		painel2.add(dataPrevisaoDaColheitaField);
		painel2.add(tipoDeCulturaField);
		//painel3
		painel3.add(variedadeLabel);
		painel3.add(areaCultivoLabel);
		painel3.add(tipoDeSoloLabel);
		//painel4
		painel4.add(variedadeField);
		painel4.add(areaCultivoField);
		painel4.add(tipoDeSoloComboBox);
		//painel5
		painel5.add(portePlantacaoLabel);
		painel5.add(titularidadeTerrenoLabel);
		painel5.add(tempoDeVidaDaPlantacaoLabel);
		//painel6
		painel6.add(portePlantacaoComboBox);
		painel6.add(titularidadeTerrenoComboBox);
		painel6.add(tempoDeVidaDaPlantacaoComboBox);
		//painel7
		painel7.add(sistemaDeIrrigacaoLabel);
		painel7.add(adubacaoLabel);
		painel7.add(defensivoAgricolaLabel);
		//painel8
		painel8.add(sistemaDeIrrigacaoComboBox);
		painel8.add(adubacaoComboBox);
		painel8.add(defensivoAgricolaComboBox);
		//painel9
		painel9.add(new JLabel(""));
		painel9.add(cadastrarPlantacaoButton);
		painel9.add(new JLabel(""));
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
	}

	public JFormattedTextField getDataInicioDaPlantacaoField() {
		return dataInicioDaPlantacaoField;
	}

	public JFormattedTextField getDataPrevisaoDaColheitaField() {
		return dataPrevisaoDaColheitaField;
	}

	public JTextField getTipoDeCulturaField() {
		return tipoDeCulturaField;
	}

	public JComboBox<String> getTitularidadeTerrenoComboBox() {
		return titularidadeTerrenoComboBox;
	}

	public JComboBox<String> getPortePlantacaoComboBox() {
		return portePlantacaoComboBox;
	}

	public JTextField getVariedadeField() {
		return variedadeField;
	}

	public JTextField getAreaCultivoField() {
		return areaCultivoField;
	}

	public JComboBox<String> getTempoDeVidaComboBox() {
		return tempoDeVidaDaPlantacaoComboBox;
	}

	public JComboBox<String> getTipoDeSoloComboBox() {
		return tipoDeSoloComboBox;
	}

	public JButton getCadastrarPlantacaoButton() {
		return cadastrarPlantacaoButton;
	}

	public JComboBox<String> getTempoDeVidaDaPlantacaoComboBox() {
		return tempoDeVidaDaPlantacaoComboBox;
	}

	public JComboBox<String> getSistemaDeIrrigacaoComboBox() {
		return sistemaDeIrrigacaoComboBox;
	}

	public JComboBox<String> getAdubacaoComboBox() {
		return adubacaoComboBox;
	}

	public JComboBox<String> getDefensivoAgricolaComboBox() {
		return defensivoAgricolaComboBox;
	}	
}
