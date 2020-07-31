package ir.seefa.web.repository;

import ir.seefa.web.entity.MenuEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 01:02:49
 */
public class MenuEntityManagerRepository {
    private EntityManager entityManager;

    public MenuEntityManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<MenuEntity> save(MenuEntity menu) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(menu);
//            entityManager.merge(menu);
//            entityManager.createNamedQuery("findAll");
//            entityManager.createNativeQuery("select * from Menu");
            entityManager.getTransaction().commit();
            return Optional.of(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<MenuEntity> findById(Integer menuId) {
        MenuEntity menu = entityManager.find(MenuEntity.class, menuId);
        return menu != null ? Optional.of(menu) : Optional.empty();
    }

    public List<MenuEntity> findAll() {
        return entityManager.createQuery("from MenuEntity", MenuEntity.class).getResultList();
    }

    public List<MenuEntity> findAll2() {
        Query query = entityManager.createQuery("from MenuEntity", MenuEntity.class);
        return query.getResultList();
    }

    public List<MenuEntity> findAllByNativeQuery() {
        return entityManager.createNativeQuery("select m from MenuEntity m", MenuEntity.class).getResultList();
    }

    // TODO: Must to check
    public List<MenuEntity> findMenusByTitle(String menuTitle) {
        Query query = entityManager.createQuery("select m from MenuEntity m where m.menuTitle like %:menuTitle%", MenuEntity.class);
        query.setParameter("menuTitle", menuTitle);
        return query.getResultList();
    }

    // TODO: Must to check
    public List<MenuEntity> findAllByNamedQuery() {
        return entityManager.createNamedQuery("findAll", MenuEntity.class).getResultList();
    }

    // TODO: Must to check
    public boolean deleteById(Integer menuId) {
        MenuEntity menu = entityManager.find(MenuEntity.class, menuId);
        if (menu != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(menu);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    // By JPQL
    public boolean updateMenuTitleByQuery(MenuEntity menu) {
        MenuEntity resultMenu = entityManager.find(MenuEntity.class, menu);
        if (resultMenu != null) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("update MenuEntity m set m.menuTitle='" + menu.getMenuTitle() + "'");
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    // By Native Query
    public boolean updateMenuTitleByNativeQuery(MenuEntity menu) {
        MenuEntity resultMenu = entityManager.find(MenuEntity.class, menu);
        if (resultMenu != null) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("update MenuEntity m set m.menuTitle='" + menu.getMenuTitle() + "'");
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    // Fetch by Criteria Query
    public List<MenuEntity> findAllByCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);

        Root<MenuEntity> menu = criteriaQuery.from(MenuEntity.class);
        CriteriaQuery<MenuEntity> select = criteriaQuery.select(menu).orderBy(criteriaBuilder.asc(menu.get("menuOrder")));

        Query query = entityManager.createQuery(select);
//        TypedQuery<MenuEntity> query = entityManager.createQuery(select);
        return query.getResultList();
    }

    // Fetch by Criteria Query specific fields
    public List<MenuEntity> findAllByCriteriaQuerySpecificFeilds() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);

        Root<MenuEntity> menu = criteriaQuery.from(MenuEntity.class);
        CriteriaQuery<MenuEntity> select = criteriaQuery.multiselect(menu.get("menuTitle"), menu.get("menuCode"), menu.get("menuUrl")).orderBy(criteriaBuilder.asc(menu.get("menuOrder")));

        Query query = entityManager.createQuery(select);
//        TypedQuery<MenuEntity> query = entityManager.createQuery(select);
        return query.getResultList();
    }
}
