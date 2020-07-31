package ir.seefa.web.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 12:43:56
 */
@Entity
@Table(name = "User", schema = "seefasitev1", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = UserEntity.FIND_BY_ID, query = "select u from UserEntity u where u.id =:id"),
        @NamedQuery(name = UserEntity.FIND_BY_USERNAME, query = "select u from UserEntity u where u.username =:username"),
        @NamedQuery(name = UserEntity.FIND_ACTIVE_USER_BY_USERNAME, query = "select u from UserEntity u where u.userActive = 1 and u.username =:username")
})
public class UserEntity {
    public static final String FIND_BY_ID = "findUserById";
    public static final String FIND_BY_USERNAME = "findUserByUsername";
    public static final String FIND_ACTIVE_USER_BY_USERNAME = "findActiveUserByUsername";

    private int userId;
    private String name;
    private String family;
    private String username;
    private String email;
    private Byte userActive;
    private String userObject;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String imageProfile;
    private String password;

    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 75)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "family", nullable = true, length = 75)
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 75)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "userActive", nullable = true)
    public Byte getUserActive() {
        return userActive;
    }

    public void setUserActive(Byte userActive) {
        this.userActive = userActive;
    }

    @Basic
    @Column(name = "userObject", nullable = true)
    public String getUserObject() {
        return userObject;
    }

    public void setUserObject(String userObject) {
        this.userObject = userObject;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "updateDate", nullable = true)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "imageProfile", nullable = true, length = -1)
    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                Objects.equals(name, that.name) &&
                Objects.equals(family, that.family) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userActive, that.userActive) &&
                Objects.equals(userObject, that.userObject) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(imageProfile, that.imageProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, family, username, email, userActive, userObject, createDate, updateDate, imageProfile);
    }
}
