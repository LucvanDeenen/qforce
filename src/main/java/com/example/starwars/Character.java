package com.example.starwars;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;

import java.util.List;

public class Character implements Person {

    @JsonProperty("id")
    public long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("birth_year")
    public String birthYear;

    @JsonProperty("gender")
    public Gender gender;

    @JsonProperty("height")
    public Integer height;

    @JsonProperty("weight")
    public Integer weight;

    @JsonProperty("movies")
    public List<Movie> movies;

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name.replaceAll(" ", "_").toLowerCase();
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear.replaceAll(" ", "_").toLowerCase();
    }

    public void setGender(String gender) {
        switch (gender) {
            case "male":
                this.gender = Gender.MALE;
                break;
            case "female":
                this.gender = Gender.FEMALE;
                break;
            case "n/a":
                this.gender = Gender.NOT_APPLICABLE;
                break;
            default:
                this.gender = Gender.UNKNOWN;
                break;
        }
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
