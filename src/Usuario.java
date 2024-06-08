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

    private String criaId(){
        return null;
    }
    int getId(){
        return this.id;
    }
    String getNome(){
        return this.nome;
    }
    String getSenha(){
        return this.senha;
    }
    String getCargo(){
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
