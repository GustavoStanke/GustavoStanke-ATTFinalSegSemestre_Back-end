package orgSenac.aula01.Model.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    private String cep;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_rua")
    private Rua rua;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_uf")
    private UF uf;

    public Endereco(Bairro bairro, String cep, Cidade cidade, int idEndereco, String numero, Rua rua, UF uf) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.idEndereco = idEndereco;
        this.numero = numero;
        this.rua = rua;
        this.uf = uf;
    }

    public Endereco() {
        // Construtor padrão
    }

    // Getters e Setters

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Rua getRua() {
        return rua;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
