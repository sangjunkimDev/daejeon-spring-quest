package kr.or.ddit.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberServiceInf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/home")
@Controller
public class MemberController {
	
	@Resource(name="memberService")
	private MemberServiceInf memberService;
	
	@RequestMapping("/loginpage")
	public String loginpage(Model model) {
		return "home/loginPage";
	}
	
	
	@RequestMapping("/login")
	public String login(Model model, MemberVO memberVO, @RequestParam("mem_id") String mem_id, HttpSession session) {
		MemberVO returnMemberVO = memberService.memberLogin(memberVO);
		boolean flag = memberService.loginYN(memberVO.getMem_id(), returnMemberVO);
		
		if (flag) {
			session.setAttribute("MemberVO", returnMemberVO);
			return "redirect:/main/tboardlistpage";
		} else {
			model.addAttribute("mem_id", memberVO.getMem_id());
			return "home/loginpage";
		}
	}
	
}
