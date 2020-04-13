package com.codevallsma.loginTemplate.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents the MYSQL table for Users
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String email;
    @Column(name = "username")
    @ColumnTransformer(
            read = "AES_DECRYPT(UNHEX(username), 'ankon')",
            write = "HEX(AES_ENCRYPT(?, 'ankon'))"
    )
    private String username;
    @JsonIgnore
    private String password;
    private Boolean isActive;
    @Transient
    private String passwordConfirm;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * Constructor
     * @param id: The id of the user
     */
    public User(long id) {
        this.id = id;
    }

    /**
     * Constructor
     * @param userName
     * @param email
     * @param password
     */
    public User(String userName, String email, String password) {
        this.email = email;
        this.username = userName;
        this.password = password;
        this.isActive=true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Checks if this class is the same as another class given by parameters
     * @param o An object
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
    /**
     * To string function
     * @return A string with the information of this class
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    /****************************************************************************************
     *
     *                                      GETTERS I SETTERS
     *
     ****************************************************************************************/

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
