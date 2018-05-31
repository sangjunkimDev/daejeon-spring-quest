package kr.or.ddit.tboard.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.tboard.model.TboardVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("tboardDao")
public class TboardDao implements TboardDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * Method : getTboardList
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 목록 가져옴
	 */
	@Override
	public List<TboardVO> getTboardList() {
		logger.debug("{} : {}", "TboardDao", "getTboardList()");
		return sqlSessionTemplate.selectList("tboard.getTboardList");
	}
	
	/**
	 * Method : getTboard
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param tboardVO 최소한 seq를 가진 인스턴스
	 * @return 해당 게시판의 모든 정보 가져옴
	 * Method 설명 : 해당 seq를 가진 게시판 가져옴
	 */
	@Override
	public TboardVO getTboard(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardDao", "getTboard()");
		return sqlSessionTemplate.selectOne("tboard.getTboard", tboardVO);
	}
	
	/**
	 * Method : updateTboardYN
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param tboardVO 해당 게시판 vo
	 * @return update된 튜플 수
	 * Method 설명 : 활성화여부 update
	 */
	@Override
	public int updateTboardYN(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardDao", "updateTboardYN(TboardVO tboardVO)");
		return sqlSessionTemplate.update("tboard.updateTboardYN", tboardVO);
	}

	/**
	 * Method : insertTboard
	 * 최초작성일 : 2018. 5. 14.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param tboardVO 새로운 게시판
	 * @return insert된 튜플 수
	 * Method 설명 : 새로운 게시판을 DB에 insert한다. insert된 튜플수 반환
	 */
	@Override
	public int insertTboard(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardDao", "insertTboard(TboardVO tboardVO)");
		return sqlSessionTemplate.update("tboard.insertTboard", tboardVO);
	}

}
