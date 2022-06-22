package nl.springboot.safar.repositories;

import nl.springboot.safar.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
	public Optional<City> findByName(String name);
}
