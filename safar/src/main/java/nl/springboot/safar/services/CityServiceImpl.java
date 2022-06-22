package nl.springboot.safar.services;

import lombok.RequiredArgsConstructor;
import nl.springboot.safar.models.City;
import nl.springboot.safar.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;

	public List<City> findAll(){
		return cityRepository.findAll();
	}

	public Optional<City> findById(Integer id){
		return cityRepository.findById(id);
	}

	public Optional<City> findByName(String name) {
		return cityRepository.findByName(name);
	}

	public City create(City city){
		return cityRepository.save(city);
	};
}
