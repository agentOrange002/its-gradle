package sys.app.its.service;

import java.util.List;

import sys.app.its.dto.TaskDto;


public interface TaskService {
	TaskDto createTask(TaskDto dto,String ticketId, String userId);
	TaskDto getTaskByTaskId(String taskId);
	List<TaskDto> getAllTask();
	List<TaskDto> getAllTaskBySupportId(String userId);
	List<TaskDto> getAllTaskByTicketId(String ticketId);
}
