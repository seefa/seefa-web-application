package ir.seefa.web.service;

import ir.seefa.web.business.WebApplicationEJB;
import ir.seefa.web.dto.CredentialRequestDto;
import ir.seefa.web.dto.UserProfileResponseDto;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 25 Jul 2020 T 07:15:19
 */
@WebService(
        serviceName = "WebSeefaSoap",
        portName = "SeefaPort"
)
public class SoapWebApplication {

    @EJB
    WebApplicationEJB facade;

    @Resource
    WebServiceContext wsctx;

    @WebMethod
    public String sayHello() {
        return "Hello Seefa Web Service...";
    }

    @WebMethod
    public String login() {
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        CredentialRequestDto requestDto = new CredentialRequestDto();
        requestDto.setUsername(userList.get(0) != null ? userList.get(0).toString() : "");
        requestDto.setPassword(passList.get(0) != null ? passList.get(0).toString() : "");
        UserProfileResponseDto login = facade.login(requestDto);
        if (Objects.nonNull(login) && login.getMainMenus().size() > 0) {
            return "Authentication OK";
        } else {
            return "Authentication Failed";
        }
    }
}
