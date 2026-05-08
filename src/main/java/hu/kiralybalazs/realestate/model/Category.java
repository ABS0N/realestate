package hu.kiralybalazs.realestate.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Ez az ÜRES konstruktor KELL a JPA-nak
    public Category() {}

    // Ez a konstruktor KELL a "new Category("Név")" híváshoz
    public Category(String name) {
        this.name = name;
    }

    // ... getterek és setterek ...
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