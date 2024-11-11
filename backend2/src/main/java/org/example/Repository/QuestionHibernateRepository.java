package org.example.Repository;

import org.example.Domain.Question;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionHibernateRepository implements QuestionRepository {

    public static Question find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Question where id=:idQ", Question.class)
                    .setParameter("idQ", id)
                    .getSingleResultOrNull();
        }
    }

    @Override
    public void add(Question question) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(question));
    }

    public List<Question> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Question", Question.class).getResultList();
        }
    }


    public void delete(Integer id) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Question question = session.find(Question.class, id);
            if (question != null) {
                session.remove(question);
            }
        });
    }
}
