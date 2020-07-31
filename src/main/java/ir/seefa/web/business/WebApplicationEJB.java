package ir.seefa.web.business;

import ir.seefa.web.dto.CredentialRequestDto;
import ir.seefa.web.dto.Menu;
import ir.seefa.web.dto.UserProfileResponseDto;

import javax.ejb.Local;
import java.util.List;

/**
 * Application Facade methods
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 04:26:00
 */
@Local
public interface WebApplicationEJB {
    UserProfileResponseDto login(CredentialRequestDto credentialRequestDto);
    List<Menu> getGuestMainMenus();
}
