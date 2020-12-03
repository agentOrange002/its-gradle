package sys.app.its.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import sys.app.its.dto.IssueDto;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Log4j2
public class IssueAspect {
	
	@Before("execution(public * sys.app.its.service.implementation.IssueServiceImplementation.getIssueByIssueId(..)) && args(issueId,..)")
    public void checkGetIssueByIssueId(JoinPoint pjp,String issueId) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		log.info(" Check Get Issue By IssueId....... ");
    }
	
	@AfterReturning(pointcut="execution(public * sys.app.its.service.implementation.IssueServiceImplementation.saveIssue(..))",returning="retVal")
    public void saveIssueLogAfterSaving(JoinPoint pjp,Object retVal) throws Throwable {
       // log.info(" Check Get Issue By IssueId :" + issueId +" ---- "+pjp);
		log.info(" Log Save Issue....... "+((IssueDto)retVal).toString());
    }
}
