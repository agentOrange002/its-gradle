package sys.app.its.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueCategoryDto implements Serializable  {	
	private static final long serialVersionUID = 1519117831359892221L;
	private Long id;	
	private String categoryId;	
	private String name;
}
