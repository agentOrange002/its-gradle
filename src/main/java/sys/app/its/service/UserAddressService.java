package sys.app.its.service;

import java.util.List;
import sys.app.its.dto.UserAddressDto;

public interface UserAddressService {
	List<UserAddressDto> allAddress();
	UserAddressDto getAddressById(String addressId);
	UserAddressDto saveAddress(String userId, UserAddressDto dto);	
	UserAddressDto updateAddress(UserAddressDto addressDto , String addressid);
}
