package br.edu.ifms.entity;

public class Paciente {
	private int codigo;
	private  String nome;
	private String sexo;
	private String patologias;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPatologias() {
		return patologias;
	}
	public void setPatologias(String patologias) {
		this.patologias = patologias;
	}
	
	
	
}
