package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plantacao")
public class Plantacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private boolean ativo;
	
	@Column(nullable=false)
	private Date data_cadastro;
	
	@Column(nullable=false)
	private Date data_inicio;
	
	@Column(nullable=false)
	private Date previsao_colheita;
	
	@Column(length=50, nullable=false)
	private String cultura;
	
	@Column(length=50, nullable=false)
	private String variedade;
	
	@Column(nullable=false)
	private float area;
	
	@Column(length=25, nullable=false)
	private String solo;
	
	@Column(length=25, nullable=false)
	private String porte;
	
	@Column(length=25, nullable=false)
	private String titularidade_terreno;
	
	@Column(length=25, nullable=false)
	private String tempo_de_vida;
	
	@Column(length=25, nullable=false)
	private String sistema_de_irrigacao;
	
	@Column(length=25, nullable=false)
	private String sistema_de_adubacao;
	
	@Column(length=25, nullable=false)
	private String defensivo_agricola;
	
	public Plantacao() {
		
	}

	public Plantacao(Date data_cadastro, Date data_inicio, Date previsao_colheita, String cultura, String variedade, float area,
			String solo, String porte, String titularidade_terreno, String tempo_de_vida, String sistema_de_irrigacao,
			String sistema_de_adubacao, String defensivo_agricola) {
		this.data_inicio = data_inicio;
		this.previsao_colheita = previsao_colheita;
		this.cultura = cultura;
		this.variedade = variedade;
		this.area = area;
		this.solo = solo;
		this.porte = porte;
		this.titularidade_terreno = titularidade_terreno;
		this.tempo_de_vida = tempo_de_vida;
		this.sistema_de_irrigacao = sistema_de_irrigacao;
		this.sistema_de_adubacao = sistema_de_adubacao;
		this.defensivo_agricola = defensivo_agricola;
		this.data_cadastro = data_cadastro;
		ativo = true;
	}

	public int getId() {
		return id;
	}	

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public Date getPrevisao_colheita() {
		return previsao_colheita;
	}

	public String getCultura() {
		return cultura;
	}

	public String getVariedade() {
		return variedade;
	}

	public float getArea() {
		return area;
	}

	public String getSolo() {
		return solo;
	}

	public String getPorte() {
		return porte;
	}

	public String getTitularidade_terreno() {
		return titularidade_terreno;
	}

	public String getTempo_de_vida() {
		return tempo_de_vida;
	}

	public String getSistema_de_irrigacao() {
		return sistema_de_irrigacao;
	}

	public String getSistema_de_adubacao() {
		return sistema_de_adubacao;
	}

	public String getDefensivo_agricola() {
		return defensivo_agricola;
	}
	
	public Date getData_cadastro() {
		return data_cadastro;
	}
}
