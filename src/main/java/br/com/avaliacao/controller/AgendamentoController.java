package br.com.avaliacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.avaliacao.dto.RequisicaoNovoAgendamento;
import br.com.avaliacao.model.Agendamento;
import br.com.avaliacao.repository.AgendamentoRepository;

@Controller
@RequestMapping("agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoAgendamento novoAgendamento) {
		return "agendamento/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoAgendamento novoAgendamento, BindingResult result) {
		Agendamento agendamento = novoAgendamento.toAgendamento(); 
		
		if(result.hasErrors()) {
			return "agendamento/formulario";
		}
		
		agendamentoRepository.save(agendamento);
		return "agendamento/formulario";
	}
	
}
