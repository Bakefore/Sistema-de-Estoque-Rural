package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ControleEntrar;

public class DesktopPrincipal extends Tela{	//Tela principal que terá os internalFrames
	
	private JDesktopPane desktopPane;
	private JMenuBar barraDeMenu;
	private JMenu usuarioMenu, animalMenu, plantaMenu, funcionarioMenu, historicoMenu;
	private JMenuItem entrarItem, sairItem, alterarSenha, cadastrarAnimal, pesquisarAnimal, cadastrarPlantacao, 
	pesquisarPlantacao, cadastrarFuncionario, pesquisarFuncionario, historicoFuncionarioItem, historicoAnimalItem, historicoPlantacaoitem;
	
	
	public DesktopPrincipal() {
		super("Sistema de Estoque Rural", 1014, 694, true);	//60px na altura do JMenuBar - 14px de margem de erro
		setMinimumSize(new Dimension(1014, 694));	//Menor Tamanho da tela de desktop
		setExtendedState(MAXIMIZED_BOTH);	//Inicia a tela já maximinizada
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());				
		
		editarLookAndFeel();
		instanciarObjetos();
		editarObjetos();
		adicionarObjetos();
		
		setVisible(true);
	}
	
	public void instanciarObjetos(){	//Controlar as instancias 
		desktopPane = new JDesktopPane();
		barraDeMenu = new JMenuBar();
		
		//Instanciando JMenu
		usuarioMenu = new JMenu("Usuário");
		funcionarioMenu = new JMenu("Funcionário");
		animalMenu = new JMenu("Animais");
		plantaMenu = new JMenu("Plantação");
		historicoMenu = new JMenu("Histórico");
		
		//Instanciando JMenuItem
		entrarItem = new JMenuItem("Entrar");
		alterarSenha = new JMenuItem("Alterar Senha");
		sairItem = new JMenuItem("Sair");
		
		cadastrarFuncionario = new JMenuItem("Cadastar Funcionário");
		pesquisarFuncionario = new JMenuItem("Pesquisar Funcionário");
		
		cadastrarAnimal = new JMenuItem("Cadastrar Animal");
		pesquisarAnimal = new JMenuItem("Pesquisar Animal");
		
		cadastrarPlantacao = new JMenuItem("Cadastrar Plantação");
		pesquisarPlantacao = new JMenuItem("Pesquisar Plantação");	
		
		historicoFuncionarioItem = new JMenuItem("Histórico de Funcionários");
		historicoAnimalItem = new JMenuItem("Histórico de Animais");
		historicoPlantacaoitem = new JMenuItem("Histórico de Plantações");
		
		//Editando ícones dos JMenu
		usuarioMenu.setIcon(new ImageIcon("resource/img/icon/usuario32.png"));
		funcionarioMenu.setIcon(new ImageIcon("resource/img/icon/funcionario32.png"));
		animalMenu.setIcon(new ImageIcon("resource/img/icon/animal32.png"));
		plantaMenu.setIcon(new ImageIcon("resource/img/icon/planta32.png"));
		historicoMenu.setIcon(new ImageIcon("resource/img/icon/historico32.png"));
		
		//Editando ícones dos JMenuItem		
		entrarItem.setIcon(new ImageIcon("resource/img/icon/entrar16.png"));
		alterarSenha.setIcon(new ImageIcon("resource/img/icon/editar16.png"));
		sairItem.setIcon(new ImageIcon("resource/img/icon/sair16.png"));
		
		cadastrarFuncionario.setIcon(new ImageIcon("resource/img/icon/cadastrarFuncionario16.png"));
		pesquisarFuncionario.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		
		cadastrarAnimal.setIcon(new ImageIcon("resource/img/icon/cadastrarAnimal16.png"));
		pesquisarAnimal.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		
		cadastrarPlantacao.setIcon(new ImageIcon("resource/img/icon/cadastrarPlanta16.png"));
		pesquisarPlantacao.setIcon(new ImageIcon("resource/img/icon/pesquisar16.png"));
		
		historicoFuncionarioItem.setIcon(new ImageIcon("resource/img/icon/cadastrarFuncionario16.png"));
		historicoAnimalItem.setIcon(new ImageIcon("resource/img/icon/historicoAnimal16.png"));
		historicoPlantacaoitem.setIcon(new ImageIcon("resource/img/icon/historicoPlantacao16.png"));
	}
	
	public void editarObjetos(){	//Controlar as edições 
		setContentPane(desktopPane);
		setJMenuBar(barraDeMenu);
		
		//Adicionando os JMenu ao JMenuBar
		barraDeMenu.add(usuarioMenu);
		barraDeMenu.add(funcionarioMenu);
		barraDeMenu.add(animalMenu);
		barraDeMenu.add(plantaMenu);
		barraDeMenu.add(historicoMenu);
		
		//Associando JMenuItems com os devidos JMenus
		//usuarioMenu
		usuarioMenu.add(entrarItem);
		usuarioMenu.add(alterarSenha);
		usuarioMenu.add(sairItem);
		
		//funcionarioMenu
		funcionarioMenu.add(cadastrarFuncionario);
		funcionarioMenu.add(pesquisarFuncionario);
		
		//animalMenu
		animalMenu.add(cadastrarAnimal);
		animalMenu.add(pesquisarAnimal);
		
		//plantaMenu
		plantaMenu.add(cadastrarPlantacao);
		plantaMenu.add(pesquisarPlantacao);
		
		//historicoMenu
		historicoMenu.add(historicoFuncionarioItem);
		historicoMenu.add(historicoAnimalItem);
		historicoMenu.add(historicoPlantacaoitem);
		
		//Editando JItems
		sairItem.setVisible(false);
		alterarSenha.setVisible(false);
		funcionarioMenu.setVisible(false);
		animalMenu.setVisible(false);
		plantaMenu.setVisible(false);
		historicoMenu.setVisible(false);
	}
	
	public void adicionarObjetos(){		//Adicionar Objetos no JFrame
		
	}
	
	public void editarLookAndFeel(){
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuItem getEntrarItem() {
		return entrarItem;
	}

	public JMenuItem getSairItem() {
		return sairItem;
	}

	public JMenu getUsuarioMenu() {
		return usuarioMenu;
	}

	public JMenu getAnimalMenu() {
		return animalMenu;
	}

	public JMenu getPlantaMenu() {
		return plantaMenu;
	}

	public JMenu getFuncionarioMenu() {
		return funcionarioMenu;
	}

	public JMenuItem getAlterarSenha() {
		return alterarSenha;
	}

	public JMenuItem getCadastrarAnimal() {
		return cadastrarAnimal;
	}

	public JMenuItem getPesquisarAnimal() {
		return pesquisarAnimal;
	}

	public JMenuItem getCadastrarPlantacao() {
		return cadastrarPlantacao;
	}

	public JMenuItem getPesquisarPlantacao() {
		return pesquisarPlantacao;
	}

	public JMenuItem getCadastrarFuncionario() {
		return cadastrarFuncionario;
	}

	public JMenuItem getPesquisarFuncionario() {
		return pesquisarFuncionario;
	}

	public JMenu getHistoricoMenu() {
		return historicoMenu;
	}

	public JMenuItem getHistoricoFuncionarioItem() {
		return historicoFuncionarioItem;
	}

	public JMenuItem getHistoricoAnimalItem() {
		return historicoAnimalItem;
	}

	public JMenuItem getHistoricoPlantacaoitem() {
		return historicoPlantacaoitem;
	}	
}
