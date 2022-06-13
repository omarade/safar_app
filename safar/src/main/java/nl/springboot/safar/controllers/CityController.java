package nl.springboot.safar.controllers;

import nl.springboot.safar.models.City;
import nl.springboot.safar.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {

	private final CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping("")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> cities = null;
		cities = cityService.findAll();

		if(cities != null) {
			return ResponseEntity.ok().body(cities);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<City> GetCity(@PathVariable(value = "id") int id) {
		Optional<City> city = cityService.findById(id);

		if(city.isPresent()) {
			return ResponseEntity.ok().body(city.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping()
	public ResponseEntity<City> CreateCity(@RequestBody City city){
		cityService.create(city);
		URI location = URI.create(String.format("/cities/" + city.getId()));
		return ResponseEntity.created(location).build();
	}
}
