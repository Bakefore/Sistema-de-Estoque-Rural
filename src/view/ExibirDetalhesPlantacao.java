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

import model.Plantacao;

public class ExibirDetalhesPlantacao extends TelaInterna{
	
	private JLabel dataInicioDaPlantacaoLabel, dataPrevisaoDaColheitaLabel, tipoDeCulturaLabel, variedadeLabel, areaCultivoLabel, 
	titularidadeTerrenoLabel, tempoDeVidaDaPlantacaoLabel, portePlantacaoLabel, tipoDeSoloLabel, sistemaDeIrrigacaoLabel, 
	adubacaoLabel, defensivoAgricolaLabel, dataDeCadastroLabel;
	private JTextField tipoDeCulturaField, dataInicioDaPlantacaoField, dataPrevisaoDaColheitaField, variedadeField, areaCultivoField,
	dataDeCadastroField, titularidadeTerrenoField, portePlantacaoField, tipoDeSoloField, tempoDeVidaDaPlantacaoField,
	sistemaDeIrrigacaoField, adubacaoField, defensivoAgricolaField;
	private JButton editarPlantacaoButton;
	private Plantacao plantacao;
	private JPanel painel1, painel2, painel3, painel4, painel5, painel6, painel7, painel8, painel9, 
	painel10, painel11; 
	
	public ExibirDetalhesPlantacao(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop, Plantacao plantacao) {
		super("Detalhes da Plantação", 748, 500, true, true, true, dimensaoTela, pertenceDesktop);
		setLayout(new GridLayout(11, 1));	
		
		this.plantacao = plantacao;
		
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
		areaCultivoLabel = new JLabel("Área de Cultivo *");
		titularidadeTerrenoLabel = new JLabel("Titularidade do Terreno *");
		tempoDeVidaDaPlantacaoLabel = new JLabel("Tempo de Vida *");
		portePlantacaoLabel = new JLabel("Porte da Plantação *");
		tipoDeSoloLabel = new JLabel("Tipo de Solo *");
		sistemaDeIrrigacaoLabel = new JLabel("Sistema de Irrigação *");
		adubacaoLabel = new JLabel("Adubação *");
		defensivoAgricolaLabel = new JLabel("Defensivo Agrícola *");
		dataDeCadastroLabel = new JLabel("Data de Cadastro *");
				
		titularidadeTerrenoField = new JTextField();
		portePlantacaoField = new JTextField();
		tempoDeVidaDaPlantacaoField = new JTextField();
		tipoDeSoloField = new JTextField();		
		sistemaDeIrrigacaoField = new JTextField();
		adubacaoField = new JTextField();
		defensivoAgricolaField = new JTextField();		
		variedadeField = new JTextField();
		areaCultivoField = new JTextField();
		dataInicioDaPlantacaoField = new JTextField();
		dataPrevisaoDaColheitaField = new JTextField();
		tipoDeCulturaField = new JTextField();		
		dataDeCadastroField = new JTextField();
		
		editarPlantacaoButton = new JButton("Editar");
		
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
		painel10 = new JPanel(new GridLayout(1, 3));
		painel11 = new JPanel(new GridLayout(1, 3));
	}
	
	public void editarObjetos(){	
		titularidadeTerrenoField.setEditable(false);
		portePlantacaoField.setEditable(false);
		tempoDeVidaDaPlantacaoField.setEditable(false);
		tipoDeSoloField.setEditable(false);
		sistemaDeIrrigacaoField.setEditable(false);
		adubacaoField.setEditable(false);
		defensivoAgricolaField.setEditable(false);
		variedadeField.setEditable(false);
		areaCultivoField.setEditable(false);
		dataInicioDaPlantacaoField.setEditable(false);
		dataPrevisaoDaColheitaField.setEditable(false);
		tipoDeCulturaField.setEditable(false);
		dataDeCadastroField.setEditable(false);
		
		editarPlantacaoButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		
		//Atribui os valores aos campos das variáveis
		//Formata data de início da plantação
		DateFormat formatoAmericano = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatoAmericano.format(plantacao.getData_inicio());
		
		titularidadeTerrenoField.setText(plantacao.getTitularidade_terreno());
		portePlantacaoField.setText(plantacao.getPorte());
		tempoDeVidaDaPlantacaoField.setText(plantacao.getTempo_de_vida());
		tipoDeSoloField.setText(plantacao.getSolo());
		sistemaDeIrrigacaoField.setText(plantacao.getSistema_de_irrigacao());
		adubacaoField.setText(plantacao.getSistema_de_adubacao());
		defensivoAgricolaField.setText(plantacao.getDefensivo_agricola());
		variedadeField.setText(plantacao.getVariedade());
		areaCultivoField.setText(plantacao.getArea()+"");
		dataInicioDaPlantacaoField.setText(dataFormatada);
		
		dataFormatada = formatoAmericano.format(plantacao.getPrevisao_colheita());
		
		dataPrevisaoDaColheitaField.setText(dataFormatada);
		tipoDeCulturaField.setText(plantacao.getCultura());
		
		dataFormatada = formatoAmericano.format(plantacao.getData_cadastro());
		
		dataDeCadastroField.setText(dataFormatada);
		
		dataDeCadastroLabel.setFont(getFonteNegrito());
		dataDeCadastroField.setFont(getFonteNormal());	
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
		tipoDeSoloField.setFont(getFonteNormal());
		portePlantacaoLabel.setFont(getFonteNegrito());
		titularidadeTerrenoLabel.setFont(getFonteNegrito());	
		tempoDeVidaDaPlantacaoLabel.setFont(getFonteNegrito());
		portePlantacaoField.setFont(getFonteNormal());
		titularidadeTerrenoField.setFont(getFonteNormal());		
		tempoDeVidaDaPlantacaoField.setFont(getFonteNormal());
		sistemaDeIrrigacaoLabel.setFont(getFonteNegrito());
		adubacaoLabel.setFont(getFonteNegrito());
		defensivoAgricolaLabel.setFont(getFonteNegrito());
		sistemaDeIrrigacaoField.setFont(getFonteNormal());
		adubacaoField.setFont(getFonteNormal());
		defensivoAgricolaField.setFont(getFonteNormal());
		editarPlantacaoButton.setFont(getFonteNegrito());
		
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
		painel4.add(tipoDeSoloField);
		//painel5
		painel5.add(portePlantacaoLabel);
		painel5.add(titularidadeTerrenoLabel);
		painel5.add(tempoDeVidaDaPlantacaoLabel);
		//painel6
		painel6.add(portePlantacaoField);
		painel6.add(titularidadeTerrenoField);
		painel6.add(tempoDeVidaDaPlantacaoField);
		//painel7
		painel7.add(sistemaDeIrrigacaoLabel);
		painel7.add(adubacaoLabel);
		painel7.add(defensivoAgricolaLabel);
		//painel8
		painel8.add(sistemaDeIrrigacaoField);
		painel8.add(adubacaoField);
		painel8.add(defensivoAgricolaField);
		//painel9
		painel9.add(new JLabel(""));
		painel9.add(editarPlantacaoButton);
		painel9.add(new JLabel(""));
		//painel10
		painel10.add(dataDeCadastroLabel);
		painel10.add(new JLabel(""));
		painel10.add(new JLabel(""));
		//painel11
		painel11.add(dataDeCadastroField);
		painel11.add(new JLabel(""));
		painel11.add(new JLabel(""));
	}
	
	public void adicionarObjetos(){
		add(painel10);
		add(painel11);
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

	public JTextField getEspecieField() {
		return dataInicioDaPlantacaoField;
	}

	public JTextField getRacaField() {
		return dataPrevisaoDaColheitaField;
	}

	public JTextField getSexoField() {
		return tipoDeCulturaField;
	}

	public JTextField getTitularidadeTerrenoField() {
		return titularidadeTerrenoField;
	}

	public JTextField getPortePlantacaoField() {
		return portePlantacaoField;
	}

	public JTextField getVariedadeField() {
		return variedadeField;
	}

	public JTextField getAreaCultivoField() {
		return areaCultivoField;
	}

	public JTextField getQuantidadeField() {
		return tempoDeVidaDaPlantacaoField;
	}

	public JTextField getTipoDeSoloField() {
		return tipoDeSoloField;
	}

	public JButton getEditarPlantacaoButton() {
		return editarPlantacaoButton;
	}

	public Plantacao getPlantacao() {
		return plantacao;
	}
}
