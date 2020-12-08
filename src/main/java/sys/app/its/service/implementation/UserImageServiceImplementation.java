package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserDto;
import sys.app.its.dto.UserImageDto;
import sys.app.its.entity.UserEntity;
import sys.app.its.entity.UserImageEntity;
import sys.app.its.repository.UserImageRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.UserImageService;
import sys.app.its.utility.Utility;

@Service
@AllArgsConstructor
public class UserImageServiceImplementation implements UserImageService { 

	private UserImageRepository userImageRepository;
	private UserRepository userRepository;
	private Utility utility;
	
	@Override
	public List<UserImageDto> allUserImage() {
		List<UserImageEntity> listEntity = userImageRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		List<UserImageDto> listDto = new ArrayList<UserImageDto>();
		for(UserImageEntity entity: listEntity) {
			listDto.add(mapper.map(entity, UserImageDto.class));
		}
		return listDto;
	}

	@Override
	public UserImageDto getUserImageByImageId(String imageId) {
		UserImageEntity entity = userImageRepository.findByImageId(imageId);
		UserImageDto dto = new ModelMapper().map(entity, UserImageDto.class);
		dto.setUserImageDetails(new ModelMapper().map(entity.getUserImageDetails(),UserDto.class));
		return dto;
	}

	@Override
	public UserImageDto postUserImage(String userId, UserImageDto dto) {
		UserImageEntity userimage = new ModelMapper().map(dto, UserImageEntity.class);
		UserEntity user = userRepository.findByUserId(userId);
		userimage.setImageId(utility.generateImageId(10));
		userimage.setUserImageDetails(user);
		UserImageEntity saveEntity = userImageRepository.save(userimage);
		return new ModelMapper().map(saveEntity, UserImageDto.class);
	}

	@Override
	public UserImageDto updateUserImage(UserImageDto transferDto, String userId) {
		byte[] imageBytes = transferDto.getImage();	
		UserEntity userEntity = userRepository.findByUserId(userId);		
		UserImageEntity userImageEntity = userImageRepository.findByUserImageDetails(userEntity);
		userImageEntity.setImage(imageBytes);
		UserImageEntity updateduserImageEntity = userImageRepository.save(userImageEntity);	
		return new ModelMapper().map(updateduserImageEntity,UserImageDto.class);
	}

}
