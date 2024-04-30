public class Usuario {
    public Usuario(int id, String nome, String cargo, String senha){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }
    private String nome, senha, cargo;
    private int id;
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
