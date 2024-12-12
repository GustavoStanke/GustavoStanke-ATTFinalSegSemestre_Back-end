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

import orgSenac.aula01.Model.Usuario;
import orgSenac.aula01.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Retorna todos os usuários
    @GetMapping
    public List<Usuario> get() {
        return repository.findAll();
    }

    // Retorna um usuário específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> pegarPorId(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salva um novo usuário
    @PostMapping
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario) {
        repository.save(usuario); // Aqui o usuário será salvo
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    // Deleta um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

    // Atualiza um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNome(usuario.getNome());
                    existing.setCpf(usuario.getCpf());
                    existing.setEmail(usuario.getEmail());
                    existing.setSenha(usuario.getSenha());
                    existing.setContato(usuario.getContato());
                    existing.setEndereco(usuario.getEndereco());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
