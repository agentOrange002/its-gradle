package sys.app.its.service;

import java.util.List;

import sys.app.its.dto.UserImageDto;

public interface UserImageService {
	List<UserImageDto> allUserImage();
	UserImageDto getUserImageByImageId(String imageId);
	UserImageDto postUserImage(String userId, UserImageDto dto);
	UserImageDto updateUserImage(UserImageDto transferDto, String userId);
}
