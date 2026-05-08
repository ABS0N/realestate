package hu.kiralybalazs.realestate.controller;

import hu.kiralybalazs.realestate.model.Property;
import hu.kiralybalazs.realestate.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Visszavonja a teszt közben létrehozott adatokat
class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyService propertyService; // Injektáljuk, hogy tudjunk teszt adatot csinálni

    @Test
    void testListProperties_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/properties"))
                .andExpect(status().isOk())
                .andExpect(view().name("property-list"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testShowCreateForm_ShouldReturnFormView() throws Exception {
        mockMvc.perform(get("/properties/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("property-form"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testSaveProperty_ShouldRedirectToList() throws Exception {
        // Mentés tesztelése (POST kérés) CSRF tokennel
        mockMvc.perform(post("/properties/save")
                        .with(csrf()) // Ez KÖTELEZŐ a Spring Security miatt!
                        .param("address", "Teszt utca 99.")
                        .param("price", "15000000")
                        .param("area", "60"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/properties"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testEditProperty_ShouldReturnFormView() throws Exception {
        // Létrehozunk egy valós ingatlant, amit majd szerkesztünk
        Property property = new Property();
        property.setAddress("Szerkesztendő utca");
        property.setPrice(10000);
        property.setArea(10);
        Property saved = propertyService.save(property);

        // Szerkesztés oldal betöltése
        mockMvc.perform(get("/properties/edit/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("property-form"))
                .andExpect(model().attributeExists("property"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteProperty_ShouldRedirectToList() throws Exception {
        // Létrehozunk egy valós ingatlant, amit majd törlünk
        Property property = new Property();
        property.setAddress("Törlendő utca");
        property.setPrice(10000);
        property.setArea(10);
        Property saved = propertyService.save(property);

        // Törlés végpont meghívása
        mockMvc.perform(get("/properties/delete/" + saved.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/properties"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testEditProperty_WithInvalidId_ShouldHandleException() throws Exception {
        // Egy biztosan nem létező ID-t kérünk le
        mockMvc.perform(get("/properties/edit/9999999"))
                .andExpect(status().is3xxRedirection()); // Vagy isNotFound(), attól függ, hogyan van beállítva a hibakezelőd
    }
}