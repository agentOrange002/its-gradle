package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserDto;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.UserService;
import sys.app.its.utility.Utility;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {	
	
	private UserRepository userRepository;
	private Utility utility;
	
	@Override
	public List<UserDto> allUsers() {
		ModelMapper modelmapper = new ModelMapper();
		List<UserDto> listDto = new ArrayList<UserDto>();
		List<UserEntity> listEntity = userRepository.findAll();
		for(UserEntity entity: listEntity) {
			listDto.add(modelmapper.map(entity, UserDto.class));
		}
		return listDto;
	}
	
	@Override
	public UserDto getUserById(String userId) {	
		UserEntity entity = userRepository.findByUserId(userId);	
		return new ModelMapper().map(entity,UserDto.class);
	}

	@Override
	public UserDto postUser(UserDto dto) {
		UserEntity entity = new ModelMapper().map(dto, UserEntity.class);
		entity.setUserId(utility.generateUserId(10));
		entity.setFullName(utility.generateFullName(entity.getFirstName(), entity.getMiddleName(), entity.getLastName(), entity.getSuffixName()));
		entity.setEmailVerificationStatus(false);
		entity.setEncryptedPassword(dto.getPassword());
		UserEntity saveEntity = userRepository.save(entity);		
		return new ModelMapper().map(saveEntity, UserDto.class);
	}
}
