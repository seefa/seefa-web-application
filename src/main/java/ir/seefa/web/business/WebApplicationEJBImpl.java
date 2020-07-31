package ir.seefa.web.business;

import ir.seefa.web.business.converter.MenuConverter;
import ir.seefa.web.config.HibernateListener;
import ir.seefa.web.dto.CredentialRequestDto;
import ir.seefa.web.dto.Menu;
import ir.seefa.web.dto.UserProfileResponseDto;
import ir.seefa.web.entity.MenuEntity;
import ir.seefa.web.entity.UserEntity;
import ir.seefa.web.exception.LoginFailedException;
import ir.seefa.web.repository.MenuRepository;
import ir.seefa.web.repository.UserRepository;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 02:26:16
 */
@Stateless(name = "application")
public class WebApplicationEJBImpl implements WebApplicationEJB {

//    @PersistenceContext(unitName = "MySQLSeefaWebsite")
//    private EntityManager entityManager;

    private Session session;
    private MenuRepository menuRepository;
    private UserRepository userRepository;

    public WebApplicationEJBImpl() {
        session = HibernateListener.getHibernateSession();
        menuRepository = new MenuRepository(session);
        userRepository = new UserRepository(session);
    }

    @Override
    public List<Menu> getGuestMainMenus() {
        List<MenuEntity> menuEntities = menuRepository.getGuestUserMainMenu();
        return MenuConverter.convertMainEntityToMenuDto(menuEntities);
    }

    @Override
    public UserProfileResponseDto login(CredentialRequestDto credentialRequestDto) {
        Optional<UserEntity> user = userRepository.findActiveUserByUsername(credentialRequestDto.getUsername().toLowerCase());
        Boolean loginOperationResult = user.isPresent()
                && user.get().getUsername().equalsIgnoreCase(credentialRequestDto.getUsername())
                && this.checkPassword(credentialRequestDto.getPassword(), user.get().getPassword());

        if(loginOperationResult) {
            String fullName= user.get().getName() + " " + user.get().getFamily();

            List<MenuEntity> menuEntities = menuRepository.getRegisteredUserMainMenu();
            List<Menu> registeredMainMenus = MenuConverter.convertMainEntityToMenuDto(menuEntities);
            UserProfileResponseDto responseDto = new UserProfileResponseDto();
            responseDto.setFullname(fullName);
            responseDto.setMainMenus(registeredMainMenus);
            return responseDto;
        } else {
            throw new LoginFailedException("You don't have access to system");
        }
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
