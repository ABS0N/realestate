package hu.kiralybalazs.realestate.controller;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.repository.CategoryRepository;
import hu.kiralybalazs.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listProperties(Model model) {
        model.addAttribute("properties", propertyService.findAll());
        return "property-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("categories", categoryRepository.findAll());
        return "property-form";
    }

    @PostMapping("/save")
    public String saveProperty(@ModelAttribute("property") Property property) {
        propertyService.save(property);
        return "redirect:/properties";
    }
}