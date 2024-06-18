package Livro;

public class TelaCadastroLivroController {
    private final TelaCadastroLivro telaCadastroLivro;
    private final LivroDAO livroDAO;

    public TelaCadastroLivroController(TelaCadastroLivro telaCadastroLivro, LivroDAO livroDAO) {
        this.telaCadastroLivro = telaCadastroLivro;
        this.livroDAO = livroDAO;
    }

    private boolean isNumeric(String str) {
        return (str != null && str.matches("\\d+"));
    }

    private boolean isNull(String campo1, String campo2, String campo3, String campo4){
        return campo1.trim().isEmpty() || campo2.trim().isEmpty() || campo3.trim().isEmpty() || campo4.trim().isEmpty();
    }

    void adLivro(String titulo, String autor, String categoria, String isbn, String qtde, String qtdeDiasEmp, String disponivel) {
        if (!isNumeric(qtde) || !isNumeric(qtdeDiasEmp)) {
            telaCadastroLivro.showErrorMessage("Qtde e Qtde de dias do empréstimo devem ser números inteiros positivos");
        }
        else if(!isNumeric(isbn)){
            telaCadastroLivro.showErrorMessage("O isbn deve ser numérico e sem traços");
        }
        else if(isNull(titulo, autor, categoria, isbn)){
            telaCadastroLivro.showErrorMessage("Preencha todos os campos");
        }
        else{
            if(livroDAO.adLivro(titulo, autor, categoria, isbn,
                    Integer.parseInt(qtde), Integer.parseInt(qtdeDiasEmp), disponivel)){
                telaCadastroLivro.showSuccesMessage("Livro cadastrado com sucesso!");
            }
        }
    }
}

