package nl.springboot.safar.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.springboot.safar.models.Site;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class UserFavoriteSitesDto {
	private List<Site> favoriteSites;
}
