package br.com.avaliacao.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.dao.AgendamentoDao;
import br.com.avaliacao.domain.Agendamento;
import br.com.avaliacao.domain.Operacao;

@Service
@Transactional(readOnly = true)
public class AgendamentoServiceImpl implements AgendamentoService {
	
	@Autowired
	private AgendamentoDao dao;
	private Double porcentagem = 0.0;
	private Double total = 0.0;
	private final Double cotacaoDolar = 5.29;
	private final Double taxaA = cotacaoDolar * 3.0;
	private final Double taxaB = cotacaoDolar * 12.0;

	@Transactional(readOnly = false)
	@Override
	public void salvar(Agendamento agendamento) {
		calculaOperacao(agendamento);
		dao.save(agendamento);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	public List<Agendamento> buscarTodos() {
		return dao.findAll();
	}
	
	@Override
	public void calculaOperacao(Agendamento agendamento) {
		if(Operacao.A.equals(agendamento.getOperacao())) {
			calculaTaxaA(agendamento);
		} else if (Operacao.B.equals(agendamento.getOperacao())) {
			calculaTaxaB(agendamento);
		} else if (Operacao.C.equals(agendamento.getOperacao())) {
			calculaTaxaC(agendamento);
		} else if (Operacao.D.equals(agendamento.getOperacao())) {
			calculaTaxaD(agendamento);
		} 
	}
	
	@Override
	public void calculaTaxaA(Agendamento agendamento) {	
		if(agendamento.getDataAgendamento().equals(agendamento.getDataTransferencia()) == true){
			porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 3.0);
			total = (porcentagem + taxaA) + agendamento.getValorTransferencia();
			agendamento.setValorTransferencia(total);
			agendamento.setTaxa(taxaA + porcentagem);
		} else {
			agendamento.setTaxa(00.00);
		}
	}
	
	@Override
	public void calculaTaxaB(Agendamento agendamento)  {
		if(calcularDiffDias(agendamento.getDataAgendamento(), agendamento.getDataTransferencia()) <= 10) {
			 total  = taxaB + agendamento.getValorTransferencia();
			 agendamento.setValorTransferencia(total);
			 agendamento.setTaxa(taxaB);
		} else {
			agendamento.setTaxa(00.00);
		}
	}
	
	@Override
	public void calculaTaxaC(Agendamento agendamento) {
		Long dias = calcularDiffDias(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());
		
		if(dias > 10 && dias <=20) {
			porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 8.2);
			total = porcentagem + agendamento.getValorTransferencia();
			agendamento.setValorTransferencia(total);
			agendamento.setTaxa(porcentagem);
		} else if(dias > 20 && dias <= 30 ) {
			porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 6.9);
			total = porcentagem + agendamento.getValorTransferencia();
			agendamento.setValorTransferencia(total);
			agendamento.setTaxa(porcentagem);
		} else if(dias > 30 && dias <= 40) {
			porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 4.7);
			total = porcentagem + agendamento.getValorTransferencia();
			agendamento.setValorTransferencia(total);
			agendamento.setTaxa(porcentagem);
		} else if(dias > 40) {
			porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 1.7);
			total = porcentagem + agendamento.getValorTransferencia();
			 agendamento.setValorTransferencia(total);
			 agendamento.setTaxa(porcentagem);
		} else {
			agendamento.setTaxa(00.00);
		}
	}
	
	@Override
	public void calculaTaxaD(Agendamento agendamento) {
		if(agendamento.getValorTransferencia() <= 1000) {
			calculaTaxaA(agendamento);
		} else if(agendamento.getValorTransferencia() >= 1001 && agendamento.getValorTransferencia() <= 2000) {
			calculaTaxaB(agendamento);
		} else if(agendamento.getValorTransferencia() > 2000 ) {
			calculaTaxaC(agendamento);
		} else {
			agendamento.setTaxa(00.00);
		}
	}
	
	@Override
	public Long calcularDiffDias(LocalDate agendamento, LocalDate transferencia) {
		Long diffDias = ChronoUnit.DAYS.between(agendamento, transferencia);
		return diffDias;
	}
	
	@Override
	public Double calculaPorcentagem(Double valorTransferencia, Double porcentagem) {
		Double result = (porcentagem * valorTransferencia) / 100;
		return result;
	}
	
}