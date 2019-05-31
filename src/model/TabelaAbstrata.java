package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TabelaAbstrata extends AbstractTableModel {
    private ArrayList dados = new ArrayList();
    private String[] colunas;    
    
    public TabelaAbstrata(ArrayList dados, String[] colunas) {
        setDados(dados);
        setColunas(colunas);
    }
    
    public TabelaAbstrata() {
    	
    }
    
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }
    
    @Override
    public int getRowCount() {
        return getDados().size();
    }
    
    @Override
    public int getColumnCount() {
        return getColunas().length;
    }
    
    @Override
	public Object getValueAt(int dados, int coluna) {           
    	Object[] dadosLinhas = (Object[])getDados().get(dados);
    	return dadosLinhas[coluna];
	}
    
    public void atualizarTabela(Funcionario funcionario) {
    	DateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada;
    	dataFormatada = formatoBrasileiro.format(funcionario.getData_nascimento());
        dados.add(new Object[]{funcionario.getNome(), funcionario.getCpf(), dataFormatada, funcionario.getSexo(), funcionario.getId()});
        fireTableDataChanged();
    }
    
    public ArrayList getDados() {
        return dados;
    }

    public void setDados(ArrayList dados) {
        this.dados = dados;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}