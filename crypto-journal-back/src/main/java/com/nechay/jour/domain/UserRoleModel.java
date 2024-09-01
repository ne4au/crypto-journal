package com.nechay.jour.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Table(name = "user_role")
public class UserRoleModel {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRoleAssignmentModel> userRoleAssignments;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRolePermissionModel> rolePermissions;

    public UserRoleModel() {
    }

    public UserRoleModel(long id, Set<UserRoleAssignmentModel> userRoleAssignments, Set<UserRolePermissionModel> rolePermissions) {
        this.id = id;
        this.userRoleAssignments = userRoleAssignments;
        this.rolePermissions = rolePermissions;
    }

    public long getId() {
        return id;
    }

    public Set<UserRoleAssignmentModel> getUserRoleAssignments() {
        return userRoleAssignments;
    }

    public Set<UserRolePermissionModel> getRolePermissions() {
        return rolePermissions;
    }
}
