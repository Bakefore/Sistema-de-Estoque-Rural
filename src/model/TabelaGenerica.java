package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelaGenerica extends AbstractTableModel{
	private List<Object> dados;
	private String[] colunas;	

	public TabelaGenerica(String[] colunas) {
		this.dados = new ArrayList<>();
		this.colunas = colunas;
	}
	
	public void addAll(List<Object> dados) {
		if(dados != null) {
			this.dados.clear();
			this.dados.addAll(dados);
			fireTableDataChanged();
		}
	}
	
	public void remover(int index) {
		dados.remove(index);
		fireTableDataChanged();
	}
	
	public Object getValor(int linha) {
		return dados.get(linha);
	}
	
	public boolean addValor(Object dado) {
		boolean operacao = dados.add(dado);
		if(operacao)
			fireTableRowsInserted(dados.size()-1, dados.size()-1);
		return operacao;
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public int getRowCount() {
		return dados.size();
	}
	
	@Override
	public Object getValueAt(int dados, int coluna) {           
    	Object[] dadosLinhas = (Object[])getDados().get(dados);
    	return dadosLinhas[coluna];
	}

	public List<Object> getDados() {
		return dados;
	}

	public void setDados(List<Object> dados) {
		this.dados = dados;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}	
}
