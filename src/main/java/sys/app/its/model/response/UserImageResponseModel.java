package sys.app.its.model.response;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class UserImageResponseModel {
	private Long id;
	private String imageId;
	private byte[] image;
	private ShortUserResponseModel userImageDetails;
}
