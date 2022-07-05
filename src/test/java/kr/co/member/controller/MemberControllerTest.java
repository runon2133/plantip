package kr.co.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class) //Spring 테스트 객체 어노테이션
@WebAppConfiguration //Spring 설정파일을 읽어오기 위한 어노테이션
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MemberControllerTest {
	//로그를 작성할 객체
	private static final Logger Logger = LoggerFactory.getLogger(MemberControllerTest.class);
	
	@Autowired
	private WebApplicationContext wac; //설정파일을 사용하기 위한 객체(@WebAppConfiguration으로 생성된 객체 주입)
	private MockMvc mockMvc; //테스트 시 웹을 요청하고 결과를 받아서 검사하는 객체
	
	@Before //테스트 사전작업을 위한 메소드
	public void setup() {
		//설정파일정보를 가지고 있는 객체를 매개변수로 전달해서 mockMvc 객체 생성
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		Logger.info("테스트 준비 완료");
	}
	
	@Test
	public void loginTest() {
		Logger.info("로그인 테스트");
		try {
			mockMvc.perform(post("/login.do")
						.param("memberId", "admin")
						.param("memberPw", "!389391tt"))
					.andDo(print())
					.andExpect(status().is3xxRedirection()); //최종 결과처리가 컨트롤러를 한번 더 호출하는 redirect 경우 is3xxRedirection
			Logger.info("login success");
		} catch (Exception e) {
			Logger.error("login fail");
			Logger.error(e.getMessage());
		}
	}
	
	/*
	@Test
	public void insertTest() {
		Logger.info("회원가입 테스트");
		try {
			mockMvc.perform(post("/join.do")
						.param("memberName", "유저일")
						.param("memberId", "user1")
						.param("memberPw", "user1234!")
						.param("memberEmail", "user1@email.com"))
					.andDo(print())
					.andExpect(status().isOk()); //최종 결과처리가 jsp페이지로 이동하는 경우 status().isOk()
			Logger.info("insert success");
		} catch (Exception e) {
			Logger.error("insert fail");
			Logger.error(e.getMessage());
		}
	}
	*/
	
}
