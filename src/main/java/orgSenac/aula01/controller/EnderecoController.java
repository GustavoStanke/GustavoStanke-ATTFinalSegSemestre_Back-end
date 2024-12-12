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

import orgSenac.aula01.Model.Endereco.Endereco;
import orgSenac.aula01.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    // Retorna todos os endereços
    @GetMapping
    public List<Endereco> pegarTodos() {
        return repository.findAll();
    }

    // Retorna um endereço específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> PegarPorID(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria um novo endereço
    @PostMapping
    public ResponseEntity<String> salvarEndereco(@RequestBody Endereco endereco) {
        repository.save(endereco);
        return ResponseEntity.ok("Endereço cadastrado com sucesso!");
    }

    // Deleta um endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Endereço deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
        }
    }

    // Atualiza um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable int id, @RequestBody Endereco endereco) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCep(endereco.getCep());
                    existing.setNumero(endereco.getNumero());
                    existing.setBairro(endereco.getBairro());
                    existing.setRua(endereco.getRua());
                    existing.setCidade(endereco.getCidade());
                    existing.setUf(endereco.getUf());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}
