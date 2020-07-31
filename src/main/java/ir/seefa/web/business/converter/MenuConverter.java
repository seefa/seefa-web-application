package ir.seefa.web.business.converter;

import ir.seefa.web.dto.Menu;
import ir.seefa.web.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 30 Jul 2020 T 03:26:50
 */
public class MenuConverter {

    public static List<Menu> convertMainEntityToMenuDto(List<MenuEntity> registeredUserMainMenus) {
        List<Menu> menus = new ArrayList<>();
        registeredUserMainMenus.forEach(item -> {
            Menu menu = new Menu();
            menu.setTitle(item.getMenuTitle());
            menu.setCode(item.getMenuCode());
            menu.setMenuUrl(item.getMenuUrl());
            menus.add(menu);
        });
        return menus;
    }
}
