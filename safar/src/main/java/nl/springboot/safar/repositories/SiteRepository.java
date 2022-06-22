package nl.springboot.safar.repositories;

import nl.springboot.safar.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
	List<Site> findByCityId(Integer cityId);

	List<Site> findByIsDeleted(boolean isDeleted);
}
