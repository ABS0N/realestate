package hu.kiralybalazs.realestate.controller;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController // <-- Ez a kulcs a Swaggerhez!
@RequestMapping("/api/properties") // <-- Külön útvonal az API-nak
public class PropertyApiController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @PostMapping
    public Property createProperty(@Valid @RequestBody Property property) {
        return propertyService.save(property);
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.findById(id).orElse(null);
    }
}