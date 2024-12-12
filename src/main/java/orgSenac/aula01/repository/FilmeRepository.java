package orgSenac.aula01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orgSenac.aula01.Model.Filme;

public interface FilmeRepository extends JpaRepository<Filme,Integer>{


    
}