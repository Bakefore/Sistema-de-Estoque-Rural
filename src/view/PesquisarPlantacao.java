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
import model.Plantacao;

public class PesquisarPlantacao extends TelaInterna{	

	private JComboBox<String> opcoesBuscaComboBox;	
	private JFormattedTextField buscaField;
	private JTable resultadoBuscaTable;
	private JScrollPane barraDeRolagemParaTabelaScroll;
	private JButton buscarButton, editarPlantacaoButton, exibirDetalhesPlantacaoButton,
	desativarButton;
	private List<Plantacao> plantacoes;
	private JPanel painelSuperior, painelInferior;
	private BorderLayout layout;
	
	public PesquisarPlantacao(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Pesquisar Plantação", 1000, 600, true, true, true, dimensaoTela, pertenceDesktop);
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
		editarPlantacaoButton = new JButton("Editar");
		opcoesBuscaComboBox = new JComboBox<String>();
		exibirDetalhesPlantacaoButton = new JButton("Visualizar");
		desativarButton = new JButton("Desativar");
	}
	
	public void editarObjetos(){
		resultadoBuscaTable.setFont(getFonteNormal());
		resultadoBuscaTable.setRowHeight(30);
		resultadoBuscaTable.getTableHeader().setFont(getFonteNegrito());
		
		opcoesBuscaComboBox.addItem("Área de Cultivo");
		opcoesBuscaComboBox.addItem("Porte da Plantação");
		opcoesBuscaComboBox.addItem("Tipo de Cultura");		
		opcoesBuscaComboBox.addItem("Titularidade do Terreno");		
		opcoesBuscaComboBox.setSelectedIndex(2);
		
		resultadoBuscaTable.getTableHeader().setReorderingAllowed(false);
		resultadoBuscaTable.getTableHeader().setResizingAllowed(false);
		resultadoBuscaTable.getColumnModel().getColumn(0).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(2).setPreferredWidth(242);
		resultadoBuscaTable.getColumnModel().getColumn(3).setPreferredWidth(242);	
		
		buscarButton.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		editarPlantacaoButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		exibirDetalhesPlantacaoButton.setIcon(new ImageIcon("resource/img/icon/visualizar16.png"));
		desativarButton.setIcon(new ImageIcon("resource/img/icon/desativar16.png"));
		
		//Configurando SetBounds
		
//		opcoesBuscaComboBox.setBounds(10, 0, 200, 40);
		opcoesBuscaComboBox.setFont(getFonteNegrito());
		
//		buscaField.setBounds(210, 0, 510, 40);
		buscaField.setFont(getFonteNormal());
		
//		buscarButton.setBounds(720, 0, 260, 40);
		buscarButton.setFont(getFonteNegrito());
		
//		barraDeRolagemParaTabelaScroll.setBounds(10, 40, 970, 485);
		
//		exibirDetalhesPlantacaoButton.setBounds(260, 525, 160, 40);
		exibirDetalhesPlantacaoButton.setFont(getFonteNegrito());
		
//		editarPlantacaoButton.setBounds(420, 525, 160, 40);
		editarPlantacaoButton.setFont(getFonteNegrito());
		
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
		painelInferior.add(exibirDetalhesPlantacaoButton);
		painelInferior.add(editarPlantacaoButton);
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
		String colunas[] = {"Tipo de Cultura", "Área de Cultivo", "Titularidade do Terreno", "Porte da Plantação"};
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

	public JButton getEditarPlantacaoButton() {
		return editarPlantacaoButton;
	}

	public JButton getExibirDetalhesPlantacaoButton() {
		return exibirDetalhesPlantacaoButton;
	}

	public List<Plantacao> getPlantacoes() {
		return plantacoes;
	}

	public void setPlantacoes(List<Plantacao> plantacoes) {
		this.plantacoes = plantacoes;
	}

	public JTable getResultadoBuscaTable() {
		return resultadoBuscaTable;
	}

	public JButton getDesativarButton() {
		return desativarButton;
	}	
	
}
