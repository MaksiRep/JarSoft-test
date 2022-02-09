package ru.maksirep.jarsoft.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BannerRequest {

    public BannerRequest() {}

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "price")
    private double price;

    @JsonProperty(value = "textField")
    private String textField;

    @JsonProperty(value = "categories")
    private List<String> categories;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

}
