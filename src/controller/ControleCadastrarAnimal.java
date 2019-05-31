package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import business.AnimalBusiness;
import business.HistoricoAnimalBusiness;
import model.Animal;
import model.AnimalNaoEncontradoException;
import model.CampoVazioException;
import model.HistoricoAnimal;
import view.CadastrarAnimal;
import view.Mensagem;

public class ControleCadastrarAnimal implements ActionListener{
	
	private CadastrarAnimal cadastrarAnimal;	
	private AnimalBusiness animalBusiness;
	private Animal animal;
	private HistoricoAnimalBusiness historicoAnimalBusiness;
	private HistoricoAnimal historicoAnimal;
	
	public ControleCadastrarAnimal(CadastrarAnimal cadastrarAnimal) {
		this.cadastrarAnimal = cadastrarAnimal;
		
		animalBusiness = new AnimalBusiness();
		historicoAnimalBusiness = new HistoricoAnimalBusiness();
		
		cadastrarAnimal.getCategoriaComboBox().addActionListener(this);
		cadastrarAnimal.getCadastrarAnimalButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cadastrarAnimal.getCategoriaComboBox()){
			//Controla a ação de clicar no botão de espécie	
			switch (cadastrarAnimal.getCategoriaComboBox().getSelectedItem().toString()) {
			case "Ave": {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Abacot Ranger",
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
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
					"Produção de Ovos"}));
				break;
			}
			case "Asinino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Abyssinian", 
					"American mammoth", "Amiatina", "Andalusian", "Asinara", "Asno de las Encartaciones",
					"Balearic", "Bourbonnais", "Catalan", "Corsican", "Cotentin", "Grand Noir du Berry",
					"Grigio Siciliano", "Irish", "Majorera", "Maltese", "Martina Franca", "Miranda", 
					"Pantesco", "Pega", "Poitou", "Provence", "Pyrenean", "Romagnolo", "Sardinian", 
					"Zamorano-Leonés"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Transporte"}));
				break;
			}			
			case "Bovino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Aberdeen Angus",
					"Belgian Blue", "Blonde Daquitaine", "Bonsmara", "Braford", "Brahman", "Brangus", "Bravon",
					"Canchim", "Caracu", "Charolês", "Chianina", "Devon", "Gir", "Guzerá", "Hereford", "Indubrasil",
					"Limousin", "Marchigiana", "Mertolengo", "Nelore", "Piemontês", "Romagnolo", "Santa Gertrudis",
					"Shorthorn", "Simental", "Tabapuã", "Wagyu"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
					"Pecuária de Leite", "Transporte"}));
				break;
			}
			case "Bufalino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Carabao",
					"Jafarabadi", "Mediterrâneo", "Murrah"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
					"Pecuária de Leite", "Transporte"}));
				break;
			}
			case "Camarão de Água Doce" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Akiami paste",
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
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
				break;
			}
			case "Camarão de Água Salgada" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Akiami paste",
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
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
				break;
			}
			case "Caprino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Alpine", 
					"American Lamancha", "Anglo-Nubian", "Angora", "Appenzell", "Barbari", "Beetal", "Black Bengal", 
					"Boer", "Booted", "British Alpine", "Chamois Coloured", "Changthangi", "Damascus", 
					"Danish Landrace", "Dera Din Panah", "Dutch Landrace", "Fainting", "Girgentana",
					"Golden Guernsey", "Grisons Striped", "Irish", "Jamnapari", "Jining Grey", "Kalahari Red",
					"Kamori", "Kiko", "Kinder", "Laoshan", "Murciana", "Nigerian Dwarf", "Nigora", "Oberhasli", 
					"Pygmy", "Pygora", "Pyrenean", "Russian White", "Saanen", "Sahelian", "Sarda", "Spanish", 
					"Tauernscheck", "Thuringian", "Toggenburg", "Valais Blackneck", "Verata", "Zalawadi",
					"Zhongwei"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
					"Pecuária de Leite", "Pecuária de Lã"}));
				break;
			}			
			case "Equino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"American Saddlebred",
					"Andaluz", "Anglo-Árabe", "Árabe", "Appaloosa", "Berbere", "Brasileiro de Hipismo", "Bretão", 
					"Campolina", "Crioulo", "Danish Warmblood", "Falabella", "Finlandês", "Friesian", 
					"Mangalarga", "Mangalarga Marchador", "Mustang", "Oldenburg", "Paint Horse",
					"Percheron", "Pônei", "Puro Sangue Inglês", "Puro Sangue Lusitano", "Quarto de Milha",
					"Shire", "Sela Francesa"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Transporte", 
					"Esportes"}));
				break;
			}
			case "Ovino" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Awassi",
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
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte", 
					"Pecuária de Leite", "Pecuária de Lã"}));
				break;
			}
			case "Peixe" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cachapira",
					"Cachara", "Carpa", "Pintachara", "Pintado", "Surubim", "Tambacu", "Tambaqui", "Tambatinga",
					"Tilápia"}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
				break;
			}
			case "Suíno" : {
				cadastrarAnimal.getRacaComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Canastra",
					"Canastrão", "Caruncho", "Duroc Jersey", "Hampshire", "Junqueira", "Landrace",
					"Large White", "Macau", "Moura", "Nilo", "Pereira", "Piau", "Pietrain", "Pirapitinga",
					"Sorocaba"	}));
				cadastrarAnimal.getFinalidadeComboBox().setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Pecuária de Corte"}));
				break;
			}

			default:
				break;
			}
		}
		
		else if(e.getSource() == cadastrarAnimal.getCadastrarAnimalButton()){
			//Controla a ação de cadastrar o animal na base de dados			
			try {
				animal = new Animal(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), cadastrarAnimal.getCategoriaComboBox().getSelectedItem().toString(), cadastrarAnimal.getRacaComboBox().getSelectedItem().toString(), 
					cadastrarAnimal.getSexoComboBox().getSelectedItem().toString(), new SimpleDateFormat("dd/MM/yyyy").parse(cadastrarAnimal.getNascimentoField().getText()), 
					Float.parseFloat(cadastrarAnimal.getPesoField().getText()), Float.parseFloat(cadastrarAnimal.getAlturaField().getText()), 
					cadastrarAnimal.getRegimeAlimentarComboBox().getSelectedItem().toString(), cadastrarAnimal.getFinalidadeComboBox().getSelectedItem().toString(), 
					Integer.parseInt(cadastrarAnimal.getQuantidadeField().getText()));
				
				animalBusiness.cadastrar(animal);
				
				animal = animalBusiness.pesquisarUltimoInserido();
				historicoAnimal = new HistoricoAnimal(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Cadastrou", App.funcionarioLogado, animal);
				historicoAnimalBusiness.cadastrar(historicoAnimal);
				
				Mensagem mensagem = new Mensagem("O Animal foi inserido com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
			} catch (NumberFormatException e1) {
				Mensagem mensagem = new Mensagem("Insira um valor numérico nos campos: peso, altura, quantidade!", 600);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (ParseException e1) {
				Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (CampoVazioException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (AnimalNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
			
		}
	}
}
