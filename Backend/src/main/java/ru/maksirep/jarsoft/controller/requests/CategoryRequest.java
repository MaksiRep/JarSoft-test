package ru.maksirep.jarsoft.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryRequest {

    public CategoryRequest () {}

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "requestId")
    private String requestId;

    public void setName(String name) {
        this.name = name;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
