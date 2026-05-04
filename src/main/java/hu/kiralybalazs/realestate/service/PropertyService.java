package hu.kiralybalazs.realestate.service;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property save(Property property) {
        // Ide később írhatunk ellenőrzéseket a jeles jegyhez
        return propertyRepository.save(property);
    }
}