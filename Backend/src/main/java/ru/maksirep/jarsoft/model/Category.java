package ru.maksirep.jarsoft.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    public Category () {}

    public Category (String name, String requestId, boolean deleted) {
        this.name = name;
        this.requestId = requestId;
        this.deleted = deleted;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "request_name")
    String requestId;

    @Column(name = "deleted")
    boolean deleted;



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return deleted;
    }

}
