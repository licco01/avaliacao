package br.com.avaliacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.avaliacao.domain.Agendamento;
import br.com.avaliacao.domain.Operacao;
import br.com.avaliacao.service.AgendamentoService;

@Controller
@RequestMapping("agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Agendamento agendamento) {
		return "/agendamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("agendamentos", service.buscarTodos());
		return "/agendamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "agendamento/cadastro";
		} 
		
		service.salvar(agendamento);
		if(agendamento.getTaxa() != 00.00) {
			attr.addFlashAttribute("success", "Agendamento inserido com sucesso.");
		} else {
			attr.addFlashAttribute("fail", "Agendamento inserido sem nenhuma taxa apliacada.");
		}
		return "redirect:/agendamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Agendamento removido com sucesso.");
		return "redirect:/agendamentos/listar";
	}	
	
	@ModelAttribute("op")
	public Operacao[] getOPs() {
		return Operacao.values();
	}
	
}