package br.edu.ifrn.helloword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrn.helloword.dominio.Texto;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

	@PostMapping("/dados")
	public String dados(Texto texto, ModelMap model) {
		String nome=texto.getConteudo().toUpperCase();
		model.addAttribute("texto1",nome);
	 		
		return "form";
	}
	
	@GetMapping("/")
	public String entrar(Texto texto) {
		return "form";
	}
	
}
