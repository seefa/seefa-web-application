package ir.seefa.web.business;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 04:58:23
 */
public abstract class AbstractSessionFacade<T> {
    private Class<T> entityClass;

    public AbstractSessionFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract Session getSession();

    public void create(T entity) {
        getSession().persist(entity);
    }

    public void edit(T entity) {
        getSession().merge(entity);
    }

    public void remove(T entity) {
        getSession().remove(getSession().merge(entity));
    }

    public T find(Object id) {
        return getSession().find(entityClass, id);
    }

    // TODO: abstract implementation with JPA criteria query
    public List<T> findAll(){
        CriteriaQuery cq = getSession().getCriteriaBuilder().createQuery(entityClass);
        Root<T> entity = cq.from(entityClass);
        CriteriaQuery<T> select = cq.select(entity);
        return getSession().createQuery(select).getResultList();
    }
}
