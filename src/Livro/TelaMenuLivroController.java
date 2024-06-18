package Livro;

import java.util.List;

public class TelaMenuLivroController {
    private final TelaMenuLivro telaMenuLivro;
    private final LivroDAO livroDAO;

    public TelaMenuLivroController(TelaMenuLivro telaMenuLivro, LivroDAO livroDAO) {
        this.telaMenuLivro = telaMenuLivro;
        this.livroDAO = livroDAO;
    }

    List<Livro> getLivros(String chave){
        List<Livro> resultadosDaBusca = livroDAO.getLivros(chave);
        return resultadosDaBusca;
    }

    Livro getLivroById(int id){
        Livro livro = livroDAO.getLivroById(id);
        if(livro == null){
            return null;
        }
        else{
            return livro;
        }
    }

    void excLivro(int id){
        if(livroDAO.excLivro(id)){
            telaMenuLivro.showSuccesMessage("Livro excluído com sucesso!");
        }
        else{
            telaMenuLivro.showErrorMessage("Erro ao excluir o livro");
        }
    }
}

