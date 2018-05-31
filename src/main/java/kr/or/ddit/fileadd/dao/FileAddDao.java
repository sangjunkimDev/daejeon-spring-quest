package kr.or.ddit.fileadd.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.fileadd.model.FileAddVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * FileAddDao.java
 *
 * @author "K.S.J"
 * @since 2018. 5. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일 수정자 수정내용
 * ---------- ------ ------------------------
 * 2018. 5. 16. "K.S.J" 최초 생성
 *
 * </pre>
 */

@Repository("fileAddDao")
public class FileAddDao implements FileAddDaoInf {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Method : insertFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param fileAddVO
	 * @return
	 * Method 설명 : 파일추가
	 */
	@Override
	public int insertFileAdd(FileAddVO fileAddVO) {
		logger.debug("{} : {}", "FileAddDao", "insertFileAdd(FileAddVO fileAddVO)");
		int result = sqlSessionTemplate.insert("fileAdd.insertFileAdd", fileAddVO);
		
		return result;
	}

	/**
	 * Method : getFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param file_board_seq
	 * @return
	 * Method 설명 : 해당 게시글의 파일 목록을 반환
	 */
	@Override
	public List<FileAddVO> getFileAdd(String file_board_seq) {
		logger.debug("{} : {}", "FileAddDao", "getFileAdd(" + file_board_seq + ")");
		List<FileAddVO> result = sqlSessionTemplate.selectList("fileAdd.getFileAdd", file_board_seq);
		
		return result;
	}

	/**
	 * Method : deleteFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param file_board_seq
	 * @return
	 * Method 설명 : 해당 게시글의 파일을 제거
	 */
	@Override
	public int deleteFileAdd(String file_board_seq) {
		logger.debug("{} : {}", "FileAddDao", "deleteFileAdd(String file_board_seq)");
		int result = sqlSessionTemplate.delete("fileAdd.deleteFileAdd", file_board_seq);
		
		return result;
	}

}