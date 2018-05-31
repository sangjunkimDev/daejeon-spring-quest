package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.spring.test.TestInit;

import org.junit.Test;

public class MemberDaoTest extends TestInit{
	
	@Resource(name="memberDao")
	private MemberDaoInf memberDao;
	
	@Test
	public void memberDaoTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(memberDao);
	}
	
	@Test
	public void memberLoginTest() {
		/***Given***/
		String mem_id = "test_id_1";
		String mem_pass = "test_pw_1";
		MemberVO memberVO = new MemberVO(mem_id, mem_pass);

		/***When***/
		MemberVO resultMemberVO = memberDao.memberLogin(memberVO);
		
		/***Then***/
		assertEquals(mem_id, resultMemberVO.getMem_id());
		assertEquals("test_alias_1", resultMemberVO.getMem_alias());
	}
	
}
