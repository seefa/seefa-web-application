package ir.seefa.web.repository;

import ir.seefa.web.entity.GuestMainMenu;
import ir.seefa.web.entity.MenuEntity;
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
 * @since 25 Jul 2020 T 01:02:49
 */
public class MenuRepository {
    private Session session;

    public MenuRepository(Session session) {
        this.session = session;
    }

    public List<MenuEntity> getRegisteredUserMainMenu() {
        return session.createNamedQuery(GuestMainMenu.GET_REGISTERED_USER_MAIN_MENUS).getResultList();
    }

    public List<MenuEntity> getGuestUserMainMenu() {
        return session.createNamedQuery(GuestMainMenu.GET_GUEST_MAIN_MENUS).getResultList();
    }

    public Optional<MenuEntity> save(MenuEntity menu) {
        try {
            session.getTransaction().begin();
            session.persist(menu);
            session.getTransaction().commit();
            return Optional.of(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<MenuEntity> findById(Integer menuId) {
        MenuEntity menu = session.find(MenuEntity.class, menuId);
        return menu != null ? Optional.of(menu) : Optional.empty();
    }

    // TODO: to test findById with NamedQuery JPQL
//    public Optional<MenuEntity> findByIdByNamedQuery(Integer menuId) {
//        MenuEntity menu = (MenuEntity) session.createNamedQuery(MenuEntity.FIND_MENU_BY_ID).setParameter("id", menuId).getSingleResult();
//        return menu != null ? Optional.of(menu) : Optional.empty();
//    }

    public List<MenuEntity> findAll() {
        return session.createQuery("from Menu", MenuEntity.class).getResultList();
    }

    public List<MenuEntity> findAll2() {
        Query query = session.createQuery("from Menu", MenuEntity.class);
        return query.getResultList();
    }

    public List<MenuEntity> findAllByNativeQuery() {
        return session.createNativeQuery("select m from Menu m", MenuEntity.class).getResultList();
    }

    // TODO: Must to check
    public List<MenuEntity> findMenusByTitle(String menuTitle) {
        Query query = session.createQuery("select m from Menu m where m.menuTitle like %:menuTitle%", MenuEntity.class);
        query.setParameter("menuTitle", menuTitle);
        return query.getResultList();
    }

    // TODO: Must to check
//    public List<MenuEntity> findAllByNamedQuery() {
//            return session.createNamedQuery(MenuEntity.FIND_ALL_MENUS, MenuEntity.class).getResultList();
//    }

    // TODO: Must to check
    public boolean deleteById(Integer menuId) {
        MenuEntity menu = session.find(MenuEntity.class, menuId);
        if (menu != null) {
            session.getTransaction().begin();
            session.remove(menu);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    // By JPQL
    public boolean updateMenuTitleByQuery(MenuEntity menu) {
        MenuEntity resultMenu = session.find(MenuEntity.class, menu);
        if (resultMenu != null) {
            session.getTransaction().begin();
            Query query = session.createQuery("update Menu m set m.menuTitle='" + menu.getMenuTitle() + "'");
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    // By Native Query
    public boolean updateMenuTitleByNativeQuery(MenuEntity menu) {
        MenuEntity resultMenu = session.find(MenuEntity.class, menu);
        if (resultMenu != null) {
            session.getTransaction().begin();
            Query query = session.createNativeQuery("update Menu m set m.menuTitle='" + menu.getMenuTitle() + "'");
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    // Fetch by Criteria Query
    public List<MenuEntity> findAllByCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);

        Root<MenuEntity> menu = criteriaQuery.from(MenuEntity.class);
        CriteriaQuery<MenuEntity> select = criteriaQuery.select(menu).orderBy(criteriaBuilder.asc(menu.get("menuOrder")));

        Query query = session.createQuery(select);
//        TypedQuery<MenuEntity> query = entityManager.createQuery(select);
        return query.getResultList();
    }

    // Fetch by Criteria Query specific fields
    public List<MenuEntity> findAllByCriteriaQuerySpecificFeilds() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<MenuEntity> criteriaQuery = criteriaBuilder.createQuery(MenuEntity.class);

        Root<MenuEntity> menu = criteriaQuery.from(MenuEntity.class);
        CriteriaQuery<MenuEntity> select = criteriaQuery.multiselect(menu.get("menuTitle"), menu.get("menuCode"), menu.get("menuUrl")).orderBy(criteriaBuilder.asc(menu.get("menuOrder")));

        Query query = session.createQuery(select);
//        TypedQuery<MenuEntity> query = entityManager.createQuery(select);
        return query.getResultList();
    }
}
