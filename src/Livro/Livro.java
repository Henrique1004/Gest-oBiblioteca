package Livro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue
    private int id;
    private String titulo, autor, categoria, isbn, disponivel;
    private int qtde, qtdeDiasEmp;

    public Livro() {}

    public Livro(int id, String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, String disponivel){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.qtde = qtde;
        this.qtdeDiasEmp = qtdeDiasEmp;
        this.disponivel = disponivel;
    }

    public Livro(String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, String disponivel){
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.qtde = qtde;
        this.qtdeDiasEmp = qtdeDiasEmp;
        this.disponivel = disponivel;
    }

     public String getTitulo() {
        return this.titulo;
    }

     public String getAutor() {
        return this.autor;
    }

     public String getCategoria() {
        return this.categoria;
    }

     public String getIsbn() {
        return this.isbn;
    }

     public int getId() {
        return this.id;
    }

     public int getQtde() {
        return this.qtde;
    }

     public int getQtdeDiasEmp() {
        return this.qtdeDiasEmp;
    }

     public String getDisponivel() {
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
     public void setQtde(int novaQtde) {
        this.qtde = novaQtde;
    }

     void setQtdeDiasEmp(int novaQtdeDiasEmp) {
        this.qtdeDiasEmp = novaQtdeDiasEmp;
    }

     void setDisponivel(String novoDisponivel) {
        this.disponivel = novoDisponivel;
    }
}

