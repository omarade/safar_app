package nl.springboot.safar.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "City") @Table(name = "city")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class City {

//	public City(Integer id, String name, String info, String imgPath, boolean isDeleted) {
//		this.id = id;
//		this.name = name;
//		this.info = info;
//		this.imgPath = imgPath;
//		this.isDeleted = isDeleted;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(
			name = "id",
			updatable = false
	)
	private Integer id;

	@Column(
			name = "name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String name;

	@Column(
			name = "info",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String info;

	@Column(
			name = "image_path",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String imgPath;

	@Column(
			name = "is_deleted",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private boolean isDeleted;

//	@OneToMany(mappedBy = "city",
//			targetEntity = Site.class,
//			cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//					CascadeType.MERGE, CascadeType.REFRESH},
//			fetch = FetchType.LAZY
//	)
//	@JsonIgnoreProperties({"city", "hibernateLazyInitializer", "handler"})
//	private List<Site> sites;
}
