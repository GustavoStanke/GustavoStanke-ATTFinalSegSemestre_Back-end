package orgSenac.aula01.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = false)
    private Integer indicacao;

    @Column(length = 255)
    private String descricao;

    @Column(length = 255) // Armazena o caminho ou URL da foto
    private String foto;

    // Construtor vazio
    public Filme() {
    }

    // Construtor com parâmetros
    public Filme(String titulo, Integer duracao, String genero, Integer indicacao, String descricao, String foto) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.indicacao = indicacao;
        this.descricao = descricao;
        this.foto = foto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(Integer indicacao) {
        this.indicacao = indicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
