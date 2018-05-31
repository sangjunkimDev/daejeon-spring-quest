package kr.or.ddit.tboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.tboard.model.TboardVO;
import kr.or.ddit.tboard.service.TboardServiceInf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class TboardController {
	
	@Resource(name="tboardService")
	private TboardServiceInf tboardService;
	
	@RequestMapping("/tboardlistpage")
	public String getTboardList(Model model, HttpSession session) {
		List<TboardVO> tboardList = tboardService.getTboardList();
		model.addAttribute("tboardList", tboardList);
		session.setAttribute("pageChoose", "tboardList");
		
		return "tboardList";
	}
	
	@RequestMapping("/tboardinsertpage")
	public String tboardInsertPage() {
		return "tboardinsertpage";
	}
	
	@RequestMapping("/tboardInsert")
	public String tboardInsert(TboardVO tboardVO) {
		tboardService.insertTboard(tboardVO);
		
		return "redirect:/main/tboardlistpage";
	}
	
	@RequestMapping("/tboardUpdate")
	public String tboardUpdate(TboardVO tboardVO, Model model) {
		int result = tboardService.updateTboardYN(tboardVO);
		
		if (result == 1) {
			return "redirect:/main/tboardlistpage";
		} else {
			return "redirect:/home/loginpage";
		}
	}
}
