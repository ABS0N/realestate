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
    CommandLineRunner init(PropertyRepository propertyRepository, CategoryRepository categoryRepository) {
        return args -> {
            // ELLENŐRZÉS: Csak akkor töltünk, ha még nincs egyetlen ingatlan sem
            if (propertyRepository.count() == 0) {

                Category c1 = new Category();
                c1.setName("Családi ház");
                categoryRepository.save(c1);

                Category c2 = new Category();
                c2.setName("Panellakás");
                categoryRepository.save(c2);

                propertyRepository.save(new Property("Budapest, Fő utca 1.", 45000000, 85, c1));
                propertyRepository.save(new Property("Debrecen, Pláza mellett 4.", 28000000, 52, c2));

                System.out.println("Adatbázis inicializálva kezdőadatokkal.");
            } else {
                System.out.println("Az adatbázis már tartalmaz adatokat, az inicializálás átugorva.");
            }
        };
    }
}