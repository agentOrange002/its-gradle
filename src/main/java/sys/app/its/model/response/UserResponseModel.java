package sys.app.its.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortUserAddressResponseModel;
import sys.app.its.model.shortresponse.ShortUserImageResponseModel;

@Getter @Setter
public class UserResponseModel {
	private long id;	
	private String userId;	
	private String firstName;
	private String middleName;	
	private String lastName;	
	private String suffixName;
	private String fullName;
	private String email;
	private List<ShortUserAddressResponseModel> addresses;
	private ShortUserImageResponseModel userImage;
}
