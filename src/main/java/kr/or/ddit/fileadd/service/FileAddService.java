package kr.or.ddit.fileadd.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.fileadd.dao.FileAddDaoInf;
import kr.or.ddit.fileadd.model.FileAddVO;

import org.springframework.stereotype.Service;

@Service("fileAddService")
public class FileAddService implements FileAddServiceInf {
	
	@Resource(name="fileAddDao")
	private FileAddDaoInf fileAddDao;
	
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
		return fileAddDao.insertFileAdd(fileAddVO);
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
		return fileAddDao.getFileAdd(file_board_seq);
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
		int result = 0;
		result = fileAddDao.deleteFileAdd(file_board_seq);
		
		return result;
	}

}
