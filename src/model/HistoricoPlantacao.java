package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="historico_plantacao")
public class HistoricoPlantacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private Date data_realizacao;
	
	@Column(nullable=false)
	private String acao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Funcionario funcionarioLogado;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Plantacao plantacao;
	
	public HistoricoPlantacao() {
		
	}

	public HistoricoPlantacao(Date data_realizacao, String acao, Funcionario funcionarioLogado, Plantacao plantacao) {
		this.data_realizacao = data_realizacao;
		this.acao = acao;
		this.funcionarioLogado = funcionarioLogado;
		this.plantacao = plantacao;
	}

	public int getId() {
		return id;
	}

	public Date getData_realizacao() {
		return data_realizacao;
	}

	public String getAcao() {
		return acao;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public Plantacao getPlantacao() {
		return plantacao;
	}	
}
