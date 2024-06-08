import jakarta.persistence.NoResultException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDAO {
    private final List<UsrListener> listeners = new ArrayList<>();

    public void subscribe(UsrListener usrListener) {
        listeners.add(usrListener);
    }

    private void notifyDataChanged() {
        for (UsrListener listener : listeners) {
            listener.updateData();
        }
    }

    public List<Usuario> getUsuarios(String chave) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Usuario where lower(nome) = lower(:chave) or cargo = lower(:chave)", Usuario.class)
                        .setParameter("chave", chave)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        Usuario usuario = null;
        try {
            usuario = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Usuario where id = :chave", Usuario.class)
                        .setParameter("chave", id)
                        .getSingleResult();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    boolean adUsuario(String nome, String senha, String cargo) {
        try {
            if(!existeUsuario(nome, cargo)) {
                DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                    Usuario usuario = new Usuario(nome, senha, cargo);
                    session.persist(usuario);
                });
                notifyDataChanged();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
        return false;
    }

    boolean editUsuario(int id, String nome, String senha, String cargo) {
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Usuario usuario = session.get(Usuario.class, id);
                usuario.setNome(nome);
                usuario.setCargo(cargo);
                usuario.setSenha(senha);
                session.persist(usuario);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao editar usuário: " + e.getMessage());
        }
        return false;
    }

    boolean existeUsuario(String nome, String cargo) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = DBManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createSelectionQuery("from Usuario where nome = :nome and cargo =:cargo", Usuario.class)
                        .setParameter("nome", nome)
                        .setParameter("cargo", cargo)
                        .getResultList();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return !usuarios.isEmpty();
    }

    boolean excUsuario(int id) {
        try {
            DBManager.getDatabaseSessionFactory().inTransaction(session -> {
                Usuario usuario = session.get(Usuario.class, id);
                session.remove(usuario);
            });
            notifyDataChanged();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
        return false;
    }
}
