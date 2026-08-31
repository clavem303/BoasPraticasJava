package tech.clavem303.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

    public Pet() {
        //for jackson
    }

    public Pet(String type, String name, String breed, int age, String color, float weight) {
        this.type = type;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.weight = weight;
    }

    private long id;
    @JsonProperty("tipo")
    private String type;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("raca")
    private String breed;
    @JsonProperty("idade")
    private int age;
    @JsonProperty("cor")
    private String color;
    @JsonProperty("peso")
    private float weight;

    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getBreed() {
        return breed;
    }
    public int getAge() {
        return age;
    }
}
