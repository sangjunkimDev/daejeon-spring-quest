package kr.or.ddit.tboard.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.spring.test.TestInit;
import kr.or.ddit.tboard.controller.TboardController;
import kr.or.ddit.tboard.model.TboardVO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(locations={"classpath:kr/or/ddit/spring/servlet-context.xml"})
@WebAppConfiguration
public class TboardControllerTest extends TestInit {
	
	@Resource
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Autowired
	private MockHttpSession session;
	
	
	@Resource(name="tboardController")
	private TboardController controller;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void beanInjectionTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(controller);
	}
	
	
	@Test
	public void getTboardList() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main/tboardlistpage").session(session))
										.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***When***/
		Map<String, Object> map = mav.getModel();
		List<TboardVO> tboardList = (List<TboardVO>) map.get("tboardList");
		
		/***Then***/
		assertNotNull(tboardList);
		assertEquals(19, tboardList.size());
		
		assertNotNull(session);
		assertNotNull(session.getAttribute("pageChoose"));
		assertEquals("tboardList", session.getAttribute("pageChoose"));
	}
	
	
	@Test
	public void tboardInsertPageTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main/tboardinsertpage")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***When***/

		/***Then***/
		assertEquals("tboardinsertpage", mav.getViewName());
	}
	
	@Test
	public void tboardInsertTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main/tboardInsert")
										.param("tboard_title", "test_tboard_title")
										.param("tboard_mem_id", "test_id_1")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***When***/

		/***Then***/
		assertEquals("redirect:/main/tboardlistpage", mav.getViewName());
	}
	
	@Test
	public void tboardUpdateSuccessTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main/tboardUpdate")
										.param("tboard_seq", "1")
										.param("tboard_del_yn", "N")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***When***/
		
		/***Then***/
		assertEquals("redirect:/main/tboardlistpage", mav.getViewName());
	}
	
	@Test
	public void tboardUpdateFailTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main/tboardUpdate")
				.param("tboard_seq", "0")
				.param("tboard_del_yn", "N")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***When***/
		
		/***Then***/
		assertEquals("redirect:/home/loginpage", mav.getViewName());
	}

}
