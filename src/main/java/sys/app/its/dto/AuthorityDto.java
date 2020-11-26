package sys.app.its.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorityDto implements Serializable {
	private static final long serialVersionUID = -1499548465640249185L;
	private Long id;	
	private String name;
}
