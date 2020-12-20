package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.IssueLogDto;
import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.IssueLogEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.IssueLogRepository;
import sys.app.its.repository.IssueRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.IssueLogService;
import sys.app.its.utility.Utility;

@AllArgsConstructor
@Service
public class IssueLogServiceImplementation implements IssueLogService {
	
	private IssueLogRepository issueLogRepository;
	private IssueRepository issueRepository;
	private UserRepository userRepository;
	private Utility utils;
	
	@Override
	public IssueLogDto saveIssueLog(IssueLogDto issueLog, String issueId, String userId) {
		IssueLogDto returnValue = new IssueLogDto();

		/* ERROR */
		ModelMapper amodelMapper = new ModelMapper();
		amodelMapper.getConfiguration().setAmbiguityIgnored(true);
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		UserEntity userEntity = userRepository.findByUserId(userId);
		/* ERROR */

		IssueLogEntity entity = amodelMapper.map(issueLog, IssueLogEntity.class);
		entity.setIssueDetails(issueEntity);
		entity.setIssueLogUserDetails(userEntity);
		entity.setIssueLogId(utils.generateIssueLogId(10));
		entity.setLogDate(new Date());
		IssueLogEntity saveEntity = issueLogRepository.save(entity);

		//IssueEntity ie = saveEntity.getIssueDetails();
		//UserEntity ue = saveEntity.getIssueLogUserDetails();

		//IssueDto issueDto = new ModelMapper().map(ie, IssueDto.class);
		//UserDto userDto = new ModelMapper().map(ue, UserDto.class);

		returnValue = new ModelMapper().map(saveEntity, IssueLogDto.class);
		//returnValue.setIssueDto(issueDto);
		//returnValue.setUserDto(userDto);
		return returnValue;
	}

	@Override
	public List<IssueLogDto> getIssueLogById(String issueId) {
		IssueEntity ie = issueRepository.findIssueByIssueId(issueId);
		List<IssueLogDto> returnValue = new ArrayList<IssueLogDto>();
		List<IssueLogEntity> listentity = issueLogRepository.findAllByIssueDetails(ie);
		ModelMapper mapper = new ModelMapper();
		for (IssueLogEntity entity : listentity) {

			IssueLogDto dto = mapper.map(entity, IssueLogDto.class);

			//ModelMapper mm = new ModelMapper();
			//UserEntity uent = entity.getIssueLogUserDetails();
			//IssueEntity ient = entity.getIssueDetails();
		//	UserDto udto = mm.map(uent, UserDto.class);
			//IssueDto idto = mm.map(ient, IssueDto.class);

			//dto.setUserDto(udto);
			//dto.setIssueDto(idto);
			returnValue.add(dto);
		}
		return returnValue;
	}

}
