package br.com.avaliacao.dao;

import java.util.List;

import br.com.avaliacao.domain.Agendamento;

public interface AgendamentoDao {
	
	void save(Agendamento agendamento);
    void delete(Long id);
    List<Agendamento> findAll();
    
}
