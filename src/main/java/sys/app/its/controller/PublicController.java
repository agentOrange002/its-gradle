package sys.app.its.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.IssueDto;
import sys.app.its.enums.RequestOperationName;
import sys.app.its.enums.RequestOperationStatus;
import sys.app.its.model.request.PasswordResetModel;
import sys.app.its.model.request.PublicIssueRequestModel;
import sys.app.its.model.request.RequestPasswordResetRequestModel;
import sys.app.its.model.response.OperationStatusModel;
import sys.app.its.service.IssueService;
import sys.app.its.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/public"})
public class PublicController {
	private UserService userService;
	private IssueService issueService;

	@GetMapping(path = "/email-verification", produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationStatusModel publicverifyEmailToken(@RequestParam(value = "token") String token) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
		boolean isVerified = userService.verifyEmailToken(token);
		if (isVerified) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		}
		return returnValue;
	}

	@PostMapping(path = "/password-request-reset", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicrequestReset(@RequestBody RequestPasswordResetRequestModel passwordResetRequestModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@PostMapping(path = "/password-reset", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel publicresetPassword(@RequestBody PasswordResetModel passwordResetModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean operationResult = userService.resetPassword(passwordResetModel.getToken(),
				passwordResetModel.getPassword());

		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@PostMapping(path = "/post-issue", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public OperationStatusModel publicpostIssue(@RequestBody PublicIssueRequestModel issueDetails) {
		OperationStatusModel returnValue = new OperationStatusModel();
		IssueDto issuedto = new ModelMapper().map(issueDetails, IssueDto.class);
		boolean operationResult = issueService.publicSaveIssue(issuedto);
		returnValue.setOperationName(RequestOperationName.REPORT_ISSUE.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}
}