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

    static boolean excUsuario(int id) {
        try (Connection connection = DBManager.openDatabaseConnection();
             Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM usuarios WHERE id = '" + id + "'";
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
