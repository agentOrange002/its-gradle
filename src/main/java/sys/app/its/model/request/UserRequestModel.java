package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestModel {
	private String firstName;
	private String middleName;	
	private String lastName;	
	private String suffixName;
	private String email;
	private String password;
}
