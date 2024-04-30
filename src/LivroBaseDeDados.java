import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LivroBaseDeDados {
    public List<Livro> livros;

    LivroBaseDeDados() {
        this.livros = new ArrayList<>();
    }

    boolean adLivro(String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, boolean disponivel) {
        if (existeLivro(isbn)) {
            return false;
        }

        Livro livro = new Livro(titulo, autor, categoria, isbn, qtde, qtdeDiasEmp, disponivel);
        livros.add(livro);
        return true;
    }


    boolean existeLivro(String chave){
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(chave)){
                return true;
            }
        }
        return false;
    }

    boolean excLivro(String chave) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(chave)){
                livros.remove(livro);
                return true;
            }
        }
        return false;
    }

    Livro peLivro(String chave) {
        for (Livro livro : livros) {
            if ((livro.getTitulo().equalsIgnoreCase(chave)) || (livro.getAutor().equalsIgnoreCase(chave)) || (livro.getIsbn().equalsIgnoreCase(chave)) || (livro.getCategoria().equalsIgnoreCase(chave)))
                return livro;
        }
        return null;
    }


    List<JPanel> peLivroPanel(String chave) {
        List<JPanel> livrosPe = new ArrayList<>();
        for (Livro livro : livros) {
            if ((livro.getTitulo().equalsIgnoreCase(chave)) || (livro.getAutor().equalsIgnoreCase(chave)) || (livro.getIsbn().equalsIgnoreCase(chave)) || (livro.getCategoria().equalsIgnoreCase(chave))) {
                JTextArea textArea = new JTextArea("Título: " + livro.getTitulo() + "  /  Autor: " + livro.getAutor() + "  /  Categoria: " + livro.getCategoria() + "  /  Isbn: " + livro.getIsbn() + "  /  Qtde: " + livro.getQtde() + "  /  Qtde de dias do empréstimo: " + livro.getQtdeDiasEmp() + "  /  Disponibilidade: " + ((livro.getDisponivel()) ?"Disponível":"Indisponível"));
                textArea.setOpaque(true);
                textArea.setFont(new Font("Comic Sans", Font.PLAIN, 16));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
                textArea.setBackground(Color.WHITE);
                textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                textArea.setEditable(false);
                JPanel painelTextArea = new JPanel();
                painelTextArea.add(textArea);
                painelTextArea.setLayout(new BoxLayout(painelTextArea, BoxLayout.Y_AXIS));
                painelTextArea.setBackground(Color.WHITE);
                painelTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                livrosPe.add(painelTextArea);
            }
        }
        return livrosPe;
    }
}