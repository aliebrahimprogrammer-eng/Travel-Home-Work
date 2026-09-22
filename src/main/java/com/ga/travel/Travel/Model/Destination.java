package com.ga.travel.Travel.Model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class Destination {

    @JsonIgnore
    public Destination(int id, String name, String country, String continent, String category, double rating, String priceLevel, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.continent = continent;
        this.category = category;
        this.rating = rating;
        this.priceLevel = priceLevel;
        this.description = description;
    }

    @JsonCreator
    public Destination() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int id;
    private String name;
    private String country;
    private String continent;
    private String category;
    private double rating;
    private String priceLevel;
    private String description;


}
