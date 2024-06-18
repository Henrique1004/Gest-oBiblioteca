package Livro;

public class TelaEditLivroController {
    private final TelaEditLivro telaEditLivro;
    private final LivroDAO livroDAO;

    public TelaEditLivroController(TelaEditLivro telaEditLivro, LivroDAO livroDAO) {
        this.telaEditLivro = telaEditLivro;
        this.livroDAO = livroDAO;
    }

    private boolean isNumeric(String str) {
        return (str != null && str.matches("\\d+"));
    }

    private boolean isNull(String campo1, String campo2, String campo3, String campo4){
        return campo1.trim().isEmpty() || campo2.trim().isEmpty() || campo3.trim().isEmpty() || campo4.trim().isEmpty();
    }

    void editLivro(int id, String titulo, String autor, String categoria, String isbn, String qtde, String qtdeDiasEmp, String disponivel) {
        if (!isNumeric(qtde) || !isNumeric(qtdeDiasEmp)) {
            telaEditLivro.showErrorMessage("Qtde e Qtde de dias do empréstimo devem ser números inteiros positivos");
        }
        else if(!isNumeric(isbn)){
            telaEditLivro.showErrorMessage("O isbn deve ser numérico e sem traços");
        }
        else if(isNull(titulo, autor, categoria, isbn)){
            telaEditLivro.showErrorMessage("Preencha todos os campos");
        }
        else{
            if(livroDAO.editLivro(id, titulo, autor, categoria, isbn,
                    Integer.parseInt(qtde), Integer.parseInt(qtdeDiasEmp), disponivel)){
                telaEditLivro.showSuccesMessage("Livro editado com sucesso!");
            }
        }
    }
}



