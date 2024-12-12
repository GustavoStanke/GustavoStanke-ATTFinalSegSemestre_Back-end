package orgSenac.aula01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orgSenac.aula01.Model.Ingresso;
import orgSenac.aula01.repository.IngressoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ingresso")
public class IngressoController {

    @Autowired
    private IngressoRepository repository;

    @GetMapping
    public List<Ingresso> get() {
        return repository.findAll();
    }

    @PostMapping
    public Ingresso save(@RequestBody Ingresso ingresso) {
        return repository.save(ingresso);
    }
}
