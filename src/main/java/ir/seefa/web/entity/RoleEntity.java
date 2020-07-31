package ir.seefa.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 12:43:55
 */
@Entity
@Table(name = "Role", schema = "seefasitev1", catalog = "")
public class RoleEntity {
    private int roleId;
    private String roleName;
    private byte roleDisabled;
    private int roleType;

    @Id
    @Column(name = "roleId", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "roleName", nullable = false, length = 75)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "roleDisabled", nullable = false)
    public byte getRoleDisabled() {
        return roleDisabled;
    }

    public void setRoleDisabled(byte roleDisabled) {
        this.roleDisabled = roleDisabled;
    }

    @Basic
    @Column(name = "roleType", nullable = false)
    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId &&
                roleDisabled == that.roleDisabled &&
                roleType == that.roleType &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDisabled, roleType);
    }
}
