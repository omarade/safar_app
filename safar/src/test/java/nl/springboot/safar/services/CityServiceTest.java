package nl.springboot.safar.services;

import nl.springboot.safar.models.City;
import nl.springboot.safar.models.City;
import nl.springboot.safar.repositories.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
	@InjectMocks
	CityServiceImpl cityService;

	@Mock
	CityRepository cityRepository;


	@Test
	public void getAllCities () {
		City city1 = new City(1, "Amsterdam", "info", "imgPath", false);
		City city2 = new City(2, "city2", "info","imgPath", false);
		City city3 = new City(3, "city3", "info","imgPath", false);



		List<City> expectedCities = Arrays.asList( city1, city2, city3);
		when(cityRepository.findAll()).thenReturn(
				expectedCities
		);

		List<City> actualCities = cityService.findAll();

		Assertions.assertEquals(expectedCities, actualCities);
		Assertions.assertEquals(expectedCities.size(), actualCities.size());
	}

	@Test
	public void getAllCities_noCities () {
		List<City> expectedCities = new ArrayList<>();

		when(cityRepository.findAll()).thenReturn(
				expectedCities
		);

		List<City> actualCities = cityService.findAll();

		Assertions.assertEquals(expectedCities, actualCities);
		Assertions.assertEquals(expectedCities.size(), actualCities.size());
	}

	@Test
	public void getCityById () {
		City city = new City(1, "Amsterdam", "info", "imgPath", false);
		Optional<City> expectedCity = Optional.of(new City(1, "Amsterdam", "info", "imgPath", false));

		when(cityRepository.findById(1)).thenReturn(
				expectedCity
		);

		Optional<City> actualCity = cityService.findById(1);

		Assertions.assertEquals(expectedCity, actualCity);
		Assertions.assertEquals(expectedCity.get().getId(), actualCity.get().getId());
		Assertions.assertEquals(expectedCity.get().getName(), actualCity.get().getName());
		Assertions.assertEquals(expectedCity.get().getInfo(), actualCity.get().getInfo());
		Assertions.assertEquals(expectedCity.get().getImgPath(), actualCity.get().getImgPath());
	}

	@Test
	public void getCityById_noCity () {
		Optional<City> expectedCity = Optional.empty();

		when(cityRepository.findById(1)).thenReturn(
				expectedCity
		);

		Optional<City> actualCity = cityService.findById(1);

		Assertions.assertEquals(expectedCity, actualCity);
	}

	@Test
	public void createCity () {
		City city = new City(1, "Amsterdam", "info", "imgPath", false);

		when(cityRepository.save(city)).thenReturn(
				city
		);

		City actualCity = cityService.create(city);

		Assertions.assertEquals(city, actualCity);
	}
}
