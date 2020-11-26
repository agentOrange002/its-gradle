package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResetPasswordRequestModel {
	public String newPassword;
	public String confirmedPassword;
}
