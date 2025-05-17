package com.raydow.coffee_score.service.vo;

public class CoffeeSpotVO {
    private Long id;
    private String name;

    public CoffeeSpotVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CoffeeSpotVO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
