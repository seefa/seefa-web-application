package ir.seefa.web.service;

import ir.seefa.web.business.WebApplicationEJB;
import ir.seefa.web.dto.CredentialRequestDto;
import ir.seefa.web.dto.Menu;
import ir.seefa.web.dto.UserProfileResponseDto;

import javax.ejb.EJB;
import javax.faces.annotation.RequestMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 07:12:02
 */
@Path("rest/v1")
@Produces({ MediaType.APPLICATION_JSON})
public class RestWebApplication {

    @EJB
    WebApplicationEJB facade;

    @GET
    @Path("sayHello")
    public String sayHello() {
        return "Hello Seefa Rest Services...";
    }

    @POST
    @Path("getGuestMainMenus")
    public List<Menu> getMainMenus() {
        return facade.getGuestMainMenus();
    }

    @POST
    @Path("login")
    public UserProfileResponseDto login(@RequestMap final CredentialRequestDto credential) {
        return facade.login(credential);
    }

    @POST
    @Path("logout")
    public void login() {
        System.out.println("User logout...");
    }
}
