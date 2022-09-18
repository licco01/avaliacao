package br.com.avaliacao.dao;

import org.springframework.stereotype.Repository;

import br.com.avaliacao.domain.Agendamento;

@Repository
public class AgendamentoDaoImpl extends AbstractDao<Agendamento, Long> implements AgendamentoDao {
}
