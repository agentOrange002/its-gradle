package sys.app.its.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserImageDto implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -4805167053599820652L;
		private Long id;
		private String imageId;
		private byte[] image;
		private UserDto userImageDetails;
}
