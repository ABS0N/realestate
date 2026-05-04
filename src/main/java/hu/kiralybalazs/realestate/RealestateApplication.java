package hu.kiralybalazs.realestate;

import hu.kiralybalazs.realestate.model.Category;
import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.CategoryRepository;
import hu.kiralybalazs.realestate.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealestateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepo, PropertyRepository propertyRepo) {
        return args -> {
            // Kategóriák létrehozása
            Category haz = categoryRepo.save(new Category("Családi ház"));
            Category lakas = categoryRepo.save(new Category("Panellakás"));

            // Ingatlanok létrehozása és mentése
            propertyRepo.save(new Property("Budapest, Fő utca 1.", 45000000, 85, haz));
            propertyRepo.save(new Property("Debrecen, Pláza mellett 4.", 28000000, 52, lakas));

            System.out.println("--- Mintaadatok betöltve! ---");
        };
    }
}