package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import kr.or.ddit.spring.test.TestInit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(locations={"classpath:kr/or/ddit/spring/servlet-context.xml"})
@WebAppConfiguration
public class MemberControllerTest extends TestInit{
	
	@Resource
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Resource(name="memberController")
	private MemberController memberController;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void creationTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(memberController);
	}
	
	@Test
	public void loginTest() throws Exception {
		/***Given***/
		MvcResult result = mockMvc.perform(get("/home/login")
							.param("mem_id", "test_id_1")
							.param("mem_pass", "test_pw_1")).andReturn();

		/***When***/
		ModelAndView mav = result.getModelAndView();
		
		/***Then***/
		assertEquals("tboardList", mav.getViewName());
	}
	
	
}
