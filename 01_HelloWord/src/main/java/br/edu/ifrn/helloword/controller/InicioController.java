package br.edu.ifrn.helloword.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.helloword.Repository.CadrastroRepository;
import br.edu.ifrn.helloword.Repository.DadosRepository;
import br.edu.ifrn.helloword.dominio.Cliente;
import br.edu.ifrn.helloword.dominio.Dados;

@Controller

public class InicioController {

    @Autowired
	private CadrastroRepository er;
	

    @Autowired
	private DadosRepository xr;
	
    
	@GetMapping("/helloword")
	public String Home(ModelMap Model){
	Model.addAttribute("textol", "Olá, Mundo");
		return "helloword";
		
	}
	
	
	
	
	 @RequestMapping(value = "/edita",method = RequestMethod.GET)
		public String mostra() {
		
			return "Cadrastro";
		}
	 
	 @RequestMapping(value = "/edita",method = RequestMethod.POST)
		public String mostrar(Cliente cd,RedirectAttributes att) {
		 
		  if(cd.getNome().equals("") || cd.getTelefone().equals("") || cd.getDescricao().equals("")) {
			  att.addFlashAttribute("msgError", "Error ao adicionar, tenter novamente! ");
			  return "redirect:/edita";
		  }
		 
		er.save(cd);
		att.addFlashAttribute("msgSucesso","Cadrastro Realizado com sucesso!");
			return "redirect:/edita";
		}
	 
	  @RequestMapping("/clientes")
	  public ModelAndView listaCliente() {
		  
		ModelAndView mvc = new ModelAndView("resultado");
		Iterable<Cliente>  cliente = er.findAll();
		mvc.addObject("lista",cliente);
		
		return mvc;  
	  }
	  
	  @RequestMapping(value="/{id}", method = RequestMethod.GET)
	  public ModelAndView detalheCliente(@PathVariable("id") long id) {
		  Cliente cliente = er.findByid(id);
		  ModelAndView mvc = new ModelAndView("detalhe"); 
		  mvc.addObject("evento", cliente);
		 
		  Iterable<Dados> dados = xr.findByCliente(cliente);
		  mvc.addObject("dados",dados);
		  return mvc;
	  }
	  
	  
	  @RequestMapping(value="/{id}", method = RequestMethod.POST)
	  public String detalheClientePost(@PathVariable("id")long id,Dados dados, RedirectAttributes attribute) {
		  
		  if(dados.getRg().equals("") || dados.getCpf().equals("")) {
			  attribute.addFlashAttribute("msgError", "Error ao salvar, tenter novamente! ");
			  return "redirect:/{id}";
		  }
			 
			  Cliente cliente = er.findByid(id);
			  dados.setCliente(cliente);
			  xr.save(dados);
			  attribute.addFlashAttribute("msgSucesso", " Dados salvo com Sucesso! ");
			  return "redirect:/{id}";
		  }
	  
	  
	    @RequestMapping("/deleteClientes")
	    public String deletaCliente(long id) {
	    	Cliente cliente = er.findByid(id);
	    	er.delete(cliente);
	    	return "redirect:/clientes";
	    }
	    
	    @RequestMapping("/deletaDados")
	    public String deletaDados(String rg) {
	    	Dados dados = xr.findByRg(rg);
	    	xr.delete(dados);
	    	
	    Cliente cliente = dados.getCliente();
	    long codigolg = cliente.getId();
	    String codigo="" + codigolg;
	    	return "redirect:/" + codigo;
	    }
	  
	    }
      
	 
	  

