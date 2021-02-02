package com.codevallsma.loginTemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String categoria;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Restaurant_Category", joinColumns = {
            @JoinColumn(name = "CATEGORY_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "RESTAURANT_ID") })
    private Set<Restaurant> categoria_restaurant;

    public Category() {
    }

    public Category(String categoria) {
        this.categoria = categoria;
    }

    /****************************************************************************************
     *
     *                                      GETTERS I SETTERS
     *
     ****************************************************************************************/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<Restaurant> getCategoria_restaurant() {
        return categoria_restaurant;
    }

    public void setCategoria_restaurant(Set<Restaurant> categoria_restaurant) {
        this.categoria_restaurant = categoria_restaurant;
    }
}
