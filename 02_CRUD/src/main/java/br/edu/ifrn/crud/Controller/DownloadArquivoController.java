package br.edu.ifrn.crud.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.crud.dominio.Arquivo;
import br.edu.ifrn.crud.repository.ArquivoRepository;

@Controller
public class DownloadArquivoController {

	
	 @Autowired
	private ArquivoRepository arquivoRepositiry;
	
	
	 @GetMapping("/download/{idArquivo}")
	public ResponseEntity<?> DownloadFile(
			       @PathVariable Long idArquivo,
			       @Param("salvar") String salvar
		       ){
		Arquivo  arquivoBD = arquivoRepositiry.findById(idArquivo).get();
		 
	String texto = (salvar == null || salvar.equals("true")) ?
			  "attachment ; filename=\"" + arquivoBD.getNomeArquivo() +"\"" 
			  : "inline; filename=\"" + arquivoBD.getNomeArquivo() +"\"";
			
	
      return ResponseEntity.ok()
    		  .contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo()))
    		  .header(HttpHeaders.CONTENT_DISPOSITION, texto)
              .body(new ByteArrayResource(arquivoBD.getDados()));
      
      
	}
	
}
