package sys.app.its.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import sys.app.its.dto.DashboardDto;
import sys.app.its.model.response.DashboardResponseModel;
import sys.app.its.service.DashboardService;

@Tag(name = "Dashboard", description = "Dashboard REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping({ "/api/dashboard" })
public class DashboardController {
	
	private DashboardService dashboardService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public DashboardResponseModel getDashboard() {
		DashboardDto dto = dashboardService.getDashboard();	
		return new ModelMapper().map(dto,DashboardResponseModel.class);
	}
}
