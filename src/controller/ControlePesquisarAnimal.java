package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.App;
import business.AnimalBusiness;
import business.HistoricoAnimalBusiness;
import model.Animal;
import model.AnimalNaoEncontradoException;
import model.FuncionarioNaoEncontradoException;
import model.HistoricoAnimal;
import model.NenhumaLinhaSelecionadaException;
import model.TabelaGenerica;
import view.EditarAnimal;
import view.EditarFuncionario;
import view.ExibirDetalhesAnimal;
import view.ExibirDetalhesFuncionario;
import view.Mensagem;
import view.PesquisarAnimal;

public class ControlePesquisarAnimal implements ActionListener{
	
	private PesquisarAnimal pesquisarAnimal;
	private AnimalBusiness animalBusiness;
	private TabelaGenerica tabelaGenerica;
	private HistoricoAnimalBusiness historicoAnimalBusiness;
	private HistoricoAnimal historicoAnimal;
	private Animal animal;
	
	public ControlePesquisarAnimal(PesquisarAnimal pesquisarAnimal) {
		this.pesquisarAnimal = pesquisarAnimal;
		
		animalBusiness = new AnimalBusiness();
		historicoAnimalBusiness = new HistoricoAnimalBusiness();
		String colunas[] = {"Categoria", "Raça/Espécie", "Regime Alimentar", "Finalidade"};
		tabelaGenerica = new TabelaGenerica(colunas);
		
		pesquisarAnimal.getEditarAnimalButton().addActionListener(this);
		pesquisarAnimal.getExibirDetalhesAnimalButton().addActionListener(this);
		pesquisarAnimal.getBuscarButton().addActionListener(this);
		pesquisarAnimal.getDesativarButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pesquisarAnimal.getEditarAnimalButton()){
			//Controla a ação de editar o animal selecionado
			try {
				if(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from animal where id = :PARAMETRO";
					String parametro = pesquisarAnimal.getResultadoBuscaTable().getModel().getValueAt(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, parametro)); 
					
					EditarAnimal editarAnimal = new EditarAnimal(pesquisarAnimal.getPertenceDesktop().getDesktopPane().getSize(), 
						pesquisarAnimal.getPertenceDesktop(), pesquisarAnimal.getAnimais().get(0));
					ControleEditarAnimal controleEditarAnimal = new ControleEditarAnimal(editarAnimal);
					pesquisarAnimal.getPertenceDesktop().getDesktopPane().add(editarAnimal);
					editarAnimal.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Animal foi selecionado na tabela!");
				}
			} catch (AnimalNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		else if(e.getSource() == pesquisarAnimal.getExibirDetalhesAnimalButton()){
			//Controla a ação de exibir os detalhes do animal selecionado
			try {
				if(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow() != -1){
					String query = "select * from animal where id = :PARAMETRO";
					String parametro = pesquisarAnimal.getResultadoBuscaTable().getModel().getValueAt(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow(), 4).toString();
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, parametro));
				
					ExibirDetalhesAnimal exibirDetalhesAnimal = new ExibirDetalhesAnimal(pesquisarAnimal.getPertenceDesktop().getDesktopPane().getSize(), pesquisarAnimal.getPertenceDesktop(), pesquisarAnimal.getAnimais().get(0));
					ControleExibirDetalhesAnimal controleExibirDetalhesAnimal = new ControleExibirDetalhesAnimal(exibirDetalhesAnimal);
					pesquisarAnimal.getPertenceDesktop().getDesktopPane().add(exibirDetalhesAnimal);
					exibirDetalhesAnimal.toFront();
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Animal foi selecionado na tabela!");
				}
			} catch (AnimalNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		else if(e.getSource() == pesquisarAnimal.getDesativarButton()){
			//Controla a ação de desativar o animal selecionado
			try {
				if(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow() != -1){
					int id = (int) pesquisarAnimal.getResultadoBuscaTable().getModel().getValueAt(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow(), 4);
					
					animal = animalBusiness.pesquisarPersonalizado("select * from animal where id = :PARAMETRO and ativo = true", id+"").get(0);
					historicoAnimal = new HistoricoAnimal(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "Desativou", App.funcionarioLogado, animal);
					historicoAnimalBusiness.cadastrar(historicoAnimal);
					
					animalBusiness.desativar(id);
					tabelaGenerica.remover(pesquisarAnimal.getResultadoBuscaTable().getSelectedRow());					
				}
				else{
					throw new NenhumaLinhaSelecionadaException("Nenhum Animal foi selecionado na tabela!");
				}
				Mensagem mensagem = new Mensagem("O Animal foi desativado com Sucesso!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);	
			} catch (NenhumaLinhaSelecionadaException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (AnimalNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} catch (ParseException e1) {
				Mensagem mensagem = new Mensagem("Formato de data incorreto!", 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			}
		}
		else{
			try {
				String query = new String();
				
				switch (pesquisarAnimal.getOpcoesBuscaComboBox().getSelectedItem().toString()) {
				case "Categoria":{
					query = "select * from animal where categoria like :PARAMETRO and ativo = true";	
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, "%"+pesquisarAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Raça/Espécie":{					
					query = "select * from animal where raca_especie like :PARAMETRO and ativo = true";	
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, "%"+pesquisarAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
					
				case "Regime Alimentar":{
					query = "select * from animal where regime_alimentar like :PARAMETRO and ativo = true";
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, "%"+pesquisarAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}
				
				case "Finalidade":{
					query = "select * from animal where finalidade like :PARAMETRO and ativo = true";
					pesquisarAnimal.setAnimais(animalBusiness.pesquisarPersonalizado(query, "%"+pesquisarAnimal.getBuscaField().getText()+"%"));
					editarTabela();
					break;
				}

				default:
					break;
				}
			} catch (AnimalNaoEncontradoException e1) {
				Mensagem mensagem = new Mensagem(e1.getMessage(), 400);
				ControleMensagem controleMensagem = new ControleMensagem(mensagem);
			} 
		}
	}
	
	public void editarTabela(){	
		List<Object> listaAnimais = new ArrayList<>();

		for(int i = 0; i < pesquisarAnimal.getAnimais().size(); i++){
			listaAnimais.add(new Object[]{pesquisarAnimal.getAnimais().get(i).getCategoria(), 
				pesquisarAnimal.getAnimais().get(i).getRaca_especie(), pesquisarAnimal.getAnimais().get(i).getRegime_alimentar(), 
				pesquisarAnimal.getAnimais().get(i).getFinalidade(), pesquisarAnimal.getAnimais().get(i).getId()});
		}			
		
		pesquisarAnimal.getResultadoBuscaTable().setModel(tabelaGenerica);
		tabelaGenerica.addAll(listaAnimais);
	}

}
