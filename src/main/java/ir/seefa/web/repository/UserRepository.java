package ir.seefa.web.repository;

import ir.seefa.web.entity.UserEntity;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 26 Jul 2020 T 10:49:38
 */
public class UserRepository {
    private Session session;

    public UserRepository(Session session) {
        this.session = session;
    }

    public Optional<UserEntity> save(UserEntity user) {
        try {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<UserEntity> findById(Integer userId) {
        UserEntity user = session.find(UserEntity.class, userId);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<UserEntity> findByUsername(String username) {
        List users = session.createNamedQuery(UserEntity.FIND_BY_USERNAME).setParameter("username", username).getResultList();
        return users != null && users.size() > 0 ? Optional.of((UserEntity) users.get(0)) : Optional.ofNullable(null);
    }

    public Optional<UserEntity> findActiveUserByUsername(String username) {
        List users = session.createNamedQuery(UserEntity.FIND_ACTIVE_USER_BY_USERNAME).setParameter("username", username).getResultList();
        return users != null && users.size() > 0 ? Optional.of((UserEntity) users.get(0)) : Optional.ofNullable(null);
    }

    public List<UserEntity> findAll() {
        return session.createQuery("from UserEntity ", UserEntity.class).getResultList();
    }

    public List<UserEntity> findAll2() {
        Query query = session.createQuery("from UserEntity", UserEntity.class);
        return query.getResultList();
    }

    public List<UserEntity> findAllByNativeQuery() {
        return session.createNativeQuery("select m from UserEntity m", UserEntity.class).getResultList();
    }
}
