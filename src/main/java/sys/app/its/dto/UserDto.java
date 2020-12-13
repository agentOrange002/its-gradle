package sys.app.its.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class UserDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -224973724663396070L;

	private long id;	
	private String userId;	
	private String firstName;
	private String middleName;	
	private String lastName;	
	private String suffixName;
	private String fullName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;	
	private Boolean emailVerificationStatus;
	private List<UserAddressDto> addresses;
	private UserImageDto userImage;
}
