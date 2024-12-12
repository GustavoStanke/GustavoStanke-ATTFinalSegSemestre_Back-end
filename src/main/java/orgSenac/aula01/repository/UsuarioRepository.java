package orgSenac.aula01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orgSenac.aula01.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


}
