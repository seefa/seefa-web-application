package ir.seefa.web.dto;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 26 Jul 2020 T 10:48:11
 */
public class CredentialRequestDto {
    // sample usage define Json property name
    @JsonbProperty("username")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
