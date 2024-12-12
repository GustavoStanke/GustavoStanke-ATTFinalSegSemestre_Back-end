package orgSenac.aula01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orgSenac.aula01.Model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
