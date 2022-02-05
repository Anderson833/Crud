package br.edu.ifrn.helloword.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrn.helloword.dominio.Texto;


@Controller
@RequestMapping("/tornaMaiuscula")
public class MaiusculaController {
	
	@GetMapping("/")
	public String entrar(Texto texto) {
		return "maiuscula";
	}

	 @PostMapping("/transformar")
	 public String transformar(Texto texto, ModelMap model) {
		 String Maiusculas=texto.getConteudo().toUpperCase();
		 model.addAttribute("textomaiuscula", Maiusculas);
		 return "maiuscula";
	 }
	
}
