package orgSenac.aula01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orgSenac.aula01.Model.Ingresso;


@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
}
