package com.nechay.jour.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Table(name = "user_role_permission")
public class UserRolePermissionModel {

    @Id

    private long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id")
    private UserRoleModel role;

//    @Column
//    @Enumerated(value= EnumType.STRING)
    private UserPermission userPermission;

    public UserRolePermissionModel() {
    }

    public UserRolePermissionModel(long id, UserRoleModel role, UserPermission userPermission) {
        this.id = id;
        this.role = role;
        this.userPermission = userPermission;
    }

    public UserRoleModel getRole() {
        return role;
    }

    public UserPermission getUserPermission() {
        return userPermission;
    }
}
