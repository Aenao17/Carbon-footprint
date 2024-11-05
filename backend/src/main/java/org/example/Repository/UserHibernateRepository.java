package org.example.Repository;


import org.example.Domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateRepository implements UserRepository {
    public void delete(Integer id) {}

    public static User find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User where id=:idM ", User.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void add(User elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));

    }

    @Override
    public List<User> getAll() {

        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from User ", User.class).getResultList();
        }
    }

    @Override
    public User getByCredentials(String username, String password) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User a where a.username like: usern and a.password like: passw ", User.class)
                    .setParameter("usern", username)
                    .setParameter("passw", password)
                    .getSingleResultOrNull();
        }
    }
}
