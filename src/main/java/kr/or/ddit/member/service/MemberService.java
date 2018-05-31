package kr.or.ddit.member.service;

import javax.annotation.Resource;

import kr.or.ddit.member.dao.MemberDaoInf;
import kr.or.ddit.member.model.MemberVO;

import org.springframework.stereotype.Service;

/**
 * MemberService.java
 *
 * @author "K.S.J"
 * @since 2018. 5. 30.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일 수정자 수정내용
 * ---------- ------ ------------------------
 * 2018. 5. 14. "K.S.J" 최초 생성
 *
 * </pre>
 */

@Service("memberService")
public class MemberService implements MemberServiceInf {
	
	@Resource(name="memberDao")
	private MemberDaoInf memberDao = null;
	
	/**
	 * Method : memberLogin
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param memberVO (mem_id, mem_pass를 가진 인스턴스)
	 * @return 해당 회원 반환(id와 별명을 가짐)
	 * Method 설명 : login 기능
	 */
	@Override
	public MemberVO memberLogin(MemberVO memberVO) {
		return memberDao.memberLogin(memberVO);
	}

	/**
	 * Method : loginYN
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param mem_id 회원이 입력한 아이디
	 * @param memberVO memberLogin메서드의 결과 인스턴스
	 * @return 아이디가 일치한 경우 true
	 * Method 설명 : 회원이 입력한 아이디와 결과인스턴스의 아이디가 같은 경우 true반환
	 */
	@Override
	public boolean loginYN(String mem_id, MemberVO memberVO) {
		if (memberVO == null) {
			return false;
		} else if (mem_id.equals(memberVO.getMem_id())) {
			return true;
		}
		return false;
	}

}
