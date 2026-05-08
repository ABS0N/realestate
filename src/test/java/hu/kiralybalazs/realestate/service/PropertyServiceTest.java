package hu.kiralybalazs.realestate.service;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    @Test
    public void testFindAll() {
        // Given
        Property p1 = new Property();
        Property p2 = new Property();
        when(propertyRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // When
        List<Property> result = propertyService.findAll();

        // Then
        assertEquals(2, result.size());
        verify(propertyRepository, times(1)).findAll();
    }

    @Test
    public void testSaveProperty() {
        // Given
        Property property = new Property();
        property.setAddress("Budapest, Petőfi utca 5.");
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        // When
        Property saved = propertyService.save(property);

        // Then
        assertNotNull(saved);
        assertEquals("Budapest, Petőfi utca 5.", saved.getAddress());
    }

    @Test
    public void testFindById() {
        // Given
        Property property = new Property();
        property.setId(1L);
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        // When
        Optional<Property> found = propertyService.findById(1L);

        // Then
        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }
}