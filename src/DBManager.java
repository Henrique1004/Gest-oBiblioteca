import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    public static Connection openDatabaseConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
    }

    public static void createDatabase() {
        // Abrir uma conexão com banco de dados
        try(Connection connection = openDatabaseConnection();
            Statement statement = connection.createStatement()) {

            String sql1 = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "cargo TEXT NOT NULL,"
                    + "senha TEXT NOT NULL"
                    + ");";
            statement.execute(sql1);

            String sql2 = "CREATE TABLE IF NOT EXISTS livros ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "titulo TEXT NOT NULL,"
                    + "autor TEXT NOT NULL,"
                    + "categoria TEXT NOT NULL,"
                    + "isbn TEXT NOT NULL,"
                    + "qtde INTEGER NOT NULL,"
                    + "qtdeDiasEmp int NOT NULL,"
                    + "disponivel INT NOT NULL"
                    + ");";
            statement.execute(sql2);

            String sql3 = "CREATE TABLE IF NOT EXISTS emprestimos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "livro TEXT NOT NULL,"
                    + "dataEmp DATE NOT NULL,"
                    + "qtdeDias INTEGER NOT NULL,"
                    + "nomePessoa TEXT NOT NULL,"
                    + "raPessoa TEXT NOT NULL,"
                    + "estadoDev INTEGER NOT NULL,"
                    + "FOREIGN KEY (livro) REFERENCES livros(titulo)"
                    + ");";
            statement.execute(sql3);

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
