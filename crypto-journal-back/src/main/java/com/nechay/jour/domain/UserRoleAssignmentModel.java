package com.nechay.jour.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Table(name = "user_role_assignment")
public class UserRoleAssignmentModel {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id")
    private UserRoleModel role;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private UserModel user;

//    @Column(name = "time_of_assignment")
    private ZonedDateTime timeOfAssignment;

    public UserRoleAssignmentModel() {
    }

    public UserRoleAssignmentModel(long id, UserRoleModel role, UserModel user, ZonedDateTime timeOfAssignment) {
        this.id = id;
        this.role = role;
        this.user = user;
        this.timeOfAssignment = timeOfAssignment;
    }

    public long getId() {
        return id;
    }

    public UserRoleModel getRole() {
        return role;
    }

    public UserModel getUser() {
        return user;
    }

    public ZonedDateTime getTimeOfAssignment() {
        return timeOfAssignment;
    }
}
