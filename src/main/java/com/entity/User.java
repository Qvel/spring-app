package com.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User implements Serializable {

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.age = userBuilder.age;
    }
    private String name;
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static class UserBuilder{
        private String name;
        private Long age;
        public UserBuilder setName(String name){
            this.name = name;
            return this;
        }
        public UserBuilder setAge(Long age){
            this.age = age;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
