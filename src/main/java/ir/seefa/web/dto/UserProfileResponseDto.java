package ir.seefa.web.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 30 Jul 2020 T 01:44:07
 */
@XmlRootElement
public class UserProfileResponseDto implements Serializable {
    private String fullName;
    private List<Menu> mainMenus;

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public List<Menu> getMainMenus() {
        return mainMenus;
    }

    public void setMainMenus(List<Menu> mainMenus) {
        this.mainMenus = mainMenus;
    }
}
