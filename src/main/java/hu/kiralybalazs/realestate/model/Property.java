package hu.kiralybalazs.realestate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Lombok generálja a háttérben
@AllArgsConstructor // Generál egy olyan konstruktort, amiben minden benne van
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A cím kötelező!")
    @Size(min = 5, max = 100, message = "A cím 5 és 100 karakter között legyen!")
    private String address;

    @NotNull(message = "Az ár nem maradhat üresen!")
    @Min(value = 1, message = "Az ár nem lehet negatív vagy nulla!")
    private Integer price;

    @NotNull(message = "Az alapterület nem maradhat üresen!")
    @Positive(message = "Az alapterületnek pozitívnak kell lennie!")
    private Integer area;

    @ManyToOne
    private Category category;

    public Property() {
    }

    // A te egyedi konstruktorod (ez maradjon meg!)
    public Property(String address, Integer price, Integer area, Category category) {
        this.address = address;
        this.price = price;
        this.area = area;
        this.category = category;
    }
}