package nl.springboot.safar.controllers;

import nl.springboot.safar.models.Site;
import nl.springboot.safar.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/sites")
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> sites = null;
        sites = siteService.getAllSites();

        if(sites != null) {
            return ResponseEntity.ok().body(sites);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
