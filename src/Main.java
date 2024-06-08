import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DBManager.createSessionFactory();

        SwingUtilities.invokeLater(() -> {
            TelaMenuUsr telaMenuUsr = new TelaMenuUsr();
            telaMenuUsr.setVisible(true);
        });
    }
}
