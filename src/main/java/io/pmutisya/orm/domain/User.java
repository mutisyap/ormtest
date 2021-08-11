package io.pmutisya.orm.domain;

import io.pmutisya.orm.domain.enumeration.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_users")
public class    User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

        @Column(name = "created_at")
    private String createdAt;

    @Column(name = "organisation_id")
    private Long organisationId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", organisationId=" + organisationId +
                '}';
    }
}
