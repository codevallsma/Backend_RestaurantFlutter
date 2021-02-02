package com.codevallsma.loginTemplate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "TERRASSA")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "OCUPACIO")
    private String ocupacio;
    @Column(name = "EMPLAÇAMENT")
    private String emplacamament;
    @Column(name = "SUPERFICIE_OCUPADA")
    private Float superficieOcupada;
    @Column(name = "TAULES")
    private int taules;
    @Column(name = "CADIRES")
    private int cadires;
    @Column(name = "LATITUD")
    private double latitud;
    @Column(name = "LONGITUD")
    private double longitud;
    private String restaurantName;


    public Restaurant(long id, String ocupacio, String emplacamament, Float superficieOcupada,  int taules,  int cadires, double latitud, double longitud ) {
        this.id =id;
        this.ocupacio = ocupacio;
        this.emplacamament = emplacamament;
        this.superficieOcupada = superficieOcupada;
        this.taules = taules;
        this.cadires = cadires;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Restaurant(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOcupacio() {
        return ocupacio;
    }

    public void setOcupacio(String ocupacio) {
        this.ocupacio = ocupacio;
    }

    public String getEmplacamament() {
        return emplacamament;
    }

    public void setEmplacamament(String emplacamament) {
        this.emplacamament = emplacamament;
    }

    public Float getSuperficieOcupada() {
        return superficieOcupada;
    }

    public void setSuperficieOcupada(Float superficieOcupada) {
        this.superficieOcupada = superficieOcupada;
    }

    public int getTaules() {
        return taules;
    }

    public void setTaules(int taules) {
        this.taules = taules;
    }

    public int getCadires() {
        return cadires;
    }

    public void setCadires(int cadires) {
        this.cadires = cadires;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
