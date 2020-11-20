package sys.app.its.model.response;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class UserAddressResponseModel {
	private Long id;	
	private String addressId;		
	private String city;	
	private String country;	
	private String streetName;	
	private String postalCode;	
	private String type;	
	private ShortUserResponseModel userDetails;
}
