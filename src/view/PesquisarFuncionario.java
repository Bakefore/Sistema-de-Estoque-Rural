package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import model.Funcionario;

public class PesquisarFuncionario extends TelaInterna{	

	private JComboBox<String> opcoesBuscaComboBox;	
	private JFormattedTextField buscaField;
	private JTable resultadoBuscaTable;
	private JScrollPane barraDeRolagemParaTabelaScroll;
	private JButton buscarButton, editarFuncionarioButton, exibirDetalhesFuncionarioButton,
	resetarSenhaButton, desativarButton;
	private List<Funcionario> funcionarios;
	private JPanel painelSuperior, painelInferior;
	private BorderLayout layout;
	
	public PesquisarFuncionario(Dimension dimensaoTela, DesktopPrincipal pertenceDesktop) {
		super("Pesquisar Funcionário", 1000, 600, true, true, true, dimensaoTela, pertenceDesktop);
		layout = new BorderLayout();
		setLayout(layout);
		
		if(getPertenceDesktop().getDesktopPane().getHeight() < 600){
				//Inicia a tela já maximinizada
		}
		
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
				
		setVisible(true);
	}
	
	public void instanciarObjetos(){		
		criarTabela();
		
		painelSuperior = new JPanel(new BorderLayout(5, 0));
		painelInferior = new JPanel(new GridLayout(1, 4));
		
		barraDeRolagemParaTabelaScroll = new JScrollPane(resultadoBuscaTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		buscaField = new JFormattedTextField();
		buscarButton = new JButton("Pesquisar");
		editarFuncionarioButton = new JButton("Editar");
		opcoesBuscaComboBox = new JComboBox<String>();
		exibirDetalhesFuncionarioButton = new JButton("Visualizar");
		resetarSenhaButton = new JButton("Resetar Senha");
		desativarButton = new JButton("Desativar");
	}
	
	public void editarObjetos(){
		resultadoBuscaTable.setFont(getFonteNormal());
		resultadoBuscaTable.setRowHeight(30);
		resultadoBuscaTable.getTableHeader().setFont(getFonteNegrito());
		verificarPermissaoParaResetSenha();
				
		opcoesBuscaComboBox.addItem("CPF");
		opcoesBuscaComboBox.addItem("Data de Nascimento");
		opcoesBuscaComboBox.addItem("Nome");
		opcoesBuscaComboBox.setSelectedIndex(2);
		
		resultadoBuscaTable.getTableHeader().setReorderingAllowed(false);
		resultadoBuscaTable.getTableHeader().setResizingAllowed(false);
		
		resultadoBuscaTable.getColumnModel().getColumn(0).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(1).setPreferredWidth(243);
		resultadoBuscaTable.getColumnModel().getColumn(2).setPreferredWidth(242);
		resultadoBuscaTable.getColumnModel().getColumn(3).setPreferredWidth(242);	
		
		buscarButton.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		editarFuncionarioButton.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		exibirDetalhesFuncionarioButton.setIcon(new ImageIcon("resource/img/icon/visualizar16.png"));
		resetarSenhaButton.setIcon(new ImageIcon("resource/img/icon/resetarSenha16.png"));
		desativarButton.setIcon(new ImageIcon("resource/img/icon/desativar16.png"));
		
		opcoesBuscaComboBox.setFont(getFonteNegrito());
		buscaField.setFont(getFonteNormal());
		buscarButton.setFont(getFonteNegrito());
		exibirDetalhesFuncionarioButton.setFont(getFonteNegrito());
		editarFuncionarioButton.setFont(getFonteNegrito());
		resetarSenhaButton.setFont(getFonteNegrito());
		desativarButton.setFont(getFonteNegrito());
		
		//painelSuperior
		opcoesBuscaComboBox.setPreferredSize(new Dimension(200, 40));//200
		buscaField.setPreferredSize(new Dimension(528, 40));//528
		buscarButton.setPreferredSize(new Dimension(260, 40));//260			
		
		painelSuperior.add(opcoesBuscaComboBox, BorderLayout.WEST);
		painelSuperior.add(buscaField, BorderLayout.CENTER);
		painelSuperior.add(buscarButton, BorderLayout.EAST);
		
		//painelInferior
		painelInferior.add(exibirDetalhesFuncionarioButton);
		painelInferior.add(editarFuncionarioButton);
		painelInferior.add(resetarSenhaButton);
		painelInferior.add(desativarButton);
		painelInferior.setPreferredSize(new Dimension(980, 40));		
	}
	
	public void adicionarObjetos(){
		add(barraDeRolagemParaTabelaScroll);
		add(painelSuperior, layout.NORTH);
		add(painelInferior, layout.SOUTH);
	}
	
	public void verificarPermissaoParaResetSenha(){
		if(!App.funcionarioLogado.getTipo_funcionario().equals("Super Administrador")){
			resetarSenhaButton.setEnabled(false);
		}
	}
	
	//Adiciona do ArrayList para um Array de DADOS a fim de criar a JTable
	public void criarTabela(){		
		String colunas[] = {"Nome", "CPF", "Data de Nascimento", "Sexo"};
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

	public JButton getEditarFuncionarioButton() {
		return editarFuncionarioButton;
	}

	public JButton getExibirDetalhesFuncionarioButton() {
		return exibirDetalhesFuncionarioButton;
	}

	public JTable getResultadoBuscaTable() {
		return resultadoBuscaTable;
	}

	public void setResultadoBuscaTable(JTable resultadoBuscaTable) {
		this.resultadoBuscaTable = resultadoBuscaTable;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public JButton getResetarSenhaButton() {
		return resetarSenhaButton;
	}

	public JButton getDesativarButton() {
		return desativarButton;
	}	
}
