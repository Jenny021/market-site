package com.example.app.model;

import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name = "article")
public class Article {
    @Id
    private String uuid;
    private String name;

    private double cost;

    public Article(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.uuid = UUID.randomUUID().toString();
    }

    public Article() {
        this("", 0.0);
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}