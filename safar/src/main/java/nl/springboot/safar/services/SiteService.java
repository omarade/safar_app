package nl.springboot.safar.services;

import nl.springboot.safar.models.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {
	List<Site> findAll();

	Optional<Site> findById(Integer id);

	Site create(Site site);

	List<Site> findByCityId(Integer cityId);

	List<Site> findByIsDeleted(boolean isDeleted);

//	public void deleteById(Integer id);
}
