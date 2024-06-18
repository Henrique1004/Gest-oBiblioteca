package Emprestimo;

import Livro.Livro;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "titulo")
    private Livro livro;
    private LocalDate dataEmp;
    private int qtdeDias;
    private LocalDate dataDev;
    private String nomePessoa;
    private String raPessoa;
    private String estadoDev;

    public Emprestimo() {}

    public Emprestimo(int id, Livro livro, LocalDate dataEmp, int qtdeDias, LocalDate dataDev, String nomePessoa, String raPessoa, String estadoDev){
        this.id = id;
        this.livro = livro;
        this.dataEmp = dataEmp;
        this.qtdeDias = qtdeDias;
        this.dataDev = dataDev;
        this.nomePessoa = nomePessoa;
        this.raPessoa = raPessoa;
        this.estadoDev = estadoDev;
    }

    public Emprestimo(Livro livro, LocalDate dataEmp, int qtdeDias, LocalDate dataDev, String nomePessoa, String raPessoa, String estadoDev){
        this.livro = livro;
        this.dataEmp = dataEmp;
        this.qtdeDias = qtdeDias;
        this.dataDev = dataDev;
        this.nomePessoa = nomePessoa;
        this.raPessoa = raPessoa;
        this.estadoDev = estadoDev;
    }

    public int getId() {
        return id;
    }
    public Livro getLivro(){
        return this.livro;
    }
    public LocalDate getDataEmp(){
        return this.dataEmp;
    }
    public LocalDate getDataDev(){
        return this.dataDev;
    }
    public int getQtdeDias(){
        return this.qtdeDias;
    }
    public String getNomePessoa(){
        return this.nomePessoa;
    }
    public String getRaPessoa(){
        return this.raPessoa;
    }
    public String getEstadoDev(){
        return this.estadoDev;
    }
    void setEstadoDev(String novoEstadoDev){
        this.estadoDev = novoEstadoDev;
    }
}
