package br.edu.ifrn.crud.Controller;


import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Arquivo;
import br.edu.ifrn.crud.dominio.CursoFormacao;
import br.edu.ifrn.crud.dominio.Profissao;
import br.edu.ifrn.crud.dominio.Usuario;
import br.edu.ifrn.crud.dto.AutocompleteDTO;
import br.edu.ifrn.crud.repository.ArquivoRepository;
import br.edu.ifrn.crud.repository.CursoFormacaoRepository;
import br.edu.ifrn.crud.repository.ProfissaoRepository;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class CadrastrodeUsuarioController {
    @Autowired
	 private UsuarioRepository usuarioRepository;
      
      @Autowired
      private ProfissaoRepository profissaoRepository;
      
      @Autowired
      private CursoFormacaoRepository formacaoRepository;
      
      @Autowired
     private ArquivoRepository arquivoRepository;
      
	@GetMapping("/cadrastro")
	public String entrarCadrastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "cadrastro";
	}
	
	@GetMapping("/autocompleteProfissoes")
	@Transactional(readOnly = true)
	@ResponseBody
	public List<AutocompleteDTO> autocompleteProfissoes(
			@RequestParam("term") String termo){
		
	List<Profissao> profissoes = profissaoRepository.findByNome(termo);
	
	List<AutocompleteDTO> resultados = new ArrayList<>();
	
	profissoes.forEach(p -> resultados.add( 
			new  AutocompleteDTO(p.getNome(),p.getId())
			));
			
	   
	       return resultados;
	}
	
	
	@GetMapping("/autocompleteFormacoes")
	@Transactional(readOnly = true)
	@ResponseBody
	public List<AutocompleteDTO> autocompleteFormacoes(
			@RequestParam("term") String termo){
		
	List<CursoFormacao> formacoes = formacaoRepository.findByNome(termo);
	
	List<AutocompleteDTO> resultados = new ArrayList<>();
	
	formacoes.forEach(p -> resultados.add( 
			new  AutocompleteDTO(p.getNome(),p.getId())
			));
			
	   
	       return resultados;
	}
	
	@PostMapping("/addCursoFormacao")
	  public String addCursoFormacao(Usuario usuario, ModelMap model) {
		  
		  if(usuario.getFormacoes()== null) {
			  usuario.setFormacoes(new ArrayList<>());
		  }
		  
		  usuario.getFormacoes().add(usuario.getCursoFormacao());
		  
		  return "/cadrastro";
	  }
	
	@PostMapping("/removerCursoFormacao/{id}")
	  public String removerCursoFormacao(Usuario usuario,
			  
			       @PathVariable("id")  Integer idFormacao,
			        ModelMap model) {
		 
		CursoFormacao curso = new  CursoFormacao();
		curso.setId(idFormacao);
		
		 usuario.getFormacoes().remove(curso);
		
		  
		  return "/cadrastro";
	  }
	
	
	

	
	public String iniciarEdicao(
			  @PathVariable("id") Integer idUsuario,
			   ModelMap Model,
			   HttpSession sessao
			  ) {
		  
		  Usuario u = usuarioRepository.findById(idUsuario).get();
		  
		  Model.addAttribute("usuario",u);
		  return "/cadrastro";
	
	}
	
	@PostMapping("/salvar")
	@Transactional(readOnly = false)
	public String salvar(Usuario usuario ,RedirectAttributes attr,
			@RequestParam("file") MultipartFile  arquivo,
			
			HttpSession sessao) {
		
		try {
			
			if(arquivo != null && !arquivo.isEmpty()) {
				
				String nomeArquivo = 
						StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				
				Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());
				
				
				arquivoRepository.save(arquivoBD);
			
				
				if(usuario.getFoto() != null && usuario.getFoto().getId() != null
						&& usuario.getFoto().getId() > 0){
					
				  arquivoRepository.delete(usuario.getFoto());
				  
				}
				
				        usuario.setFoto(arquivoBD);
				
			}else {
				
				usuario.setFoto(null);
			
			}
			
			//criptagrafando a senha
			
			String senhaCriptografada =
					new BCryptPasswordEncoder().encode(usuario.getSenha());
			  usuario.setSenha(senhaCriptografada);
			
			usuarioRepository.save(usuario);
			attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		
		}catch(IOException e) {
			e.printStackTrace();
		}
			
			return "redirect:/usuario/cadrastro";
	
			}	
		
		
}
		
	

	
	
	
	
	
	
	/*
	@ModelAttribute("profissoes")
	public List<String> getProfissoes(){
		return Arrays.asList("Professor", "Medico","Advogado","Policial","Bombeiro","outros");
	}*/
	
	
	

