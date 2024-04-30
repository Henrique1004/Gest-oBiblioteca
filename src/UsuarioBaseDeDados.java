import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UsuarioBaseDeDados {
    public static List<Usuario> getUsuarios(String chave){
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DBManager.openDatabaseConnection();
             Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM usuarios WHERE (nome = '" + chave + "' OR cargo = '" + chave + "' OR id = '" + chave + "')");
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cargo"), rs.getString("senha"));
                usuarios.add(usuario);
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        return usuarios;
    }
    static boolean adUsuario(String nome, String cargo, String senha) {
        if(existeUsuario(nome, cargo)){
            return false;
        }
        else{
            try (Connection connection = DBManager.openDatabaseConnection();
                 Statement statement = connection.createStatement()) {
                String sql = "INSERT INTO usuarios (nome, cargo, senha) VALUES ('" + nome + "', '" + cargo + "', '" + senha + "')";
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    static boolean editUsuario(int id, String nome, String cargo, String senha) {
        try (Connection connection = DBManager.openDatabaseConnection();
             Statement statement = connection.createStatement()) {
            String sql = "UPDATE usuarios SET nome = '" + nome + "', cargo = '" + cargo + "', senha = '" + senha + "' WHERE id = '" + id + "'";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    static boolean existeUsuario(String nome, String cargo){
        try (Connection connection = DBManager.openDatabaseConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM usuarios WHERE nome = '" + nome + "' AND cargo = '" + cargo + "'";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    boolean excUsuario(String chave) {
//        for (Usuario usuario : usuarios) {
//            if (usuario.getId().equals(chave)){
//                usuarios.remove(usuario);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    Usuario peUsuario(String chave) {
//        for (Usuario usuario : usuarios) {
//            if ((usuario.getNome().equalsIgnoreCase(chave)) || (usuario.getCargo().equalsIgnoreCase(chave)) || (usuario.getId().equals(chave)))
//                return usuario;
//        }
//        return null;
//    }
//    /*No método abaixo leva-se em consideração que pode haver mais de um usuario com o mesmo nome ou cargo*/
//     List<Usuario> peUsr(String chave) {
//        List<Usuario> usuariosPe = new ArrayList<>();
//        for(Usuario usuario : usuarios){
//            if((usuario.getNome()).equals(chave)||(usuario.getCargo()).equals(chave))
//                usuariosPe.add(usuario);
//        }
//        return null;
//    }
//    List<JPanel> peUsuarioPanel(String chave) {
//        List<JPanel> usuariosPe = new ArrayList<>();
//        for (Usuario usuario : usuarios) {
//            if ((usuario.getId().equalsIgnoreCase(chave)) || (usuario.getNome().equalsIgnoreCase(chave)) || (usuario.getCargo().equalsIgnoreCase(chave))) {
//                JTextArea textArea = new JTextArea("ID: " + usuario.getId() + " / Nome: " + usuario.getNome() + "  /  Cargo: " + usuario.getCargo());
//                textArea.setOpaque(true);
//                textArea.setFont(new Font("Comic Sans", Font.PLAIN, 16));
//                textArea.setLineWrap(true);
//                textArea.setWrapStyleWord(true);
//                textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
//                textArea.setBackground(Color.WHITE);
//                textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//                textArea.setEditable(false);
//                JPanel painelTextArea = new JPanel();
//                painelTextArea.add(textArea);
//                painelTextArea.setLayout(new BoxLayout(painelTextArea, BoxLayout.Y_AXIS));
//                painelTextArea.setBackground(Color.WHITE);
//                painelTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                usuariosPe.add(painelTextArea);
//            }
//        }
//        return usuariosPe;
//    }
}
