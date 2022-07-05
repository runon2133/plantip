package kr.co.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.member.model.vo.Member;

@Component
@Aspect
public class PasswordEncAdvice {
	
	@Autowired
	private SHA256Enc enc;
	
	@Pointcut(value="execution(* kr.co.member.model.service..*Service.*Enc(..))")
	public void encPointcut() {}

	@Before(value="encPointcut()")
	public void encPassword(JoinPoint jp) throws Exception {
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		String inputPass = m.getMemberPw(); //암호화 전 비밀번호
		String encPass = enc.encData(inputPass); //암호화 비밀번호
		m.setMemberPw(encPass);
	}
	
}
