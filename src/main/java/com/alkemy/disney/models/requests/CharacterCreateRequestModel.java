package com.alkemy.disney.models.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CharacterCreateRequestModel {
    
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Weight is required")
    private Double weight;

    @NotEmpty(message = "History is required")
    private String history;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }



}
