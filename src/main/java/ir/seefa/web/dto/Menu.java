package ir.seefa.web.dto;

import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 04:28:06
 */
public class Menu {
    private String title;
    private String code;
    private List<Menu> children;
    private String menuUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
}
