package nl.springboot.safar.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Site") @Table(name = "site")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(
			name = "id",
			updatable = false
	)
	private int id;

	@Column(
			name = "name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String name;

	@Column(
			name = "description",
			columnDefinition = "TEXT"
	)
	private String description;

	@Column(
			name = "address",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String address;

	@Column(
			name = "image_path",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String imgPath;

	@Column(
			name = "is_deleted",
			nullable = false
	)
	private boolean isDeleted;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"sites", "hibernateLazyInitializer", "handler"})
	private City city;

	public Site(int id, String name, String description, String address, String imgPath, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.imgPath = imgPath;
		this.isDeleted = isDeleted;
	}

	public Site(String name, String description, String address, String imgPath, boolean isDeleted, City city) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.imgPath = imgPath;
		this.isDeleted = isDeleted;
		this.city = city;
	}
}
