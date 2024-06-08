//import javax.swing.*;
//import java.awt.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LivroBaseDeDados {
//
//    public static List<Livro> getLivros(String chave){
//        List<Livro> livros = new ArrayList<>();
//        try (Connection connection = DBManager.openDatabaseConnection();
//             Statement statement = connection.createStatement()){
//            ResultSet rs = statement.executeQuery("SELECT * FROM livros WHERE (titulo = '" + chave + "' OR autor = '" + chave + "' OR categoria = '" + chave + "' OR isbn = '" + chave + "' OR id = '" + chave + "')");
//            while(rs.next()){
//                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("categoria"), rs.getString("isbn"), rs.getInt("qtde"), rs.getInt("qtdeDiasEmp"), rs.getBoolean("disponivel"));
//                livros.add(livro);
//            }
//        }
//        catch (SQLException err) {
//            err.printStackTrace();
//        }
//        return livros;
//    }
//    static boolean adLivro(String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, boolean disponivel) {
//        if(existeLivro(isbn)){
//            return false;
//        }
//        else{
//            try (Connection connection = DBManager.openDatabaseConnection();
//                 Statement statement = connection.createStatement()) {
//                String disponivelf = disponivel==true?"Disponível":"Indísponível";
//                String sql = "INSERT INTO livros (titulo, autor, categoria, isbn, qtde, qtdeDiasEmp, disponivel) VALUES ('" + titulo + "', '" + autor + "', '" + categoria + "', '" + isbn + "', '" + qtde + "', '" + qtdeDiasEmp + "', '" + disponivelf + "')";
//                statement.executeUpdate(sql);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//    }
//
//    static boolean editLivro(int id, String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, boolean disponivel) {
//        try (Connection connection = DBManager.openDatabaseConnection();
//             Statement statement = connection.createStatement()) {
//            String disponivelf = disponivel==true?"Disponível":"Indísponível";
//            String sql = "UPDATE usuarios SET titulo = '" + titulo + "', autor = '" + autor + "', categoria = '" + categoria + "', isbn = '" + isbn + "', qtde = '" + qtde + "', qtdeDiasEmp = '" + qtdeDiasEmp + "', disponivel = '" + disponivelf + "' WHERE id = '" + id + "'";
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    static boolean existeLivro(String id){
//        try (Connection connection = DBManager.openDatabaseConnection();
//             Statement statement = connection.createStatement()) {
//            String sql = "SELECT * FROM livros WHERE id = '" + id + "'";
//            ResultSet rs = statement.executeQuery(sql);
//            if(rs.next()){
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    static boolean excLivro(int id) {
//        try (Connection connection = DBManager.openDatabaseConnection();
//             Statement statement = connection.createStatement()) {
//            String sql = "DELETE FROM livros WHERE id = '" + id + "'";
//            statement.executeUpdate(sql);
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}