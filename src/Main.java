import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DBManager.createSessionFactory();

        SwingUtilities.invokeLater(() -> {
            TelaMenuLivro telaMenuLivro = new TelaMenuLivro();
            telaMenuLivro.setVisible(true);
            TelaMenuEmp telaMenuEmp = new TelaMenuEmp();
            telaMenuEmp.setVisible(true);
        });
    }
}
