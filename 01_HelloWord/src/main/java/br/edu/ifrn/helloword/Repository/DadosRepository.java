package br.edu.ifrn.helloword.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrn.helloword.dominio.Cliente;
import br.edu.ifrn.helloword.dominio.Dados;

public interface DadosRepository  extends CrudRepository<Dados,String>{
  Iterable<Dados> findByCliente(Cliente cliente);
  Dados  findByRg(String rg);
}
