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
@Table(name="historico_animal")
public class HistoricoAnimal {
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
	private Animal animalAlvo;
	
	public HistoricoAnimal() {
		
	}

	public HistoricoAnimal(Date data_realizacao, String acao, Funcionario funcionarioLogado, Animal animalAlvo) {
		this.data_realizacao = data_realizacao;
		this.acao = acao;
		this.funcionarioLogado = funcionarioLogado;
		this.animalAlvo = animalAlvo;
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

	public Animal getAnimalAlvo() {
		return animalAlvo;
	}	
}
