package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * User role entity
 */
@Component
@Entity
@Table(name="users")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User implements Serializable {

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.dayOfBirth = userBuilder.dayOfBirth;
        this.role = userBuilder.role;
    }

    /**
     * User's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * User's email
     */
    @Column(name="email",nullable = false,length = 40)
    private String email;
    /**
     * User's password
     */
    @Column(name="password",length = 500)
    @JsonIgnore
    private String password;
    /**
     * User's name
     */
    @Column(name="name",length = 40)
    private String name;
    /**
     * User's surname
     */
    @Column(name="surname",length = 50)
    private String surname;
    /**
     * User's day of birthday
     */
    @Column(name="day_of_birth")
    private LocalDate dayOfBirth;
    /**
     * User's role {@link Role}
     */
    @ManyToOne
    @JoinColumn(name="user_role",referencedColumnName = "id",nullable = false)
    private Role role;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", role=" + role +
                '}';
    }

    /**
     * User's builder
     */
    public static class UserBuilder{

        public UserBuilder(String email,String password){
            this.email = email;
            this.password = password;
        }
        private String email;
        private String password;
        private String name;
        private String surname;
        private LocalDate dayOfBirth;
        private Role role;

        public UserBuilder setName(String name){
            this.name = name;
            return this;
        }
        public UserBuilder setSurname(String surname){
            this.surname = surname;
            return this;
        }
        public UserBuilder setDayOfBirth(LocalDate dayOfBirth){
            this.dayOfBirth = dayOfBirth;
            return this;
        }
        public UserBuilder setRole(Role role){
            this.role = role;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
