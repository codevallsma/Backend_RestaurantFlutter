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
}
