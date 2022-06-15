package nl.springboot.safar.services;

import nl.springboot.safar.models.City;
import nl.springboot.safar.models.Site;
import nl.springboot.safar.repositories.SiteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class SiteServiceTest {

	@InjectMocks
	SiteServiceImpl siteService;

	@Mock
	SiteRepository siteRepository;


	@Test
	public void getAllSites () {
		City city = new City(1, "Amsterdam", "info", "imgPath", false);

		List<Site> expectedSites = Arrays.asList(
			new Site(1, "site1", "","Historical","address 1", false),
			new Site(2, "site2", "","Historical","address 2", false),
			new Site(3, "site3", "","Historical","address 3", false)
		);
		when(siteRepository.findAll()).thenReturn(
			expectedSites
		);

		List<Site> actualSites = siteService.findAll();

		Assertions.assertEquals(expectedSites, actualSites);
		Assertions.assertEquals(expectedSites.size(), actualSites.size());
	}

	@Test
	public void getAllSites_noSites () {
		List<Site> expectedSites = new ArrayList<>();

		when(siteRepository.findAll()).thenReturn(
				expectedSites
		);

		List<Site> actualSites = siteService.findAll();

		Assertions.assertEquals(expectedSites, actualSites);
		Assertions.assertEquals(expectedSites.size(), actualSites.size());
	}

	@Test
	public void getSiteById () {
		City city = new City(1, "Amsterdam", "info", "imgPath", false);
		Optional<Site> expectedSite = Optional.of(new Site(1, "site1", "","Historical","address 1", false, city));

		when(siteRepository.findById(1)).thenReturn(
				expectedSite
		);

		Optional<Site> actualSite = siteService.findById(1);

		Assertions.assertEquals(expectedSite, actualSite);
		Assertions.assertEquals(expectedSite.get().getName(), actualSite.get().getName());
		Assertions.assertEquals(expectedSite.get().getAddress(), actualSite.get().getAddress());
		Assertions.assertEquals(expectedSite.get().getDescription(), actualSite.get().getDescription());
		Assertions.assertEquals(expectedSite.get().getImgPath(), actualSite.get().getImgPath());
		Assertions.assertEquals(expectedSite.get().getId(), actualSite.get().getId());
	}

	@Test
	public void getSiteById_noSite () {
		Optional<Site> expectedSite = Optional.empty();

		when(siteRepository.findById(1)).thenReturn(
				expectedSite
		);

		Optional<Site> actualSite = siteService.findById(1);

		Assertions.assertEquals(expectedSite, actualSite);
	}

	@Test
	public void createSite () {
		City city = new City(1, "Amsterdam", "info", "imgPath", false);
		Site site = new Site(1, "site1", "","Historical","address 1", false, city);

		when(siteRepository.save(site)).thenReturn(
				site
		);

		Site actualSite = siteService.create(site);

		Assertions.assertEquals(site, actualSite);
	}
}
