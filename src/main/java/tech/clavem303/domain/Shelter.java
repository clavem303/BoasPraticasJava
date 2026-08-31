package tech.clavem303.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shelter {

    public Shelter(){
        //for jackson
    }

    public Shelter(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    private long id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("telefone")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    private Pet[] pets;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public Pet[] getPets() {
        return pets;
    }
}
