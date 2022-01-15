package br.edu.ifrn.crud.Controller;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.crud.dominio.Usuario;
import br.edu.ifrn.crud.repository.UsuarioRepository;

@Controller

@RequestMapping("/usuarios")
public class BuscarUsuarioController {

	 @Autowired
	private UsuarioRepository usuarioRepository;
	@GetMapping("/busca")
	public String entrarBusca() {
		return "/busca";
	}
	
	@GetMapping("/buscar")
	public String buscar( @RequestParam(name="nome", required=false) String nome, 
			              @RequestParam(name="email", required=false) String email, 
			               @RequestParam(name="mostrarTodosDados",required=false) boolean mostrarTodosDados,
			               HttpSession sessao
			               , ModelMap model) {
		
		List<Usuario> usuariosEncontrados =
	 usuarioRepository.findByEmailAndNome(email, nome);
		
		
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		if("mostrarTodosDados" != null){
			model.addAttribute("mostrarTodosDados", true);
		}
		
		return "/busca";
	}
	
	@GetMapping("/editar/{id}")
	public String iniciarEdicao( 
		@PathVariable("id") Integer idUsuario,
		ModelMap model,
		HttpSession sessao
		) {
		
		Usuario u =usuarioRepository.findById(idUsuario).get();
		
		model.addAttribute("usuario", u);
		
		return "/cadrastro";
	}
	
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer idUsuario,
			HttpSession sessao,
			RedirectAttributes attr) {
	
		usuarioRepository.deleteById(idUsuario);
		attr.addFlashAttribute("msgSucesso", "Usuário removido com secesso!");
		return "redirect:/usuarios/buscar";
		
		
	}
	
	
	

}
