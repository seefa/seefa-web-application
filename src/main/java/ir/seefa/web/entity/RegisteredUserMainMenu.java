package ir.seefa.web.entity;

import javax.persistence.*;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 12:43:54
 */
@Entity
@DiscriminatorValue("RegisteredUserMainMenu")
public class RegisteredUserMainMenu extends MenuEntity {
}
