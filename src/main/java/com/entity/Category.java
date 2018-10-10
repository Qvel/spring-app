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
 * Entity Category
 * used for {@link User} users categories
 * used for {@link Event} events categories
 * used for {@link Place} places categories
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="categories")
public class Category implements Serializable {

    public Category() {
    }

    public Category(String name, Set<Place> places, Set<Event> events, Set<Filter> filters, Set<User> users) {
        this.name = name;
        this.places = places;
        this.events = events;
        this.filters = filters;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 400)
    private String name;
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(
            name="places_categories",
            joinColumns = {
                    @JoinColumn(
                            name= "category_id",
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
        name="events_categories",
        joinColumns = {
                @JoinColumn(
                        name= "category_id",
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
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="categories_filters",
            joinColumns = {
                    @JoinColumn(
                            name= "category_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "filter_id",
                            nullable = false
                    )
            }
    )
    private Set<Filter> filters = new HashSet<>();
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_categories",
            joinColumns = {
                    @JoinColumn(
                            name = "category_id",
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
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
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", places=" + places +
                ", events=" + events +
                ", filters=" + filters +
                ", users=" + users +
                '}';
    }
}
