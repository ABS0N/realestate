package hu.kiralybalazs.realestate.model;

import jakarta.persistence.*;

@Entity
public class Property {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Integer price;
    private Integer area;

    @ManyToOne
    private Category category;

    public Property() {}

    // Ez a 4 paraméteres konstruktor KELL a RealestateApplication-be
    public Property(String address, Integer price, Integer area, Category category) {
        this.address = address;
        this.price = price;
        this.area = area;
        this.category = category;
    }

    // ... getterek és setterek ...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    

}