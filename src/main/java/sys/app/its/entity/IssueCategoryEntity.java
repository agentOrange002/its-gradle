package sys.app.its.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class IssueCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4280136293467589879L;

	
	@Id
	@SequenceGenerator(name="category_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	private Long id;
	
	@Column(name="category_id",unique=true)
	private String categoryId;
	
	@Column(name="name",unique=true)
	private String name;	
	
	@OneToMany(mappedBy="categoryDetails",cascade = CascadeType.ALL)
	private List<IssueEntity> issues;
}