package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.fileadd.model.FileAddVO;
import kr.or.ddit.fileadd.service.FileAddServiceInf;
import kr.or.ddit.tboard.model.TboardVO;
import kr.or.ddit.tboard.service.TboardServiceInf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/main")
@Controller
public class BoardController {
	private final String UPLOAD_PATH = "D:/A_TeachingMaterial/7.JspSpring/uploadStorage";
	
	@Resource(name = "boardService")
	BoardServiceInf boardService;

	@Resource(name = "tboardService")
	TboardServiceInf tboardService;

	@Resource(name = "fileAddService")
	FileAddServiceInf fileAddService;

	@RequestMapping("/boardlistpage")
	public String boardlistpage(Model model,
			@RequestParam(required = false) String board_tboard_seq,
			@RequestParam(required = false) String pageNum, HttpSession session) {

		if (board_tboard_seq == null) {
			board_tboard_seq = (String) session
					.getAttribute("board_tboard_seq");
		}

		List<TboardVO> tboardListTmp = tboardService.getTboardList();

		List<TboardVO> tboardList = new ArrayList<TboardVO>();
		for (TboardVO tboardvo : tboardListTmp) {
			if (tboardvo.getTboard_del_yn().equals("Y")) {
				tboardList.add(tboardvo);
			}
		}

		TboardVO tboardVO = null;

		if (board_tboard_seq == null && tboardList.size() != 0) {
			tboardVO = tboardList.get(0);
			board_tboard_seq = tboardVO.getTboard_seq();
		} else {
			tboardVO = new TboardVO();
			tboardVO.setTboard_seq(board_tboard_seq);
			tboardVO = tboardService.getTboard(tboardVO);
		}

		pageNum = (pageNum == null ? "1" : pageNum);

		int count = boardService.getBoardListCount(board_tboard_seq);
		List<BoardVO> boardList = boardService.getBoardPagingList(
				board_tboard_seq, pageNum);

		int pageCount = 0;
		if (count != 0) {
			pageCount = (int) Math.ceil(count / 10d);
		}

		session.setAttribute("pageChoose", "BoardList");

		model.addAttribute("tboardList", tboardList);
		model.addAttribute("tboardVO", tboardVO);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("boardList", boardList);

		return "boardList";
	}

	@RequestMapping("/boardInfo")
	public String boardInfo(@RequestParam String board_seq, Model model) {

		BoardVO boardVO = boardService.getBoardInfo(board_seq);
		List<FileAddVO> fileAddVOList = fileAddService.getFileAdd(board_seq);
		// 댓글도 추가 필요~~~

		model.addAttribute("boardVO", boardVO);
		model.addAttribute("fileAddVOList", fileAddVOList);
		// 댓글도 추가 필요~~~

		return "boardInfo";
	}

	@RequestMapping("boardInsertPage")
	public String boardInsertPage() {
		return "boardInsertPage";
	}
	
	
	@RequestMapping("boardInsert")
	public String boardInsert(BoardVO boardVO, HttpServletRequest request, Model model, @RequestPart("file") List<MultipartFile> multipartFileArr) throws ServletException, IOException {
		// 게시글 처리 부분
		String board_p_seq = boardVO.getBoard_p_seq();
		
		if (board_p_seq.isEmpty()) {
			boardService.insertBoard(boardVO);
		} else {
			boardVO.setBoard_p_seq(board_p_seq);
			boardService.insertBoardP(boardVO);
		}

		// 파일 처리 부분
		List<FileAddVO> fileAddVOList = new ArrayList<FileAddVO>();
//		fileSetting(request, boardVO.getBoard_seq(), fileAddVOList);
		fileSetting(multipartFileArr, boardVO.getBoard_seq(), fileAddVOList);
		for (FileAddVO fileAddVO : fileAddVOList) {
			fileAddService.insertFileAdd(fileAddVO);
		}
		
		model.addAttribute("board_tboard_seq", boardVO.getBoard_tboard_seq());
		request.getSession().setAttribute("board_tboard_seq", boardVO.getBoard_tboard_seq());
		return "redirect:/main/boardlistpage";
	}

	private void fileSetting(List<MultipartFile> multipartFileArr, String file_board_seq,
			List<FileAddVO> fileAddVOList) {
		
		for (MultipartFile multipartFile : multipartFileArr) {
			String fileName = multipartFile.getName();
			String originalFilename = multipartFile.getOriginalFilename();
			String file_path = UPLOAD_PATH + "/" + originalFilename;

			try {
				// 파일 전송
				multipartFile.transferTo(new File(file_path));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			FileAddVO fileAddVO = new FileAddVO();
			fileAddVO.setFile_board_seq(file_board_seq);
			fileAddVO.setFile_path(originalFilename);

			fileAddVOList.add(fileAddVO);
		}
	}


	@RequestMapping("/boardDelete")
	public String boardDelete(BoardVO boardVO, HttpSession session) throws ServletException, IOException {
		String board_seq = boardVO.getBoard_seq();
		String board_tboard_seq = boardVO.getBoard_tboard_seq();
		
		boardService.updateBoard_del_yn(board_seq);
		
		session.setAttribute("board_tboard_seq", board_tboard_seq);
		return "redirect:/main/boardlistpage";
	}
	
	
	@RequestMapping("/boardUpdatePage")
	public String boardUpdatePage(@RequestParam String board_seq, Model model) {
		//update jsp로 전송
		BoardVO boardVO = boardService.getBoardInfo(board_seq);
		List<FileAddVO> fileAddVOList = fileAddService.getFileAdd(board_seq);
		TboardVO tboardVO = new TboardVO();
		tboardVO.setTboard_seq(boardVO.getBoard_tboard_seq());
		String tboard_title = tboardService.getTboard(tboardVO).getTboard_title();
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("fileAddVOList", fileAddVOList);
		model.addAttribute("tboard_title", tboard_title);
		
		return "boardUpdatePage";
	}
	
	
	@RequestMapping("boardUpdate")
	public String boardUpdate(BoardVO boardVO, HttpServletRequest request, Model model, @RequestPart("file") List<MultipartFile> multipartFileArr) throws ServletException, IOException {
		String board_seq = boardVO.getBoard_seq();
		
		//게시글 수정 부분
		boardService.updateBoard(boardVO);
		
		//===========================================================================
		//파일 처리 부분
		//사용할 파일 목록
		List<FileAddVO> fileAddVOList = new ArrayList<FileAddVO>();
		
		//기존 파일에서 남은 파일들 배열
		String[] chkBoxFiles = request.getParameterValues("chkBox");
		
		//기존 파일을 제거
		fileAddService.deleteFileAdd(board_seq);
		
		if (chkBoxFiles != null) {
			//유지할 파일만 선정하기
			for (String chkBoxFile : chkBoxFiles) {
				FileAddVO fileAddVO = new FileAddVO();
				fileAddVO.setFile_path(chkBoxFile);
				fileAddVO.setFile_board_seq(board_seq);
				fileAddVOList.add(fileAddVO);
			}
		}
		fileSetting(multipartFileArr, boardVO.getBoard_seq(), fileAddVOList);
		
		for (FileAddVO fileAddVO : fileAddVOList) {
			fileAddService.insertFileAdd(fileAddVO);
		}
		
		//해당 게시글 상세화면으로 이동
		return "redirect:/main/boardInfo?board_seq=" + board_seq;
	}
	
	
	
	
	
	
	
	
	
	
}
