package hu.kiralybalazs.realestate.service;

import hu.kiralybalazs.realestate.model.Category;
import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Varázsszó: A teszt végén mindent visszavon (Rollback), így nem szemeteli tele az adatbázist!
class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFullCrudLifecycle() {
        // 1. CREATE (Létrehozás)
        Category category = new Category();
        category.setName("Teszt Kategória");
        categoryRepository.save(category);

        Property property = new Property();
        property.setAddress("Teszt utca 1.");
        property.setPrice(10000000);
        property.setArea(50);
        property.setCategory(category);

        Property savedProperty = propertyService.save(property);
        assertNotNull(savedProperty.getId(), "A mentett ingatlannak ID-t kell kapnia");

        // 2. READ (Olvasás)
        Property foundProperty = propertyService.findById(savedProperty.getId()).orElse(null);
        assertNotNull(foundProperty, "Az ingatlant meg kell találni az adatbázisban");
        assertEquals("Teszt utca 1.", foundProperty.getAddress());

        // 3. UPDATE (Módosítás)
        foundProperty.setPrice(12000000);
        propertyService.save(foundProperty);
        Property updatedProperty = propertyService.findById(savedProperty.getId()).get();
        assertEquals(12000000, updatedProperty.getPrice(), "Az árnak frissülnie kellett");

        // 4. DELETE (Törlés)
        propertyService.deleteById(savedProperty.getId());
        assertTrue(propertyService.findById(savedProperty.getId()).isEmpty(), "Törlés után nem szabad megtalálni");
    }
}