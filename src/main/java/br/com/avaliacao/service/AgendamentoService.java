package br.com.avaliacao.service;

import java.time.LocalDate;
import java.util.List;

import br.com.avaliacao.domain.Agendamento;

public interface AgendamentoService {
	
    void salvar(Agendamento agendamento);

    void excluir(Long id);
    
    void calculaTaxaA(Agendamento agendamento); 
    
    void calculaTaxaB(Agendamento agendamento);
    
    void calculaTaxaC(Agendamento agendamento);
    
    void calculaTaxaD(Agendamento agendamento); 

    void calculaOperacao(Agendamento agendamento);
    
    List<Agendamento> buscarTodos();
    
    Long calcularDiffDias(LocalDate agendamento, LocalDate transferencia);
    
    Double calculaPorcentagem(Double valorTransferencia, Double porcentagem);
    
}