package TelasIniciaisMain;

import Data.DBManager;
import Usuario.Usuario;

import javax.swing.*;

public class Main {
    public static Usuario usrLogado = null;
    public static void main(String[] args) {
        DBManager.createSessionFactory();

//        Usuario usuario = new Usuario("Henrique", "123456", "adm");
//        try {
//            DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
//                session.persist(usuario);
//                return null;
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
