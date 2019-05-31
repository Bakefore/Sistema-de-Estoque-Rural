package model;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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
@Table(name="funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private boolean ativo;
	
	@Column(nullable=false)
	private Date data_cadastro;
	
	@Column(length=20, nullable=false)
	private String tipo_funcionario;
	
	@Column(length=9, nullable=false)
	private String sexo;
	
	@Column(length=255, nullable=false)
	private String nome;
	
	@Column(length=100)
	private String email;
	
	@Column(length=14, unique=false)
	private String cpf;
	
	@Column(nullable=false)
	private Date data_nascimento;
	
	@Column(length=14)
	private String telefone;
	
	@Column(length=14)
	private String celular;
	
	@Column(length=100, unique=false, nullable=false)
	private String login;
	
	@Column(length=255, nullable=false)
	private String senha;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	public Funcionario() {
		
	}
	
	public Funcionario(Date data_cadastro, String tipo_funcionario, String sexo, String nome, String email, String cpf,
			Date data_nascimento, String telefone, String celular, String login, String senha, Endereco endereco) {
		this.tipo_funcionario = tipo_funcionario;
		this.sexo = sexo;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.telefone = telefone;
		this.celular = celular;
		this.login = login;
		this.senha = senha;
		this.endereco = endereco;
		this.data_cadastro = data_cadastro;
		ativo = true;
	}
	
	public static String criptografar(String original) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}		
		
		algorithm.reset();
		return hexString.toString();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTipo_funcionario() {
		return tipo_funcionario;
	}

	public String getSexo() {
		return sexo;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCelular() {
		return celular;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}	

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}	
}
