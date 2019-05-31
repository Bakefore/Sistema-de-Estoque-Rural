package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.App;
import model.Animal;

public class PesquisarAnimal extends TelaInterna{	

	private JComboBox<String> opcoesBuscaComboBox;	
	private JFormattedTextField buscaField;
	private JTable resultadoBuscaTable;
	private JScrollPane barraDeRolagemParaTabelaScroll;
	private JButton buscarButton, editarAnimalButton, exibirDetalhesAnimalButton,
	desativarButton;
	private List<Animal> animais;
	private JPanel painelSuperior, painelInferior;
	private BorderLayout layout;
	
	public PesquisarAnimal(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Pesquisar Animal", 1000, 600, true, true, true, dimensaoTela, pertenceDesktop);
		layout = new BorderLayout();
		setLayout(layout);
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
				
		setVisible(true);
	}
	
	public void instanciarObjetos(){		
		criarTabela();	
		
		painelSuperior = new JPanel(new BorderLayout(5, 0));
		painelInferior = new JPanel(new GridLayout(1, 3));
		
		barraDeRolagemParaTabelaScroll = new JScrollPane(resultadoBuscaTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		buscaField = new JFormattedTextField();
		buscarButton = new JButton("Pesquisar");
		editarAnimalButton = new JButton("Editar");
		opcoesBuscaComboBox = new JComboBox<String>();
		exibirDetalhesAnimalButton = new JButton("Visualizar");
		desativarButton = new JButton("Desativar");
	}
	
	public void editarObjetos(){
		resultadoBuscaTable.setFont(getFonteNormal());
		resultadoBuscaTable.setRowHeight(30);
		resultadoBuscaTable.getTableHeader().setFont(getFonteNegrito());
		
		opcoesBuscaComboBox.addItem("Categoria");		
		opcoesBuscaComboBox.addItem("Finalidade");
		opcoesBuscaComboBox.addItem("Raça/Espécie");
		opcoesBuscaComboBox.addItem("Regime Alimentar");
		opcoesBuscaComboBox.setSelectedIndex(2);
		
		resultadoBuscaTable.getTableHeader().setReorderingAllowed(false);
		resultadoBuscaTable.getTableHeader().setResizingAllowed(false);
		resultadoBuscaTable.getColumnModel().getColumn(0).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(2).setPreferredWidth(242);
		resultadoBuscaTable.getColumnModel().getColumn(3).setPreferredWidth(242);	
		
		buscarButton.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		editarAnimalButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		exibirDetalhesAnimalButton.setIcon(new ImageIcon("resource/img/icon/visualizar16.png"));
		desativarButton.setIcon(new ImageIcon("resource/img/icon/desativar16.png"));
		
		//Configurando SetBounds		
//		opcoesBuscaComboBox.setBounds(10, 0, 200, 40);
		opcoesBuscaComboBox.setFont(getFonteNegrito());
		
//		buscaField.setBounds(210, 0, 510, 40);
		buscaField.setFont(getFonteNormal());
		
//		buscarButton.setBounds(720, 0, 260, 40);
		buscarButton.setFont(getFonteNegrito());
		
//		barraDeRolagemParaTabelaScroll.setBounds(10, 40, 970, 485);
		
//		exibirDetalhesAnimalButton.setBounds(260, 525, 160, 40);
		exibirDetalhesAnimalButton.setFont(getFonteNegrito());
		
//		editarAnimalButton.setBounds(420, 525, 160, 40);
		editarAnimalButton.setFont(getFonteNegrito());
		
//		desativarButton.setBounds(580, 525, 160, 40);
		desativarButton.setFont(getFonteNegrito());
		
		//painelSuperior
		opcoesBuscaComboBox.setPreferredSize(new Dimension(200, 40));//200
		buscaField.setPreferredSize(new Dimension(528, 40));//510
		buscarButton.setPreferredSize(new Dimension(260, 40));//260			
		
		painelSuperior.add(opcoesBuscaComboBox, BorderLayout.WEST);
		painelSuperior.add(buscaField, BorderLayout.CENTER);
		painelSuperior.add(buscarButton, BorderLayout.EAST);
//		painelSuperior.setPreferredSize(new Dimension(980, 50));
		
		//painelInferior
		painelInferior.add(exibirDetalhesAnimalButton);
		painelInferior.add(editarAnimalButton);
		painelInferior.add(desativarButton);
		painelInferior.setPreferredSize(new Dimension(980, 40));	
	}
	
	public void adicionarObjetos(){
		add(barraDeRolagemParaTabelaScroll);
		add(painelSuperior, layout.NORTH);
		add(painelInferior, layout.SOUTH);
	}
	
	//Adiciona do ArrayList para um Array de DADOS a fim de criar a JTable
	public void criarTabela(){	
		String colunas[] = {"Categoria", "Raça/Espécie", "Regime Alimentar", "Finalidade"};
		String dados[][] = new String[0][0];
		resultadoBuscaTable = new JTable(dados, colunas);
	}

	public JComboBox<String> getOpcoesBuscaComboBox() {
		return opcoesBuscaComboBox;
	}

	public JFormattedTextField getBuscaField() {
		return buscaField;
	}

	public JButton getBuscarButton() {
		return buscarButton;
	}

	public JButton getEditarAnimalButton() {
		return editarAnimalButton;
	}

	public JButton getExibirDetalhesAnimalButton() {
		return exibirDetalhesAnimalButton;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public JTable getResultadoBuscaTable() {
		return resultadoBuscaTable;
	}

	public JButton getDesativarButton() {
		return desativarButton;
	}	
	
}
