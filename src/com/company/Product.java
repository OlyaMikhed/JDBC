package com.company;

public class Product extends Entity {

    private String title;
    private Double cost;
    private Integer brand_id;

    public Product() {
    }

    public Product(String title, Double cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, Double cost, Integer brand_id) {
        this.title = title;
        this.cost = cost;
        this.brand_id = brand_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", cost=" + cost +
                ", brand_id='" + brand_id + '\'' +
                '}';
    }
}
