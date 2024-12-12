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

import orgSenac.aula01.Model.Sala;
import orgSenac.aula01.repository.SalaRepository;

@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = "*")
public class SalaController {

    @Autowired
    private SalaRepository repository;

    // Retorna todas as salas
    @GetMapping
    public List<Sala> get() {
        return repository.findAll();
    }

    // Retorna uma sala específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Sala> pegarPorId(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salva uma nova sala
    @PostMapping
    public ResponseEntity<String> salvarSala(@RequestBody Sala sala) {
        repository.save(sala); // Aqui a sala será salva
        return ResponseEntity.ok("Sala cadastrada com sucesso!");
    }

    // Deleta uma sala pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSala(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Sala deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sala não encontrada!");
        }
    }

    // Atualiza uma sala existente
    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizarSala(@PathVariable int id, @RequestBody Sala sala) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNumero(sala.getNumero());
                    existing.setCapacidade(sala.getCapacidade());
                    existing.setDisponivel(sala.isDisponivel());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
