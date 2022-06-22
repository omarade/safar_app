package nl.springboot.safar.controllers;

import lombok.AllArgsConstructor;
import nl.springboot.safar.models.City;
import nl.springboot.safar.models.Site;
import nl.springboot.safar.models.User;
import nl.springboot.safar.models.dto.SiteDto;
import nl.springboot.safar.services.CityService;
import nl.springboot.safar.services.SiteService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/sites")
@CrossOrigin(origins = "http://localhost:3000")
public class SiteController {

    private final SiteService siteService;
    private final CityService cityService;

//    public SiteController(SiteService siteService) {
//        this.siteService = siteService;
//    }

    @GetMapping("")
    public ResponseEntity<List<Site>> getAllSites(@RequestParam(required = false) String city) {
        List<Site> sites = null;

        if (city != null) {
            Optional<City> cityModel = cityService.findByName(city);
            if (cityModel.isPresent()) {
                Integer cityId = cityModel.get().getId();
                sites = siteService.findByCityId(cityId);
            }
        } else {
//            sites = siteService.findAll();
            sites = siteService.findByIsDeleted(false);
        }

        if(sites != null) {
            return ResponseEntity.ok().body(sites);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Site> getSiteBy(@PathVariable Integer id){
        Optional<Site> site = null;
        site = siteService.findById(id);

        if(site.isPresent()) {
            return ResponseEntity.ok().body(site.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Site> createSite(@RequestBody SiteDto siteDto){
//        	public Site(String name, String description, String address, String imgPath, boolean isDeleted, City city) {

        Optional<City> city = cityService.findById(siteDto.getCityId());

        Site site = new Site(siteDto.getName(), siteDto.getDescription(), siteDto.getAddress(), siteDto.getImgPath(), false, city.get());

        siteService.create(site);
        URI location = URI.create(String.format("/sites/" + site.getId()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSite(@PathVariable(required = true) Integer id) {
        Optional<Site> site = siteService.findById(id);
        if(site.isPresent()){
            site.get().setDeleted(true);
            siteService.create(site.get());

        }
        return ResponseEntity.noContent().build();
    }

}
