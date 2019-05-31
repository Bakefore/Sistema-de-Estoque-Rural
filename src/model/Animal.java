package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="animal")
public class Animal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private boolean ativo;
	
	@Column(nullable=false)
	private Date data_cadastro;
	
	@Column(length=25, nullable=false)
	private String categoria;
	
	@Column(length=25, nullable=false)
	private String raca_especie;
	
	@Column(length=5, nullable=false)
	private String sexo;
	
	@Column(nullable=false)
	private Date data_nascimento;
	
	@Column(nullable=false)
	private float peso_medio;
	
	@Column(nullable=false)
	private float altura_media;
	
	@Column(length=25, nullable=false)
	private String regime_alimentar;
	
	@Column(length=25, nullable=false)
	private String finalidade;
	
	@Column(nullable=false)
	private int quantidade;
	
	public Animal(){
		
	}
	
	public Animal(Date data_cadastro, String categoria, String raca_especie, String sexo, Date data_nascimento, float peso_medio,
			float altura_media, String regime_alimentar, String finalidade, int quantidade) {
		this.categoria = categoria;
		this.raca_especie = raca_especie;
		this.sexo = sexo;
		this.data_nascimento = data_nascimento;
		this.peso_medio = peso_medio;
		this.altura_media = altura_media;
		this.regime_alimentar = regime_alimentar;
		this.finalidade = finalidade;
		this.quantidade = quantidade;
		this.data_cadastro = data_cadastro;
		ativo = true;
	}

	public int getId() {
		return id;
	}	
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getRaca_especie() {
		return raca_especie;
	}

	public String getSexo() {
		return sexo;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public float getPeso_medio() {
		return peso_medio;
	}

	public float getAltura_media() {
		return altura_media;
	}

	public String getRegime_alimentar() {
		return regime_alimentar;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public int getQuantidade() {
		return quantidade;
	}	
	
	public Date getData_cadastro() {
		return data_cadastro;
	}
}
