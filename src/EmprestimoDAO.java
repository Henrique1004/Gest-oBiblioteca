import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private final List<GeneralListener> listeners = new ArrayList<>();

    public void subscribe(GeneralListener empListener) {
        listeners.add(empListener);
    }

    private void notifyDataChanged() {
        for (GeneralListener listener : listeners) {
            listener.updateData();
        }
    }

    public List<Emprestimo> getEmp(String chave) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            emprestimos = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Emprestimo where lower(livro) = lower(:chave) or lower(nomePessoa) = lower(:chave)" +
                                "or raPessoa = :chave", Emprestimo.class)
                        .setParameter("chave", chave)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    boolean adEmp(Livro livro, LocalDate dataEmp, int qtdeDias, LocalDate dataDev, String nomePessoa, String raPessoa, String estadoDev) {
        try {
            if (!existeEmp(raPessoa)) {
                DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                    Emprestimo emprestimo = new Emprestimo(livro, dataEmp, qtdeDias, dataDev, nomePessoa, raPessoa, estadoDev);
                    session.persist(emprestimo);
                });
                notifyDataChanged();
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("Erro ao inserir empréstimo: " + e.getMessage());
        }
        return false;
    }


    boolean existeEmp(String chave){
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            emprestimos = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Emprestimo where raPessoa = :chave", Emprestimo.class)
                        .setParameter("chave", chave)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return !emprestimos.isEmpty();
    }

    boolean setDevolvido(int id){
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Emprestimo emp = session.get(Emprestimo.class, id);
                emp.setEstadoDev("Devolvido");
                session.persist(emp);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean excEmp(int id) {
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Emprestimo emprestimo = session.get(Emprestimo.class, id);
                session.remove(emprestimo);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir empréstimo: " + e.getMessage());
        }
        return false;
    }

    public Livro getLivroByIsbnForEmp(String isbn) {
        Livro livro = null;
        try {
            livro = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Livro where isbn = :chave",Livro.class)
                        .setParameter("chave", isbn)
                        .getSingleResult();
            });
            setQtdeLivroEmp(isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livro;
    }

    public boolean setQtdeLivroEmp(String isbn){
        try{
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Livro livro = session.get(Livro.class, isbn);
                livro.setQtde(livro.getQtde()-1);
                session.persist(livro);
            });
            notifyDataChanged();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}