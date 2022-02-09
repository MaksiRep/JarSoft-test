package ru.maksirep.jarsoft.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "logs")
public class Logs {

    public Logs () {}

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "date")
    private Date date = new Date(System.currentTimeMillis());

    @Column(name = "price")
    private double price;

    @Column(name = "no_content_reason")
    private String noContentReason;

    @ManyToOne
    private Banner banner;

    @ManyToMany
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Logs(String userAgent, String ipAddress, double price, String noContentReason, Banner banner, List<Category> categories) {
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.price = price;
        this.noContentReason = noContentReason;
        this.banner = banner;
        this.categories = categories;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNoContentReason(String noContentReason) {
        this.noContentReason = noContentReason;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public int getId() {
        return id;
    }


    public String getUserAgent() {
        return userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getNoContentReason() {
        return noContentReason;
    }

    public Banner getBanner() {
        return banner;
    }

}
