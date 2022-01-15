package br.edu.ifrn.crud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
@GetMapping("/")
	public String incio() {
		return "inicio";
	}
@GetMapping("/login")
     public String login() {
	return "login";
}
@GetMapping("/login-error")
     public String loginError(ModelMap model ) {
	 model.addAttribute("msgError","login ou senha incorretos. Tente novamete.");
	return "login";
}






}
