package ir.seefa.web.business;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 04:58:23
 */
public abstract class AbstractEntityManagerFacade<T> {
    private Class<T> entityClass;

    public AbstractEntityManagerFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    // TODO: abstract implementation with JPA criteria query
    public List<T> findAll(){
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        CriteriaQuery<T> select = cq.select(entity);
        return getEntityManager().createQuery(select).getResultList();
    }
}
