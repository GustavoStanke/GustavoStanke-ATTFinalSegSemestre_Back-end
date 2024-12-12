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
import orgSenac.aula01.Model.Sessao;
import orgSenac.aula01.repository.SalaRepository;
import orgSenac.aula01.repository.SessaoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sessao")
public class SessaoController {

    @Autowired
    private SessaoRepository repository;

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sessao> get() {
        return repository.findAll();
    }

    // Retorna uma sessão específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Sessao> getById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sessao> save(@RequestBody Sessao sessao) {
        try {
            Sala sala = sessao.getSala();

            // Verifica se a sala já existe
            if (salaRepository.existsById(sala.getId())) {
                Sala salaExistente = salaRepository.findById(sala.getId()).orElseThrow();
                if (!salaExistente.isDisponivel()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(null); // Sala já está indisponível
                }
                salaExistente.setDisponivel(false); // Atualiza a sala existente para indisponível
                salaRepository.save(salaExistente);
            }

            // Salva a nova sessão
            Sessao novaSessao = repository.save(sessao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaSessao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable int id) {
        // Verifica se a sessão existe no banco de dados
        if (repository.existsById(id)) {
            Sessao sessao = repository.findById(id).get(); // Obtém a sessão
            Sala sala = sessao.getSala(); // Obtém a sala associada à sessão

            // Exclui a sessão
            repository.deleteById(id);

            // Atualiza o status da sala para "disponível"
            sala.setDisponivel(true);

            // Atualiza a sala no banco de dados
            salaRepository.save(sala);

            return ResponseEntity.ok("Sessão deletada com sucesso e sala disponível novamente!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sessão não encontrada!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessao> atualizarSessao(@PathVariable int id, @RequestBody Sessao sessao) {
        // Log para rastrear o ID
        System.out.println("Atualizando sessão com ID: " + id);

        Sessao existing = repository.findById(id).orElse(null);

        if (existing == null) {
            System.out.println("Sessão não encontrada para o ID: " + id);
            return ResponseEntity.notFound().build();
        }

        
        // Atualizar os dados
        existing.setFilme(sessao.getFilme());
        existing.setSala(sessao.getSala());
        existing.setData(sessao.getData());
        existing.setHora(sessao.getHora());

        // Salvar a sessão
        repository.save(existing);

        return ResponseEntity.ok(existing);
    }

}
