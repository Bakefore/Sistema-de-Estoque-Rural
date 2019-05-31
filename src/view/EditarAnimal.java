package view;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControleMensagem;
import model.Animal;

public class EditarAnimal extends TelaInterna{
	
	private JLabel especieLabel, racaLabel, sexoLabel, nascimentoLabel, pesoLabel, finalidadeLabel, 
	quantidadeLabel, regimeAlimentarLabel, alturaLabel, dataDeCadastroLabel;
	private JComboBox<String> especieComboBox, racaComboBox, sexoComboBox, finalidadeComboBox, 
	regimeAlimentarComboBox;
	private JTextField pesoField, quantidadeField, alturaField, dataDeCadastroField;
	private JFormattedTextField nascimentoField;
	private JButton editarAnimalButton;
	private Animal animal;
	
	public EditarAnimal(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop, Animal animal) {
		super("Editar Animal", 748, 420, false, true, false, dimensaoTela, pertenceDesktop);
		setLayout(null);		
		
		this.animal = animal;
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){	
		especieLabel = new JLabel("Categoria *");
		racaLabel = new JLabel("Raça/Espécie *");
		sexoLabel = new JLabel("Sexo *");
		nascimentoLabel = new JLabel("Data de Nascimento *");
		pesoLabel = new JLabel("Peso Médio em Quilos *");
		finalidadeLabel = new JLabel("Finalidade *");
		quantidadeLabel = new JLabel("Quantidade *");
		regimeAlimentarLabel = new JLabel("Regime Alimentar *");
		alturaLabel = new JLabel("Altura em Metros *");
		dataDeCadastroLabel = new JLabel("Data de Cadastro *");
		
		especieComboBox = new JComboBox<String>();
		racaComboBox = new JComboBox<String>();
		sexoComboBox = new JComboBox<String>();
		finalidadeComboBox = new JComboBox<String>();
		regimeAlimentarComboBox = new JComboBox<String>();
		
		try {
			nascimentoField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			Mensagem mensagem = new Mensagem("Apenas números são permitidos!", 400);
			ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
		}
		
		pesoField = new JTextField();
		quantidadeField = new JTextField();		
		alturaField = new JTextField();
		dataDeCadastroField = new JTextField();
		
		editarAnimalButton = new JButton("Editar");
	}
	
	public void editarObjetos(){
		especieComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Ave", "Asinino", 
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
		
		dataDeCadastroField.setEditable(false);
		
		//Atribui os valores aos campos das variáveis
		//Formata data de nascimento do animal
		DateFormat formatoAmericano = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatoAmericano.format(animal.getData_nascimento());
		
		especieComboBox.setSelectedItem(animal.getCategoria());		
		sexoComboBox.setSelectedItem(animal.getSexo());		
		regimeAlimentarComboBox.setSelectedItem(animal.getRegime_alimentar());
		nascimentoField.setText(dataFormatada);
		pesoField.setText(animal.getPeso_medio()+"");
		quantidadeField.setText(animal.getQuantidade()+"");
		alturaField.setText(animal.getAltura_media()+"");
		editarOpcoes(this);
		racaComboBox.setSelectedItem(animal.getRaca_especie());
		finalidadeComboBox.setSelectedItem(animal.getFinalidade());
		
		//Formata o valor da data de cadastro
		dataFormatada = formatoAmericano.format(animal.getData_cadastro());	
		dataDeCadastroField.setText(dataFormatada);
		
		dataDeCadastroLabel.setBounds(10, 0, 150, 40);
		dataDeCadastroLabel.setFont(getFonteNegrito());
		
		dataDeCadastroField.setBounds(8, 40, 229, 40);
		dataDeCadastroField.setFont(getFonteNormal());		
		
		especieLabel.setBounds(10, 80, 150, 40);
		especieLabel.setFont(getFonteNegrito());	
		
		racaLabel.setBounds(258, 80, 200, 40);		
		racaLabel.setFont(getFonteNegrito());
		
		sexoLabel.setBounds(507, 80, 200, 40);
		sexoLabel.setFont(getFonteNegrito());
		
		especieComboBox.setBounds(8, 120, 229, 40);
		especieComboBox.setFont(getFonteNormal());
		
		racaComboBox.setBounds(255, 120, 231, 40);
		racaComboBox.setFont(getFonteNormal());
		
		sexoComboBox.setBounds(505, 120, 223, 40);
		sexoComboBox.setFont(getFonteNormal());
		
		nascimentoLabel.setBounds(10, 160, 200, 40);
		nascimentoLabel.setFont(getFonteNegrito());
		
		pesoLabel.setBounds(258, 160, 200, 40);
		pesoLabel.setFont(getFonteNegrito());
		
		alturaLabel.setBounds(507, 160, 200, 40);
		alturaLabel.setFont(getFonteNegrito());
		
		nascimentoField.setBounds(8, 200, 229, 40);
		nascimentoField.setFont(getFonteNormal());
		
		pesoField.setBounds(255, 200, 231, 40);
		pesoField.setFont(getFonteNormal());
		
		alturaField.setBounds(505, 200, 223, 40);
		alturaField.setFont(getFonteNormal());
		
		regimeAlimentarLabel.setBounds(10, 240, 200, 40);
		regimeAlimentarLabel.setFont(getFonteNegrito());
		
		finalidadeLabel.setBounds(258, 240, 200, 40);
		finalidadeLabel.setFont(getFonteNegrito());	
		
		quantidadeLabel.setBounds(507, 240, 200, 40);
		quantidadeLabel.setFont(getFonteNegrito());
		
		regimeAlimentarComboBox.setBounds(8, 280, 229, 40);
		regimeAlimentarComboBox.setFont(getFonteNormal());
		
		finalidadeComboBox.setBounds(255, 280, 231, 40);
		finalidadeComboBox.setFont(getFonteNormal());		
		
		quantidadeField.setBounds(505, 280, 223, 40);
		quantidadeField.setFont(getFonteNormal());
		
		editarAnimalButton.setBounds(274, 340, 200, 40);
		editarAnimalButton.setFont(getFonteNegrito());
	}
	
	public void adicionarObjetos(){
		add(especieLabel);
		add(especieComboBox);
		add(racaLabel);
		add(racaComboBox);
		add(sexoLabel);
		add(sexoComboBox);
		add(nascimentoLabel);
		add(nascimentoField);
		add(pesoLabel);
		add(pesoField);
		add(finalidadeLabel);
		add(finalidadeComboBox);
		add(quantidadeLabel);
		add(quantidadeField);
		add(regimeAlimentarLabel);
		add(regimeAlimentarComboBox);
		add(editarAnimalButton);
		add(alturaLabel);
		add(alturaField);
		add(dataDeCadastroLabel);
		add(dataDeCadastroField);
	}
	
	public void editarOpcoes(EditarAnimal editarAnimal){
		switch (editarAnimal.getEspecieComboBox().getSelectedItem().toString()) {
		case "Ave": {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Abacot Ranger",
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
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
				"Produção de Ovos"}));
			break;
		}
		case "Asinino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Abyssinian", 
				"American mammoth", "Amiatina", "Andalusian", "Asinara", "Asno de las Encartaciones",
				"Balearic", "Bourbonnais", "Catalan", "Corsican", "Cotentin", "Grand Noir du Berry",
				"Grigio Siciliano", "Irish", "Majorera", "Maltese", "Martina Franca", "Miranda", 
				"Pantesco", "Pega", "Poitou", "Provence", "Pyrenean", "Romagnolo", "Sardinian", 
				"Zamorano-Leonés"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Transporte"}));
			break;
		}			
		case "Bovino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Aberdeen Angus",
				"Belgian Blue", "Blonde Daquitaine", "Bonsmara", "Braford", "Brahman", "Brangus", "Bravon",
				"Canchim", "Caracu", "Charolês", "Chianina", "Devon", "Gir", "Guzerá", "Hereford", "Indubrasil",
				"Limousin", "Marchigiana", "Mertolengo", "Nelore", "Piemontês", "Romagnolo", "Santa Gertrudis",
				"Shorthorn", "Simental", "Tabapuã", "Wagyu"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
				"Pecuária de Leite", "Transporte"}));
			break;
		}
		case "Bufalino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Carabao",
				"Jafarabadi", "Mediterrâneo", "Murrah"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
				"Pecuária de Leite", "Transporte"}));
			break;
		}
		case "Camarão de Água Doce" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Akiami paste",
				"Alaskan pink", "Alpheus heterochaelis", "Alpheus randalli", "Amano", "Ancylomenes magnificus",
				"Aristaeomorpha foliacea", "Aristaeopsis edwardsiana", "Atya gabonensis",
				"Atyopsis moluccensis", "Bee", "Botan", "Caramote", "Caridina babaulti",
				"Caridina dennerli", "Caridina gracilirostris", "Caridina serratirostris", "Cherry",
				"Chinese white", "Deep-water rose", "Fenneropenaeus merguiensis", "Freshwater",
				"Giant freshwater", "Giant tiger", "Halocaridina rubra", "Hokkai", "Hymenocera picta",
				"Indian", "Japanese glass", "Kuruma", "Lake", "Macrobrachium nipponense",
				"Metapenaeopsis barbata", "Moyebi", "Neocaridina denticulata denticulata",
				"Palaemon adspersus", "Palaemon serratus", "Palaemonetes paludosus", "Pandalus borealis",
				"Pandalus hypsinotus", "Pandalus montagui", "Pandalus platyceros", "Pederson cleaner",
				"Penaeus semisulcatus", "Riceland", "Sakura", "Shiba", "Tiger pistol",
				"Trachysalambria curvirostris", "White", "Whiteleg"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
			break;
		}
		case "Camarão de Água Salgada" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Akiami paste",
				"Alaskan pink", "Alpheus heterochaelis", "Alpheus randalli", "Amano", "Ancylomenes magnificus",
				"Aristaeomorpha foliacea", "Aristaeopsis edwardsiana", "Atya gabonensis",
				"Atyopsis moluccensis", "Bee", "Botan", "Caramote", "Caridina babaulti",
				"Caridina dennerli", "Caridina gracilirostris", "Caridina serratirostris", "Cherry",
				"Chinese white", "Deep-water rose", "Fenneropenaeus merguiensis", "Freshwater",
				"Giant freshwater", "Giant tiger", "Halocaridina rubra", "Hokkai", "Hymenocera picta",
				"Indian", "Japanese glass", "Kuruma", "Lake", "Macrobrachium nipponense",
				"Metapenaeopsis barbata", "Moyebi", "Neocaridina denticulata denticulata",
				"Palaemon adspersus", "Palaemon serratus", "Palaemonetes paludosus", "Pandalus borealis",
				"Pandalus hypsinotus", "Pandalus montagui", "Pandalus platyceros", "Pederson cleaner",
				"Penaeus semisulcatus", "Riceland", "Sakura", "Shiba", "Tiger pistol",
				"Trachysalambria curvirostris", "White", "Whiteleg"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
			break;
		}
		case "Caprino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Alpine", 
				"American Lamancha", "Anglo-Nubian", "Angora", "Appenzell", "Barbari", "Beetal", "Black Bengal", 
				"Boer", "Booted", "British Alpine", "Chamois Coloured", "Changthangi", "Damascus", 
				"Danish Landrace", "Dera Din Panah", "Dutch Landrace", "Fainting", "Girgentana",
				"Golden Guernsey", "Grisons Striped", "Irish", "Jamnapari", "Jining Grey", "Kalahari Red",
				"Kamori", "Kiko", "Kinder", "Laoshan", "Murciana", "Nigerian Dwarf", "Nigora", "Oberhasli", 
				"Pygmy", "Pygora", "Pyrenean", "Russian White", "Saanen", "Sahelian", "Sarda", "Spanish", 
				"Tauernscheck", "Thuringian", "Toggenburg", "Valais Blackneck", "Verata", "Zalawadi",
				"Zhongwei"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
				"Pecuária de Leite", "Pecuária de Lã"}));
			break;
		}			
		case "Equino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"American Saddlebred",
				"Andaluz", "Anglo-Árabe", "Árabe", "Appaloosa", "Berbere", "Brasileiro de Hipismo", "Bretão", 
				"Campolina", "Crioulo", "Danish Warmblood", "Falabella", "Finlandês", "Friesian", 
				"Mangalarga", "Mangalarga Marchador", "Mustang", "Oldenburg", "Paint Horse",
				"Percheron", "Pônei", "Puro Sangue Inglês", "Puro Sangue Lusitano", "Quarto de Milha",
				"Shire", "Sela Francesa"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Transporte", 
				"Esportes"}));
			break;
		}
		case "Ovino" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Awassi",
				"Badger Face Welsh Mountain", "Balwen Welsh Mountain", "Barbados Black Belly",
				"Beulah Speckled Face", "Black Welsh Mountain", "Border Cheviot", "Border Leicester",
				"Boreray", "British Milk", "Castlemilk Moorit", "Charollais", "Cheviot", 
				"Coopworth", "Corriedale", "Delaine Merino", "Devon Closewool", "Dorset Down",
				"Dorset Horn", "East Friesian", "Exmoor Horn", "Hebridean", "Herdwick",
				"Île-de-France", "Jacob", "Katahdin", "Kerry Hill", "Leicester Longwool",
				"Lincoln", "Lonk", "Merino", "Navajo-Churro", "Norfolk Horn", "North Country Cheviot",
				"Ouessant", "Perendale", "Polypay", "Romanov", "Romney", "Royal White", "Ryeland",
				"Scottish Blackface", "Soay", "Southdown", "Suffolk", "Swaledale", "Targhee", 
				"Teeswater", "Texel", "Welsh Mountain", "Wiltshire Horn"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
				"Pecuária de Leite", "Pecuária de Lã"}));
			break;
		}
		case "Peixe" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cachapira",
				"Cachara", "Carpa", "Pintachara", "Pintado", "Surubim", "Tambacu", "Tambaqui", "Tambatinga",
				"Tilápia"}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
			break;
		}
		case "Suíno" : {
			editarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Canastra",
				"Canastrão", "Caruncho", "Duroc Jersey", "Hampshire", "Junqueira", "Landrace",
				"Large White", "Macau", "Moura", "Nilo", "Pereira", "Piau", "Pietrain", "Pirapitinga",
				"Sorocaba"	}));
			editarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
			break;
		}

		default:
			break;
		}
	}

	public JComboBox<String> getEspecieComboBox() {
		return especieComboBox;
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

	public JButton getEditarAnimalButton() {
		return editarAnimalButton;
	}

	public JTextField getDataDeCadastroField() {
		return dataDeCadastroField;
	}

	public Animal getAnimal() {
		return animal;
	}	
	
}
