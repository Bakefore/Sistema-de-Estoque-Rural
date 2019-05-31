package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import model.Funcionario;
import model.HistoricoAnimal;
import model.HistoricoFuncionario;

public class ExibirHistoricoAnimal extends TelaInterna{	

	private JComboBox<String> opcoesBuscaComboBox;	
	private JFormattedTextField buscaField;
	private JTable resultadoBuscaTable;
	private JScrollPane barraDeRolagemParaTabelaScroll;
	private ArrayList<String> resultadoBuscaArray; //Adicionar elementos encontrados com o filtro
	private JButton buscarButton;
	private List<HistoricoAnimal> historicosList;
	private JPanel painelSuperior, painelInferior;
	private BorderLayout layout;
	
	public ExibirHistoricoAnimal(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Histórico de Animais", 1000, 600, true, true, true, dimensaoTela, pertenceDesktop);
		layout = new BorderLayout();
		setLayout(layout);
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
				
		setVisible(true);
	}
	
	public void instanciarObjetos(){
		resultadoBuscaArray = new ArrayList<>();
		
		painelSuperior = new JPanel(new BorderLayout(5, 0));
		
		String colunas[] = {"Data", "Funcionário Responsável", "Ação Realizada", "Raça Alterada"};
		
		//Cria uma Tabela com campos vazios caso o array tenha menos que 4 elementos
		if((resultadoBuscaArray.size() != 0) && (resultadoBuscaArray.size() < 4)){	
			String dados[][] = new String[4][4];
			criarTabela(dados,colunas);
		}
		else{
			String dados[][] = new String[resultadoBuscaArray.size()][resultadoBuscaArray.size()];
			criarTabela(dados,colunas);
		}		
		
		barraDeRolagemParaTabelaScroll = new JScrollPane(resultadoBuscaTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		buscaField = new JFormattedTextField();
		buscarButton = new JButton("Pesquisar");
		opcoesBuscaComboBox = new JComboBox<String>();
	}
	
	public void editarObjetos(){
		resultadoBuscaTable.setFont(getFonteNormal());
		resultadoBuscaTable.setRowHeight(30);
		resultadoBuscaTable.getTableHeader().setFont(getFonteNegrito());
		
		opcoesBuscaComboBox.addItem("Ação Realizada");
		opcoesBuscaComboBox.addItem("Data");
		opcoesBuscaComboBox.addItem("Raça Alterada");
		opcoesBuscaComboBox.addItem("Responsável");		
		opcoesBuscaComboBox.setSelectedIndex(0);
		
		resultadoBuscaTable.getTableHeader().setReorderingAllowed(false);
		resultadoBuscaTable.getTableHeader().setResizingAllowed(false);
		
		resultadoBuscaTable.getColumnModel().getColumn(0).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(2).setPreferredWidth(242);
		resultadoBuscaTable.getColumnModel().getColumn(3).setPreferredWidth(242);	
		
		buscarButton.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		
		//Configurando SetBounds
		
//		opcoesBuscaComboBox.setBounds(10, 0, 200, 40);
		opcoesBuscaComboBox.setFont(getFonteNegrito());
		
//		buscaField.setBounds(210, 0, 510, 40);
		buscaField.setFont(getFonteNormal());
		
//		buscarButton.setBounds(720, 0, 260, 40);
		buscarButton.setFont(getFonteNegrito());
		
//		barraDeRolagemParaTabelaScroll.setBounds(10, 40, 970, 525);
		
		//painelSuperior
		opcoesBuscaComboBox.setPreferredSize(new Dimension(200, 40));//200
		buscaField.setPreferredSize(new Dimension(528, 40));//510
		buscarButton.setPreferredSize(new Dimension(260, 40));//260			
		
		painelSuperior.add(opcoesBuscaComboBox, BorderLayout.WEST);
		painelSuperior.add(buscaField, BorderLayout.CENTER);
		painelSuperior.add(buscarButton, BorderLayout.EAST);
//		painelSuperior.setPreferredSize(new Dimension(980, 50));
	}
	
	public void adicionarObjetos(){
		add(barraDeRolagemParaTabelaScroll);
		add(painelSuperior, layout.NORTH);
	}
	
	//Adiciona do ArrayList para um Array de DADOS a fim de criar a JTable
	public void criarTabela(String dados[][], String colunas[]){	
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

	public JTable getResultadoBuscaTable() {
		return resultadoBuscaTable;
	}

	public void setResultadoBuscaTable(JTable resultadoBuscaTable) {
		this.resultadoBuscaTable = resultadoBuscaTable;
	}

	public List<HistoricoAnimal> getHistoricosList() {
		return historicosList;
	}

	public void setHistoricosList(List<HistoricoAnimal> historicosList) {
		this.historicosList = historicosList;
	}	
}
