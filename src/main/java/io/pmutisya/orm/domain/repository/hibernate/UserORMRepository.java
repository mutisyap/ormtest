package io.pmutisya.orm.domain.repository.hibernate;

import io.pmutisya.orm.domain.User;
import io.pmutisya.orm.domain.config.HibernateConfiguration;
import io.pmutisya.orm.domain.repository.UserRepository;
import org.hibernate.Session;

public class UserORMRepository implements UserRepository {

    public void save(User user) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public User findById(Long id) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            session.beginTransaction();

            return session.get(User.class, id);
        }
    }
}
