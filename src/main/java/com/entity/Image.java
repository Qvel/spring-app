package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity used for :
 * images for {@link Place} places
 * images for {@link User} users
 * images for {@link Event} events
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="images")
public class Image implements Serializable {
    public Image() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "path",nullable = false,length = 400)
    private String path;
    @Column(name="is_logo")
    private Boolean isLogo;
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(
            name="images_of_places",
            joinColumns = {
                    @JoinColumn(
                            name= "image_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "place_id",
                            nullable = false
                    )
            }

    )
    @JsonIgnore
    private Set<Place> places = new HashSet<>();
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(
            name="images_of_events",
            joinColumns = {
                    @JoinColumn(
                            name= "image_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "event_id",
                            nullable = false
                    )
            }
    )
    @JsonIgnore
    private Set<Event> events = new HashSet<>();
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(
            name = "images_of_users",
            joinColumns = {
                    @JoinColumn(
                            name = "image_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false
                    )
            }
    )
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getLogo() {
        return isLogo;
    }

    public void setLogo(Boolean logo) {
        isLogo = logo;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(path, image.path) &&
                Objects.equals(isLogo, image.isLogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, isLogo);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", isLogo=" + isLogo +
                ", places=" + places +
                ", events=" + events +
                ", users=" + users +
                '}';
    }
}
