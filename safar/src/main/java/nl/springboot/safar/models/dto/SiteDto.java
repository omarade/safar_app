package nl.springboot.safar.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class SiteDto {
	private String name;
	private String description;
	private String address;
	private String imgPath;
	private Integer cityId;
}
