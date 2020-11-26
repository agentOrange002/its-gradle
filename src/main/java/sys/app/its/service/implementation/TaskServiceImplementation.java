package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.TaskDto;
import sys.app.its.entity.TaskEntity;
import sys.app.its.entity.TicketEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.exception.ApplicationServiceException;
import sys.app.its.exception.ErrorMessages;
import sys.app.its.repository.TaskRepository;
import sys.app.its.repository.TicketRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.TaskService;
import sys.app.its.utility.Utility;

@AllArgsConstructor
@Service
public class TaskServiceImplementation implements TaskService {
	
	private Utility utils;
	private TaskRepository taskRepository;
	private TicketRepository ticketRepository;
	private UserRepository userRepository;

	@Override
	public TaskDto createTask(TaskDto dto, String ticketId, String userId) {		
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);	
		if (ticketEntity == null)throw new ApplicationServiceException(ErrorMessages.TASKID_NOT_FOUND.getErrorMessage());
		UserEntity userEntity = userRepository.findByUserId(userId);		
		TaskEntity entity = new TaskEntity();
		entity.setSubject(dto.getSubject());
		entity.setDescription(dto.getDescription());
		entity.setTaskId(utils.generateTaskId(10));
		entity.setDateOpened(new Date());
		entity.setTicketDetails(ticketEntity);
		entity.setUsertaskDetails(userEntity);
		TaskEntity saveEntity = taskRepository.save(entity);
		return new ModelMapper().map(saveEntity, TaskDto.class);		
	}

	@Override
	public TaskDto getTaskByTaskId(String taskId) {
		TaskEntity entity = taskRepository.findByTaskId(taskId);	
		if (entity == null)throw new ApplicationServiceException(ErrorMessages.TASKID_NOT_FOUND.getErrorMessage());
		return new ModelMapper().map(entity, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTask() {
		List<TaskEntity> allTasks = taskRepository.findAll();
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}
	
	@Override
	public List<TaskDto> getAllTaskBySupportId(String userId) {
		List<TaskEntity> allTasks = taskRepository.findTasksUsingSupportId(userId);
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}

	@Override
	public List<TaskDto> getAllTaskByTicketId(String ticketId) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);
		List<TaskEntity> allTasks = taskRepository.findTasksByTicketDetails(ticketEntity);
		List<TaskDto> returnList = new ArrayList<TaskDto>();
		ModelMapper mapper = new ModelMapper();
		for(TaskEntity entity: allTasks) {
			TaskDto dto = mapper.map(entity, TaskDto.class);
			returnList.add(dto);
		}		
		return returnList;
	}

}
