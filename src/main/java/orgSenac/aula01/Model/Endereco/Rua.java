package orgSenac.aula01.Model.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRua;

    private String descricao;

    public Rua(String descricao, int idRua) {
        this.descricao = descricao;
        this.idRua = idRua;
    }

    // Getters and Setters

    public int getIdRua() {
        return idRua;
    }

    public void setIdRua(int idRua) {
        this.idRua = idRua;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
