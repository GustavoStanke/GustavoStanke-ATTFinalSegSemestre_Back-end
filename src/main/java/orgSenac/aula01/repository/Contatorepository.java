package orgSenac.aula01.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import orgSenac.aula01.Model.Contato;

public interface Contatorepository extends JpaRepository<Contato, Integer> {

}