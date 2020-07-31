package ir.seefa.web.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 12:43:54
 */
@Entity(name = "Menu")
@Table(name = "Menu", schema = "seefasitev1")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "menuType", discriminatorType = DiscriminatorType.STRING, length = 50, columnDefinition = "VARCHAR(50)")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = MenuEntity.FIND_ALL_MENUS, query = "select m from Menu m order by menuOrder"),
        @NamedQuery(name = MenuEntity.GET_ALL_ACTIVE_MENUS, query = "select m from Menu m where m.active = true order by menuOrder"),
        @NamedQuery(name = MenuEntity.GET_REGISTERED_USER_MAIN_MENUS, query = "select m from Menu m where m.active = true and m.menuType='RegisteredUserMainMenu' order by menuOrder"),
        @NamedQuery(name = MenuEntity.GET_GUEST_MAIN_MENUS, query = "select m from Menu m where m.active = true and m.menuType='GuestMainMenu' order by menuOrder"),
        @NamedQuery(name = MenuEntity.FIND_MENU_BY_ID, query = "select m from Menu m where m.id=:menuId"),
        @NamedQuery(name = MenuEntity.FIND_MENU_BY_TITLE, query = "select m from Menu m where m.menuTitle like '%:menuTitle%' order by menuOrder"),
        @NamedQuery(name = MenuEntity.FIND_CHILDREN_MENUS_BY_PARENT_ID, query = "select m from Menu m where m.menuParentId=:menuParentId order by menuOrder"),
        @NamedQuery(name = MenuEntity.FIND_MENU_BY_MENU_CODE, query = "select m from Menu m where m.menuCode=:menuCode")
})
public class MenuEntity {
    public static final String GET_GUEST_MAIN_MENUS = "getRegisteredUserMenus";
    public static final String GET_REGISTERED_USER_MAIN_MENUS = "getGuestMainMenus";
    public static final String FIND_ALL_MENUS = "findAllMenus";
    public static final String FIND_MENU_BY_ID = "findById";
    public static final String GET_ALL_ACTIVE_MENUS = "getAllActiveMenus";
    public static final String FIND_MENU_BY_TITLE = "findMenuByMenuTitle";
    public static final String FIND_CHILDREN_MENUS_BY_PARENT_ID = "findChildrenMenusByParentId";
    public static final String FIND_MENU_BY_MENU_CODE = "findMenuByMenuCode";
    private int id;
    private String menuTitle;
    private String menuCode;
    private Integer menuParentId;
    private String menuUrl;
    private Boolean active;
    private Integer menuOrder;
    private String menuType;
//    private String menuObject;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "menuTitle", nullable = false, length = 100)
    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    @Basic
    @Column(name = "menuCode", length = 100)
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Basic
    @Column(name = "menuParentId")
    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    @Basic
    @Column(name = "menuUrl", length = 400)
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Basic
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "menuOrder")
    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    @Basic
    @Column(name = "menuType")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

//    @Basic
//    @Column(name = "menuObject", nullable = true)
//    public String getMenuObject() {
//        return menuObject;
//    }
//
//    public void setMenuObject(String menuObject) {
//        this.menuObject = menuObject;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return id == that.id &&
                Objects.equals(menuTitle, that.menuTitle) &&
                Objects.equals(menuCode, that.menuCode) &&
                Objects.equals(menuParentId, that.menuParentId) &&
                Objects.equals(menuUrl, that.menuUrl);
//                Objects.equals(menuObject, that.menuObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuTitle, menuCode, menuParentId, menuUrl);
    }
}
