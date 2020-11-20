package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserAddressDto;
import sys.app.its.dto.UserDto;
import sys.app.its.entity.UserAddressEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.UserAddressRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.UserAddressService;
import sys.app.its.utility.Utility;

@AllArgsConstructor
@Service
public class UserAddressServiceImplementation implements UserAddressService {
	
	private UserAddressRepository addressRepository;
	private UserRepository userRepository;
	private Utility utility;
	
	@Override
	public List<UserAddressDto> allAddress() {
		ModelMapper mapper = new ModelMapper();
		List<UserAddressDto> listDto = new ArrayList<UserAddressDto>();
		List<UserAddressEntity> listEntity = addressRepository.findAll();
		for(UserAddressEntity entity: listEntity) {
			listDto.add(mapper.map(entity, UserAddressDto.class));
		}
		return listDto;
	}
	
	@Override
	public UserAddressDto getAddressById(String addressId) {
		UserAddressEntity entity = addressRepository.findByAddressId(addressId);
		return new ModelMapper().map(entity, UserAddressDto.class);
	}

	@Override
	public UserAddressDto saveAddress(String userId, UserAddressDto dto) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		UserAddressEntity addressEntity = new ModelMapper().map(dto, UserAddressEntity.class);
		addressEntity.setAddressId(utility.generateAddressId(10));
		addressEntity.setUserDetails(userEntity);
		UserAddressEntity saveAddressEntity = addressRepository.save(addressEntity);
		UserEntity saveUserEntity = saveAddressEntity.getUserDetails();
		UserDto userDto = new ModelMapper().map(saveUserEntity, UserDto.class);
		UserAddressDto returnDto = new ModelMapper().map(saveAddressEntity, UserAddressDto.class);
		returnDto.setUserDetails(userDto);
		return returnDto;
	}
}
