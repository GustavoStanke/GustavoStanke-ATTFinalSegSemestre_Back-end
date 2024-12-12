package orgSenac.aula01.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import orgSenac.aula01.Model.Endereco.Endereco;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_contato")
    private Contato contato;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Usuario() {
        // Construtor padrão
    }

    public Usuario(Contato contato, String cpf, String email, Endereco endereco, int idUsuario, String nome, String senha) {
        this.contato = contato;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
    }

    // Getters and Setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    

}
