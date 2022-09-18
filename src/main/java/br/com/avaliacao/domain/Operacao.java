package br.com.avaliacao.domain;

public enum Operacao {
	
	A("A", "Tipo A"),
	B("B", "Tipo B"),
	C("C", "Tipo C"),
	D("D", "Tipo D");

	private String sigla;
	private String descricao;
	
	Operacao(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}

}