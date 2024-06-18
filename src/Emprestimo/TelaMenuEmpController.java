package Emprestimo;

import Livro.LivroDAO;

import java.util.List;

public class TelaMenuEmpController {
    private final TelaMenuEmp telaMenuEmp;
    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;

    public TelaMenuEmpController(TelaMenuEmp telaMenuEmp, EmprestimoDAO emprestimoDAO) {
        this.telaMenuEmp = telaMenuEmp;
        this.emprestimoDAO = emprestimoDAO;
        this.livroDAO = new LivroDAO();
    }

    List<Emprestimo> getEmp(String chave){
        List<Emprestimo> resultadosDaBusca = emprestimoDAO.getEmp(chave);
        return resultadosDaBusca;
    }

    void setDevolvido(int id) {
        if(emprestimoDAO.setDevolvido(id) && livroDAO.adLivroDevolvido(id)){
            telaMenuEmp.showSuccesMessage("Devolução feita com sucesso!");
        }
        else{
            telaMenuEmp.showErrorMessage("Erro ao realizar devolução");
        }
    }

    void excEmp(int id){
        if(emprestimoDAO.excEmp(id)){
            telaMenuEmp.showSuccesMessage("Empréstimo excluído com sucesso!");
        }
        else{
            telaMenuEmp.showErrorMessage("Erro ao excluir o empréstimo");
        }
    }
}
