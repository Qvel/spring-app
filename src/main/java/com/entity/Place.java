package com.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity Places used for:
 * users {@link User} places (where he can go or was already)
 * places {@link Category} for categories  (list of places for each category)
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="places")
public class Place implements Serializable {
    public Place() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 500)
    private String name;

    @Column(name= "description",nullable = false,length = 2000)
    private String description;

    @Column(name="longitude",nullable = false,length = 200)
    private String longitude;

    @Column(name = "latitude",nullable = false,length = 200)
    private String latitude;

    @ManyToMany(mappedBy = "places",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "places",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();


}
