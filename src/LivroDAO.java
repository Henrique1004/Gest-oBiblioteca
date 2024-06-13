import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private final List<GeneralListener> listeners = new ArrayList<>();

    public void subscribe(GeneralListener livroListener) {
        listeners.add(livroListener);
    }

    private void notifyDataChanged() {
        for (GeneralListener listener : listeners) {
            listener.updateData();
        }
    }
//or isbn = :chave or qtde = cast(:chave as int) or qtdeDiasEmp = cast(:chave as int)
    public List<Livro> getLivros(String chave){
        List<Livro> livros = new ArrayList<>();
        try{
            livros = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Livro where lower(titulo) = lower(:chave) or lower(autor) = lower(:chave) " +
                                "or lower(categoria) = lower(:chave) or lower(disponivel) = lower(:chave) or isbn = :chave " +
                                "or qtde = cast(:chave as int) or qtdeDiasEmp = cast(:chave as int)", Livro.class)
                    .setParameter("chave", chave)
                    .getResultList();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro getLivroById(int id) {
        Livro livro = null;
        try {
            livro = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Livro where id = :chave",Livro.class)
                        .setParameter("chave", id)
                        .getSingleResult();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livro;
    }

    public boolean adLivro(String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, String disponivel) {
        try {
            if(!existeLivro(isbn)) {
                DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                    Livro livro = new Livro(titulo, autor, categoria, isbn, qtde, qtdeDiasEmp, disponivel);
                    session.persist(livro);
                });
                notifyDataChanged();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
        }
        return false;
    }

    public boolean editLivro(int id, String titulo, String autor, String categoria, String isbn, int qtde, int qtdeDiasEmp, String disponivel) {
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Livro livro = session.get(Livro.class, id);
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setCategoria(categoria);
                livro.setIsbn(isbn);
                livro.setQtde(qtde);
                livro.setQtdeDiasEmp(qtdeDiasEmp);
                livro.setDisponivel(disponivel);
                session.persist(livro);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao editar livro: " + e.getMessage());
        }
        return false;
    }

    static boolean existeLivro(String isbn){
        List<Livro> livros = new ArrayList<>();
        try {
            livros = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Livro where isbn = :isbn", Livro.class)
                        .setParameter("isbn", isbn)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return !livros.isEmpty();
    }

    public boolean excLivro(int id) {
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Livro livro = session.get(Livro.class, id);
                session.remove(livro);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
        return false;
    }
}