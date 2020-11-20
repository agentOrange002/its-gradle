package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAddressRequestModel {
	private String city;	
	private String country;	
	private String streetName;	
	private String postalCode;	
	private String type;	
}
