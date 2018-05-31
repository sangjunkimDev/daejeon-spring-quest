package kr.or.ddit.tboard.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.tboard.dao.TboardDaoInf;
import kr.or.ddit.tboard.model.TboardVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TboardService.java
 *
 * @author "K.S.J"
 * @since 2018. 5. 15.
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

@Service("tboardService")
public class TboardService implements TboardServiceInf {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="tboardDao")
	private TboardDaoInf tboardDao;
	
	/**
	 * Method : getTboardList
	 * 최초작성일 : 2018. 5. 15.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @return
	 * Method 설명 : 모든 게시판의 목록을 가져옴
	 */
	@Override
	public List<TboardVO> getTboardList() {
		logger.debug("{} : {}", "TboardService", "getTboardList()");
		return tboardDao.getTboardList();
	}

	@Override
	public TboardVO getTboard(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardService", "getTboard()");
		return tboardDao.getTboard(tboardVO);
	}

	/**
	 * Method : updateTboardYN
	 * 최초작성일 : 2018. 5. 15.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param tboardVO 해당 게시판 vo
	 * @return update된 튜플 수
	 * Method 설명 : 활성화여부 update
	 */
	@Override
	public int updateTboardYN(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardService", "updateTboardYN(TboardVO tboardVO)");
		return tboardDao.updateTboardYN(tboardVO);
	}

	/**
	 * Method : insertTboard
	 * 최초작성일 : 2018. 5. 15.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param tboardVO 새로운 게시판
	 * @return insert된 튜플 수
	 * Method 설명 : 새로운 게시판을 DB에 insert한다. insert된 튜플수 반환
	 */
	@Override
	public int insertTboard(TboardVO tboardVO) {
		logger.debug("{} : {}", "TboardService", "(TboardVO tboardVO)");
		return tboardDao.insertTboard(tboardVO);
	}

}
