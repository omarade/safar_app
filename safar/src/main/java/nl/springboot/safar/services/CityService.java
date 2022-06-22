package nl.springboot.safar.services;

import nl.springboot.safar.models.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
	public List<City> findAll();

	public Optional<City> findById(Integer id);

	public Optional<City> findByName(String name);

	public City create(City city);

//	public void deleteById(Integer id);
}
