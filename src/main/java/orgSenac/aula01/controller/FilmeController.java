package orgSenac.aula01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orgSenac.aula01.Model.Filme;
import orgSenac.aula01.repository.FilmeRepository;

@RestController 
@RequestMapping("/filme")
@CrossOrigin(origins = "*")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public List<Filme> pegarTodos() {
        return repository.findAll();
    }

   // Retorna um filme específico por ID
   @GetMapping("/{id}")
   public ResponseEntity<Filme> PegarPorID(@PathVariable int id) {
       return repository.findById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

    @PostMapping
    public ResponseEntity<String> salvarFilme(@RequestBody Filme filme) {
        repository.save(filme); // Aqui o filme deve ser salvo
        return ResponseEntity.ok("Filme cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletarFilme(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Filme deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
    }

   // Atualiza um filme existente
   @PutMapping("/{id}")
   public ResponseEntity<Filme> Atualizar(@PathVariable int id, @RequestBody Filme filme) {
       return repository.findById(id)
               .map(existing -> {
                   existing.setTitulo(filme.getTitulo());
                   existing.setDuracao(filme.getDuracao());
                   existing.setGenero(filme.getGenero());
                   existing.setIndicacao(filme.getIndicacao());
                   existing.setDescricao(filme.getDescricao());
                   existing.setFoto(filme.getFoto());
                   repository.save(existing);
                   return ResponseEntity.ok(existing);
               })
               .orElse(ResponseEntity.notFound().build());
   }

}