package Data;

import Livro.Livro;
import Usuario.Usuario;
import Emprestimo.Emprestimo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        return sessionFactory;
    }

    public static void createSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Livro.class)
                    .addAnnotatedClass(Emprestimo.class)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

