package kr.or.ddit.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.board.model.BoardVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("boardDao")
public class BoardDao implements BoardDaoInf {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Method : getBoardPagingList
	 * 최초작성일 : 2018. 5. 15.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param board_tboard_seq 해당 게시판 번호
	 * @param pageNum 해당 페이지 번호
	 * @return
	 * Method 설명 : 게시판의 페이지번호에 맞는 게시글 목록을 반환
	 */
	@Override
	public List<BoardVO> getBoardPagingList(String board_tboard_seq, String pageNum) {
		logger.debug("{} : {}", "BoardDao" , "getBoardPagingList(String board_tboard_seq, String pageNum)");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("board_tboard_seq", board_tboard_seq);
		map.put("pageNum", pageNum);
		List<BoardVO> list = sqlSessionTemplate.selectList("board.getBoardPagingList", map);
		
		return list;
	}
	
	/**
	 * Method : getBoardListCount
	 * 최초작성일 : 2018. 5. 15.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param board_tboard_seq 게시판번호
	 * @return
	 * Method 설명 : 게시판의 모든 게시글 수를 반환
	 */
	@Override
	public int getBoardListCount(String board_tboard_seq) {
		logger.debug("{} : {}", "BoardDao" , "getBoardListCount(String board_tboard_seq)");
		int result = sqlSessionTemplate.selectOne("board.getBoardListCount", board_tboard_seq);
				
		return result;
	}

	/**
	 * Method : insertBoard
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 일반적인 글쓰기(부모글 없음)
	 */
	@Override
	public int insertBoard(BoardVO boardVO) {
		logger.debug("{} : {}", "BoardDao" , "insertBoard(BoardVO boardVO)");
		int result = sqlSessionTemplate.insert("board.insertBoard", boardVO);

		return result;
	}
	
	/**
	 * Method : insertBoardP
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 부모글에 계층형글쓰기
	 */
	@Override
	public int insertBoardP(BoardVO boardVO) {
		logger.debug("{} : {}", "BoardDao" , "insertBoardP(BoardVO boardVO)");
		int result = sqlSessionTemplate.insert("board.insertBoardP", boardVO);

		return result;
	}

	/**
	 * Method : getBoardInfo
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param board_seq
	 * @return
	 * Method 설명 : 게시글의 모든정보조회(게시글번호이용) - 계층형글쓰기에도 사용함
	 */
	@Override
	public BoardVO getBoardInfo(String board_seq) {
		logger.debug("{} : {}", "BoardDao" , "getBoardPagingList(String board_tboard_seq, String pageNum)");
		BoardVO result = sqlSessionTemplate.selectOne("board.getBoardInfo", board_seq);

		return result;
	}

	/**
	 * Method : updateBoard_del_yn
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param board_seq
	 * @return
	 * Method 설명 : 해당 게시글의 사용여부를 N으로 변경
	 */
	@Override
	public int updateBoard_del_yn(String board_seq) {
		logger.debug("{} : {}", "BoardDao" , "updateBoard_del_yn(String board_seq)");
		int result = sqlSessionTemplate.update("board.updateBoard_del_yn", board_seq);
		
		return result;
	}

	/**
	 * Method : updateBoard
	 * 최초작성일 : 2018. 5. 16.
	 * 작성자 : "K.S.J"
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 해당 게시글 수정
	 */
	@Override
	public int updateBoard(BoardVO boardVO) {
		logger.debug("{} : {}", "BoardDao" , "updateBoard(BoardVO boardVO)");
		int result = sqlSessionTemplate.update("board.updateBoard", boardVO);
		
		return result;
	}
	
}
