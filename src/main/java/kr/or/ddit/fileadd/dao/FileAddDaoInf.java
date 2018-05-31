package kr.or.ddit.fileadd.dao;

import java.util.List;

import kr.or.ddit.fileadd.model.FileAddVO;

/**
 * FileAddDaoInf.java
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
public interface FileAddDaoInf {
	/**
	 * Method : insertFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param fileAddVO
	 * @return
	 * Method 설명 : 파일추가
	 */
	int insertFileAdd(FileAddVO fileAddVO);
	
	/**
	 * Method : getFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param file_board_seq
	 * @return
	 * Method 설명 : 해당 게시글의 파일 목록을 반환
	 */
	List<FileAddVO> getFileAdd(String file_board_seq);
	
	/**
	 * Method : deleteFileAdd
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param file_board_seq
	 * @return
	 * Method 설명 : 해당 게시글의 파일을 제거
	 */
	int deleteFileAdd(String file_board_seq);
	
}
