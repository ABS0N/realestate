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
}