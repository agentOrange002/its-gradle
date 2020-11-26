package sys.app.its.model.response;

import java.util.Collection;



import lombok.Getter;
import lombok.Setter;
import sys.app.its.entity.AuthorityEntity;

@Getter @Setter
public class RoleResponseModel {
	private Long id;
	private String name;
	private Collection<AuthorityEntity> authorities;
}
