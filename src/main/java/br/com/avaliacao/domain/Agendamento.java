package br.com.avaliacao.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "AGENDAMENTOS")
public class Agendamento extends AbstractEntity<Long> {
	
	@NotBlank
	@Size(min = 6, max = 6, message = "Conta origem deve ter {max} caracteres")
	@Column(nullable = false, unique = true)
	private String contaOrigem;

	@NotBlank
	@Size(min = 6, max = 6, message = "Conta destino deve ter {max} caracteres")
	@Column(nullable = false, unique = true)
	private String contaDestino;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double valorTransferencia;
	
	@NotNull
	@FutureOrPresent(message = "{FutureOrPresent.agendamento.dataTransferencia}")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_transferencia", nullable = false, columnDefinition = "DATE")
	private LocalDate dataTransferencia;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_agendamento", columnDefinition = "DATE")
	private LocalDate dataAgendamento = LocalDate.now();
	
	@NotNull
	@Column(nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private Operacao operacao;
	
	private Double taxa;
	
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

	public Double getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(Double valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
}