package br.com.avaliacao.dto;

import javax.validation.constraints.NotBlank;

import br.com.avaliacao.model.Agendamento;

public class RequisicaoNovoAgendamento {
	
	@NotBlank
	private String contaOrigem;
	
	@NotBlank
	private String contaDestino;
	
	@NotBlank
	private String valorTransTaxa;
	
	@NotBlank
	private String dataTransferencia;
	
	@NotBlank
	private String dataAgendamento;
	
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}
	public String getValorTransTaxa() {
		return valorTransTaxa;
	}
	public void setValorTransTaxa(String valorTransTaxa) {
		this.valorTransTaxa = valorTransTaxa;
	}
	public String getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public Agendamento toAgendamento() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaOrigem(contaOrigem);
		agendamento.setContaDestino(contaDestino);
		agendamento.setDataAgendamento(dataAgendamento);
		agendamento.setDataTransferencia(dataTransferencia);
		agendamento.setValorTransTaxa(valorTransTaxa);
		return agendamento;
	}
}
