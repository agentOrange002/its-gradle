package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserImageDto;
import sys.app.its.model.request.UserImageRequestModel;
import sys.app.its.model.response.UserImageResponseModel;
import sys.app.its.service.UserImageService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users/images")
public class UserImageController {
	
	private UserImageService userImageService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserImageResponseModel> allUserImage(){
		ModelMapper mapper = new ModelMapper();
		List<UserImageResponseModel> listResponse = new ArrayList<UserImageResponseModel>();
		List<UserImageDto> listDto = userImageService.allUserImage();
		for(UserImageDto dto : listDto) {
			listResponse.add(mapper.map(dto, UserImageResponseModel.class));
		}
		return listResponse;
	}
	
	@GetMapping(path="/{imageId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel getUserImageByImageId(@PathVariable String imageId){
		UserImageDto dto = userImageService.getUserImageByImageId(imageId);		
		return new ModelMapper().map(dto, UserImageResponseModel.class);
	}
	
	@PostMapping(path="/save/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel postUserImage(@PathVariable String userId,@RequestBody UserImageRequestModel requestModel) {
		String[] file = requestModel.getImage();
		String newFile = file[0];
		//String base64Image = newFile.split(",")[1];
		String base64Image = newFile.split(",")[0]; 
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		UserImageDto transferDto = new UserImageDto();
		transferDto.setImage(imageBytes);
		UserImageDto dto = userImageService.postUserImage(userId,transferDto);
		return new ModelMapper().map(dto, UserImageResponseModel.class);
	}
	
}
