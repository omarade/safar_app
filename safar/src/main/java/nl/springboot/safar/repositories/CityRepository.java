package nl.springboot.safar.repositories;

import nl.springboot.safar.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
