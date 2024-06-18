package Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String cargo;
    private String senha;

    public Usuario() {}

    public Usuario(int id, String nome, String senha, String cargo){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Usuario(String nome, String senha, String cargo){
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }

    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getSenha(){
        return this.senha;
    }
    public String getCargo(){
        return this.cargo;
    }
    void setNome(String novoNome){
        this.nome = novoNome;
    }
    void setSenha(String novaSenha){
        this.senha = novaSenha;
    }
    void setCargo(String novoCargo){
        this.cargo = novoCargo;
    }
}
