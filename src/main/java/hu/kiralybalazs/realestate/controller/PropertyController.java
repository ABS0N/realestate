package hu.kiralybalazs.realestate.controller;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.CategoryRepository;
import hu.kiralybalazs.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties") // Minden, ami /properties-szel kezdődik, ide jön
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. LISTÁZÁS (Read)
    @GetMapping
    public String listProperties(Model model) {
        // Lekérjük az összes ingatlant és betesszük a "properties" nevű listába a HTML-nek
        model.addAttribute("properties", propertyRepository.findAll());
        return "property-list"; // Ez a templates/property-list.html fájlra mutat
    }

    // 2. ÚJ INGATLAN ŰRLAP (Create - megjelenítés)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("categories", categoryRepository.findAll());
        return "property-form"; // Ez a templates/property-form.html fájlra mutat
    }

    // 3. MENTÉS (Create - feldolgozás)
    @PostMapping("/save")
    public String saveProperty(@ModelAttribute("property") Property property) {
        propertyRepository.save(property);
        return "redirect:/properties"; // Mentés után visszaugrik a listára
    }
}