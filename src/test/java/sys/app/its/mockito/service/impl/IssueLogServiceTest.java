package sys.app.its.mockito.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import sys.app.its.dto.IssueLogDto;
import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.IssueLogEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.IssueLogRepository;
import sys.app.its.repository.IssueRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.implementation.IssueLogServiceImplementation;
import sys.app.its.utility.Utility;

class IssueLogServiceTest {
	
	@InjectMocks
	IssueLogServiceImplementation issuelogService;
	
	@Mock
	IssueLogRepository issueLogRepository;
	
	@Mock
	IssueRepository issueRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	Utility utils;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	final void testSaveIssueLog() {
		///IID3CMU4gdxEN/UIDH3CglYMIVT
		IssueEntity issueEntity = issueRepository.findIssueByIssueId("IID3CMU4gdxEN");
		UserEntity userEntity = userRepository.findByUserId("UIDH3CglYMIVT");		
		
		IssueLogDto dto = new IssueLogDto();
		dto.setIssueMessage("Test Message");			
		
		IssueLogEntity issuelogentity = new IssueLogEntity();	
		issuelogentity.setIssueDetails(issueEntity);
		issuelogentity.setIssueLogUserDetails(userEntity);
		issuelogentity.setIssueMessage(dto.getIssueMessage());		
		issuelogentity.setIssueLogId(utils.generateIssueLogId(10));
		issuelogentity.setLogDate(new Date());
		
		when(issueLogRepository.save(any(IssueLogEntity.class))).thenReturn(issuelogentity);	
		IssueLogDto saveissuelogDto = issuelogService.saveIssueLog(dto, "IID3CMU4gdxEN" , "UIDH3CglYMIVT"); 
		
		assertNotNull(saveissuelogDto);
		assertEquals(saveissuelogDto.getIssueMessage(),issuelogentity.getIssueMessage());
		
		
		IssueLogDto updatedDto = new ModelMapper().map(issuelogentity, IssueLogDto.class);
		assertEquals(saveissuelogDto.toString(),updatedDto.toString());
		
	}
	
	@Test
	final void testGetIssueLogById() {
		IssueEntity issueEntity = issueRepository.findIssueByIssueId("IID3CMU4gdxEN");
		List<IssueLogEntity> entityList = issueLogRepository.findAllByIssueDetails(issueEntity);
		when(issueLogRepository.findAllByIssueDetails(any(IssueEntity.class))).thenReturn(entityList);

		List<IssueLogDto> returnListDto = issuelogService.getIssueLogById("IID3CMU4gdxEN");
		assertNotNull(returnListDto);
		
		List<IssueLogDto> newListDto = new ArrayList<IssueLogDto>();
		ModelMapper mapper = new ModelMapper();
		for(IssueLogEntity entity: entityList) {
			newListDto.add(mapper.map(entity, IssueLogDto.class));
		}
		assertEquals(returnListDto.toString(),newListDto.toString());
		
		assertEquals(returnListDto,newListDto);
	}
}
