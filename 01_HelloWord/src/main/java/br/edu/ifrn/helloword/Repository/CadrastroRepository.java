package br.edu.ifrn.helloword.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.helloword.dominio.Cliente;

public interface CadrastroRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByid(long id);
}
