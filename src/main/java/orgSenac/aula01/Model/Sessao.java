package orgSenac.aula01.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false, unique = false) // Certifique-se de que `unique` está como false
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String hora;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, orphanRemoval = true) // Conecta com a classe sessao, pq
                                                                                     // a qtd de ing = qtd
                                                                                     // capacidadeSala
    private List<Ingresso> ingressos;

    public Sessao() {
    }

    public Sessao(Filme filme, Sala sala, String data, String hora) {
        this.filme = filme;
        this.sala = sala;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public int getIngressosVendidos() {
        return ingressos != null ? ingressos.size() : 0;
    }

    public boolean podeVenderIngresso() {
        return getIngressosVendidos() < sala.getCapacidade();
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}