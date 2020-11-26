package sys.app.its.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.entity.AuthorityEntity;
import sys.app.its.entity.UserEntity;

@Getter @Setter
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 6207967611797110788L;
	private Long id;	
	private String name;	
	private Collection<UserEntity> users;	
	private Collection<AuthorityEntity> authorities;
}
