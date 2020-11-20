package sys.app.its.model.shortresponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShortUserAddressResponseModel {
	private Long id;	
	private String addressId;		
	private String city;	
	private String country;	
	private String streetName;	
	private String postalCode;	
	private String type;	
}
