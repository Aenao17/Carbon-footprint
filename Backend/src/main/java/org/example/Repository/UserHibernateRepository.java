package org.example.Repository;

import org.example.Domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateRepository implements UserRepository {
    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //merge actualizeaza un obiect daca este deja prezent in baza de date.
            // Este potrivit  cand dorim să facem update doar asupra unui User existent.
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //revine la starea initiala
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void add(User elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    @Override
    public User getByCredentials(String username, String password) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User a where a.username = :usern and a.password = :passw", User.class)
                    .setParameter("usern", username)
                    .setParameter("passw", password)
                    .getSingleResultOrNull();
        }
    }

    public void delete(Integer id) {
        // Implementarea metodei delete, dacă e necesară.
    }

    public static User find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User where id = :idM", User.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }
}
