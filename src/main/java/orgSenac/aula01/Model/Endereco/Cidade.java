package orgSenac.aula01.Model.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCidade;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_uf")
    private UF uf;

    public Cidade(String descricao, int idCidade, UF uf) {
        this.descricao = descricao;
        this.idCidade = idCidade;
        this.uf = uf;
    }

    // Getters and Setters

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
