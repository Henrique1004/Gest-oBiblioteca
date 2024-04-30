public class Livro {
    private String titulo, autor, categoria, isbn, id;
    private int qtde, qtdeDiasEmp;
    private boolean disponivel;

    public Livro(String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, boolean disponivel){
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.id = criaId();
        this.qtde = qtde;
        this.qtdeDiasEmp = qtdeDiasEmp;
        this.disponivel = disponivel;
    }
    int iteradorId = 0;
    private String criaId(){
        return String.valueOf(iteradorId += 1);
    }
     String getTitulo() {
        return this.titulo;
    }

     String getAutor() {
        return this.autor;
    }

     String getCategoria() {
        return this.categoria;
    }

     String getIsbn() {
        return this.isbn;
    }

     String getId() {
        return this.id;
    }

     int getQtde() {
        return this.qtde;
    }

     int getQtdeDiasEmp() {
        return this.qtdeDiasEmp;
    }

     boolean getDisponivel() {
        return this.disponivel;
    }

     void setTitulo(String novoTitulo) {
        this.titulo = novoTitulo;
    }

     void setAutor(String novoAutor) {
        this.autor = novoAutor;
    }

     void setCategoria(String novaCategoria) {
        this.categoria = novaCategoria;
    }

     void setIsbn(String novoIsbn) {
        this.isbn = novoIsbn;
    }
     void setQtde(int novaQtde) {
        this.qtde = novaQtde;
    }

     void setQtdeDiasEmp(int novaQtdeDiasEmp) {
        this.qtdeDiasEmp = novaQtdeDiasEmp;
    }

     void setDisponivel(boolean novoDisponivel) {
        this.disponivel = novoDisponivel;
    }
}

