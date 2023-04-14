package com.java.sparketl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//city  	lat	    lng 	country	    iso2	admin_name	    capital	    population	    population_proper
@Setter
@Getter
@Entity
@Table(name="india_cities")
public class IndiaCities {

    @Id
    @Column(name = "city")
    String city;

    @Column(name = "lat")
    String lat;

    @Column(name = "lng")
    String lng;

    @Column(name = "country")
    String country;

    @Column(name = "iso2")
    String iso2;

    @Column(name = "admin_name")
    String admin_name;

    @Column(name = "capital")
    String capital;

    @Column(name = "population")
    String population;

    @Column(name = "population_proper")
    String population_proper;

    public IndiaCities() {
    }

    public IndiaCities(String city, String lat, String lng, String country, String iso2, String admin_name, String capital, String population, String population_proper) {
        this.city = city;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
        this.iso2 = iso2;
        this.admin_name = admin_name;
        this.capital = capital;
        this.population = population;
        this.population_proper = population_proper;
    }

    @Override
    public String toString() {
        return "IndiaCities{" +
                "city=" + city +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", country='" + country + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", capital='" + capital + '\'' +
                ", population='" + population + '\'' +
                ", population_proper='" + population_proper + '\'' +
                '}';
    }
}
