package sys.app.its.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter	
public class RoleRequestModel {
	private String name;
	private List<String> authorities;
}
