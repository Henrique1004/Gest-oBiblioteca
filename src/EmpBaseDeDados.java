import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpBaseDeDados {
    List<Emprestimo> emprestimos;

    EmpBaseDeDados() {
        this.emprestimos = new ArrayList<>();
    }

    boolean adEmp(Livro livro, Date dataEmp, int qtdeDias, Date dataDev, String nomePessoa, String raPessoa, boolean estadoDev) {
        if (existeEmp(raPessoa)) {
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(livro, dataEmp, qtdeDias, dataDev, nomePessoa, raPessoa, estadoDev);
        emprestimos.add(emprestimo);
        return true;
    }


    boolean existeEmp(String chave){
        for (Emprestimo emprestimo: emprestimos) {
            if (emprestimo.getRaPessoa().equals(chave)){
                return true;
            }
        }
        return false;
    }

    boolean excEmp(String chave) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getRaPessoa().equals(chave)){
                emprestimos.remove(emprestimo);
                return true;
            }
        }
        return false;
    }

    Emprestimo peEmp(String chave) {
        for (Emprestimo emprestimo : emprestimos) {
            if ((emprestimo.getLivro().getTitulo().equalsIgnoreCase(chave)) || (emprestimo.getRaPessoa().equals(chave)) || (emprestimo.getNomePessoa().equalsIgnoreCase(chave)))
                return emprestimo;
        }
        return null;
    }


    List<JPanel> peEmpPanel(String chave) {
        List<JPanel> emprestimosPe = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if ((emprestimo.getLivro().getTitulo().equalsIgnoreCase(chave)) || (emprestimo.getRaPessoa().equals(chave)) || (emprestimo.getNomePessoa().equalsIgnoreCase(chave))) {
                JTextArea textArea = new JTextArea("Livro: " + emprestimo.getLivro().getTitulo() + "  /  Realizado em: " + emprestimo.getDataEmp() + "  /  Data de devolução: " + emprestimo.getDataDev() + "  /  Nome: " + emprestimo.getNomePessoa() + "  /  RA: " + emprestimo.getRaPessoa() + "  /  Qtde de dias do empréstimo: " + emprestimo.getQtdeDias() + "  /  Devolução: " + ((emprestimo.getEstadoDev()) ?"Devolvido":"Não devolvido"));
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
                emprestimosPe.add(painelTextArea);
            }
        }
        return emprestimosPe;
    }
}