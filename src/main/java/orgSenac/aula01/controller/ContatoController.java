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

import orgSenac.aula01.Model.Contato;
import orgSenac.aula01.repository.Contatorepository;


@RestController
@RequestMapping("/contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    @Autowired
    private Contatorepository repository;

    // Retorna todos os contatos
    @GetMapping
    public List<Contato> pegarTodos() {
        return repository.findAll();
    }

    // Retorna um contato específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contato> PegarPorID(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria um novo contato
    @PostMapping
    public ResponseEntity<String> salvarContato(@RequestBody Contato contato) {
        repository.save(contato);
        return ResponseEntity.ok("Contato cadastrado com sucesso!");
    }

    // Deleta um contato por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarContato(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Contato deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado!");
        }
    }

    // Atualiza um contato existente
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable int id, @RequestBody Contato contato) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNome(contato.getNome());
                    existing.setEmail(contato.getEmail());
                    existing.setTelefone(contato.getTelefone());
                    existing.setEndereco(contato.getEndereco());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
