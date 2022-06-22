package nl.springboot.safar.services;

import nl.springboot.safar.models.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {
	public List<Site> findAll();

	public Optional<Site> findById(Integer id);

	public Site create(Site site);

	public List<Site> findByCityId(Integer cityId);

//	public void deleteById(Integer id);
}
