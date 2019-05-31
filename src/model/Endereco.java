package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String rua;
	
	@Column(nullable=false)
	private int numero;
	
	@Column
	private String complemento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bairro bairro;
	
	public Endereco() {
		
	}
	
	public Endereco(String rua, int numero, String complemento, Bairro bairro) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
	}

	public int getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public int getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public Bairro getBairro() {
		return bairro;
	}		
}
