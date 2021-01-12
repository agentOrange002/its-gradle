package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import sys.app.its.dto.UserImageDto;
import sys.app.its.entity.UserImageEntity;
import sys.app.its.model.request.UserImageRequestModel;
import sys.app.its.model.response.UserImageResponseModel;
import sys.app.its.service.UserImageService;

@Tag(name = "UserImages", description = "UserImages REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping("/api/userimages")
public class UserImageController {
	
	private UserImageService userImageService;
	
	@Operation(summary = "All UserImages", description = "Get List of All UserImage", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
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
	
	@Operation(summary = "Get UserImage by UserId", description = "Get UserImage of Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@GetMapping(path="/{imageId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel getUserImageByImageId(@PathVariable String imageId){
		UserImageDto dto = userImageService.getUserImageByImageId(imageId);		
		return new ModelMapper().map(dto, UserImageResponseModel.class);
	}
	
	@Operation(summary = "Post UserImage by UserId", description = "Save UserImage on a Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@PostMapping(path="/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel postUserImage(@PathVariable String userId,@RequestBody UserImageRequestModel requestModel) {
		String[] file = requestModel.getImage();
		String newFile = file[0];
		String base64Image = newFile.split(",")[1];
		//String base64Image = newFile.split(",")[0]; 
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		UserImageDto transferDto = new UserImageDto();
		transferDto.setImage(imageBytes);
		UserImageDto dto = userImageService.postUserImage(userId,transferDto);
		return new ModelMapper().map(dto, UserImageResponseModel.class);
	}
	
	@Operation(summary = "Put UserImage by UserId", description = "Update UserImage of Specific User", tags = "UserImages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserImageEntity.class)))) })
	@PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImageResponseModel updateUserImage(@PathVariable String userId,
			@RequestBody UserImageRequestModel requestbody) {
		String[] file = requestbody.getImage();
		String newFile = file[0];
		String base64Image = newFile.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		UserImageDto transferDto = new UserImageDto();
		transferDto.setImage(imageBytes);
		UserImageDto dto = userImageService.updateUserImage(transferDto, userId);
		UserImageResponseModel response = new ModelMapper().map(dto, UserImageResponseModel.class);
		return response;
	}
	
}
