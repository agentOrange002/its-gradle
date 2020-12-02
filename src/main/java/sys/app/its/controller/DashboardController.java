package sys.app.its.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.DashboardDto;
import sys.app.its.model.response.DashboardResponseModel;
import sys.app.its.service.DashboardService;

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
