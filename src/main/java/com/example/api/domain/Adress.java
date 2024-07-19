package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "ADRESS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("cep")
    private String postalCode;
    @JsonAlias("logradouro")
    private String street;
    @JsonAlias("complemento")
    private String complement;
    @JsonAlias("unidade")
    private String unit;
    @JsonAlias("bairro")
    private String neighborhood;
    @JsonAlias("localidade")
    private String city;
    @JsonAlias("uf")
    private String state;

    public Long getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getUnit() {
        return unit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

}
