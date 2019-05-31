package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estado")
public class Estado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=2, unique=true, nullable=false)
	private String uf;
	
	public Estado() {
		
	}

	public Estado(String uf) {
		this.uf = uf;
	}

	public int getId() {
		return id;
	}

	public String getUf() {
		return uf;
	}	
}
