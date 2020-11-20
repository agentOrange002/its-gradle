package sys.app.its.model.shortresponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShortUserImageResponseModel {
	private Long id;
	private String imageId;
	private byte[] image;	
}
