package Emprestimo;

import Livro.Livro;
import Livro.LivroDAO;
import java.time.LocalDate;

public class TelaCadastroEmpController {
    private final TelaCadastroEmp telaCadastroEmp;
    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;

    public TelaCadastroEmpController(TelaCadastroEmp telaCadastroEmp, EmprestimoDAO emprestimoDAO, LivroDAO livroDAO) {
        this.telaCadastroEmp = telaCadastroEmp;
        this.emprestimoDAO = emprestimoDAO;
        this.livroDAO = livroDAO;
    }

    private boolean isNull(String campo1, String campo2) {
        return campo1.trim().isEmpty() || campo2.trim().isEmpty();
    }

    void adEmp(Livro livro, LocalDate dataEmp, int qtdeDias, LocalDate dataDev, String nomePessoa, String raPessoa, String estadoDev) {
        if(isNull(nomePessoa, raPessoa)){
            telaCadastroEmp.showErrorMessage("Preencha todos os campos!");
        }
        else{
            if(emprestimoDAO.adEmp(livro, dataEmp, qtdeDias, dataDev,
                    nomePessoa, raPessoa, estadoDev)){
                telaCadastroEmp.showSuccesMessage("Empréstimo cadastrado com sucesso!");
            }
        }
    }
}

