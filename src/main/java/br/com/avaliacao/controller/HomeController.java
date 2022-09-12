package br.com.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.avaliacao.model.Agendamento;
import br.com.avaliacao.repository.AgendamentoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findAll();
		model.addAttribute("agendamentos", agendamentos);
		return "home";
	}
	
}
