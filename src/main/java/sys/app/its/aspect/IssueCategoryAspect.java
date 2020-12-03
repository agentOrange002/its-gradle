package sys.app.its.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import sys.app.its.dto.IssueCategoryDto;
import sys.app.its.entity.IssueCategoryEntity;
import sys.app.its.repository.IssueCategoryRepository;

@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Aspect
@Log4j2
public class IssueCategoryAspect {
	@Autowired
	IssueCategoryRepository repository;
	
	@Before("execution(public * sys.app.its.service.implementation.IssueCategoryServiceImplementation.saveCategory(..)) && args(dto,..)")
    public void beforeSavingCategory(JoinPoint pjp,IssueCategoryDto dto) throws Throwable {		
       // log.info(" Check Save Category :" + dto +" ---- "+pjp);
		//String name = dto.getName();
        log.info(" Check Save Category ...");
    }
	
	@AfterReturning(pointcut="execution(public * sys.app.its.service.implementation.IssueCategoryServiceImplementation.saveCategory(..))",returning="retVal")
    public void afterSavingCategory(JoinPoint pjp,Object retVal) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		IssueCategoryDto dto = (IssueCategoryDto) retVal;
		IssueCategoryEntity entity = repository.findByName(dto.getName());
		/* log.info(" Log Save Category....... "+((CategoryDto)retVal).toString()); */
		log.info(" Log Save Category....... "+entity.getName().toString());
    }
}
