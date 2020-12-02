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
import sys.app.its.dto.TaskDto;
import sys.app.its.model.request.TaskRequestModel;
import sys.app.its.model.response.TaskResponseModel;
import sys.app.its.service.TaskService;

@AllArgsConstructor
@RestController
@RequestMapping({ "/api/tasks" })
public class TaskController {
	private TaskService taskService;
	
	@PostMapping(path="/", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public TaskResponseModel createTask(@RequestBody TaskRequestModel requestModel) {		
		TaskDto transferDto = new ModelMapper().map(requestModel, TaskDto.class);
		TaskDto dto = taskService.createTask(transferDto, requestModel.getTicketid(), requestModel.getUserid());		
		return new ModelMapper().map(dto, TaskResponseModel.class);
	}
	
	@GetMapping(path="/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskResponseModel getTaskByTaskId(@PathVariable String taskId) {
		TaskDto dto = taskService.getTaskByTaskId(taskId);		
		return new ModelMapper().map(dto, TaskResponseModel.class);
	}
	
	@GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskResponseModel> getAllTask() {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTask();
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
	
	@GetMapping(path="/all/support/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskResponseModel> getAllTask(@PathVariable String userId) {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTaskBySupportId(userId);
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
	
	@GetMapping(path="/all/ticket/{ticketId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskResponseModel> getAllTaskByTicketId(@PathVariable String ticketId) {
		List<TaskResponseModel> result  = new ArrayList<TaskResponseModel>();
		List<TaskDto> listDto = taskService.getAllTaskByTicketId(ticketId);
		ModelMapper mapper = new ModelMapper();
		for(TaskDto dto: listDto) {
			TaskResponseModel responseModel = mapper.map(dto, TaskResponseModel.class);
			result.add(responseModel);
		}
		return result;
	}
}
