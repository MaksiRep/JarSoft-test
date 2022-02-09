package ru.maksirep.jarsoft.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "banners")
public class Banner {

    public Banner (){}

    public Banner (String name, double price, String textField, List<Category> categoryList, boolean deleted) {
        this.name =name;
        this.price = price;
        this.textField = textField;
        this.categories = categoryList;
        this.deleted = deleted;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue
    int id;

    @Column(name = "name" )
    String name;

    @Column(name = "banner_text")
    String textField;

    @Column( name = "price" )
    double price;

    @ManyToMany (cascade=CascadeType.ALL)
    @JoinTable
    private List<Category> categories;


    @Column(name = "deleted")
    boolean deleted;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return deleted;
    }

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

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getTextField() {
        return textField;
    }
}
