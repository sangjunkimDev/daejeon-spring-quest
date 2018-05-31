package kr.or.ddit.tboard.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.spring.test.TestInit;
import kr.or.ddit.tboard.model.TboardVO;
import kr.or.ddit.tboard.service.TboardServiceInf;

import org.junit.Test;

public class TboardServiceTest extends TestInit{
	
	@Resource(name="tboardService")
	private TboardServiceInf service;
	

	
	@Test
	public void beanInjectionTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(service);
	}
	
	
	@Test
	public void getTboardListTest() {
		/***Given***/
		
		/***When***/
		List<TboardVO> list = service.getTboardList();
		
		/***Then***/
		assertNotNull(list);
		assertEquals(19, list.size());
	}
	
	
	@Test
	public void getTboardTest() {
		/***Given***/
		TboardVO tboardVO = new TboardVO();
		String tboard_seq = "1";
		tboardVO.setTboard_seq(tboard_seq);
		
		/***When***/
		TboardVO resultTboardVO = service.getTboard(tboardVO);
		
		/***Then***/
		assertNotNull(resultTboardVO);
		assertEquals(tboard_seq, resultTboardVO.getTboard_seq());
		assertEquals("test_tboard_title_1", resultTboardVO.getTboard_title());
	}
	
	@Test
	public void insertTboardTest() { 
		/***Given***/
		TboardVO tboardVO = new TboardVO();
		String tboard_title = "test_title";
		String tboard_mem_id = "test_id_1";
		tboardVO.setTboard_title(tboard_title);
		tboardVO.setTboard_mem_id(tboard_mem_id);
		
		/***When***/
		int resultCnt = service.insertTboard(tboardVO);
		int resultSize = service.getTboardList().size();
		
		/***Then***/
		assertEquals(1, resultCnt);
		assertEquals(20, resultSize);
	}
	
	
	@Test
	public void updateTboardYNTest() {
		/***Given***/
		TboardVO tboardVO = new TboardVO();
		String tboard_seq = "1";
		tboardVO.setTboard_seq(tboard_seq);
		
		TboardVO basicTboardVO = service.getTboard(tboardVO);
		
		String tboard_del_yn = "";
		if (basicTboardVO.getTboard_del_yn().equals("Y")) {
			tboard_del_yn = "N";
		} else {
			tboard_del_yn = "Y";
		}
		
		tboardVO.setTboard_del_yn(tboard_del_yn);
		
		/***When***/
		int resultCnt = service.updateTboardYN(tboardVO);
		TboardVO resultTboardVO = service.getTboard(tboardVO);
		
		/***Then***/
		assertEquals(1, resultCnt);
		assertEquals("1", resultTboardVO.getTboard_seq());
		assertEquals(tboard_del_yn, resultTboardVO.getTboard_del_yn());
	}
	
}
