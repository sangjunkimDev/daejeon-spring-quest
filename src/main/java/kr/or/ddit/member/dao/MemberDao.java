package kr.or.ddit.member.dao;


import javax.annotation.Resource;

import kr.or.ddit.member.model.MemberVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/*
 * @Controller : controller
 * @Service : service
 * @Repository : dao
 */
@Repository("memberDao")
public class MemberDao implements MemberDaoInf{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public MemberVO memberLogin(MemberVO memberVO) {
		logger.debug("{}", "memberLogin(memberVO)");
		return sqlSessionTemplate.selectOne("member.memberLogin", memberVO);
	}
	
}
