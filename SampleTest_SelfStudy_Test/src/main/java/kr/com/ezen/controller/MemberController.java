package kr.com.ezen.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/")
	public String member(Model model) {
		log.info("-------------------");
		String msg = "이름";
		model.addAttribute("name", msg);
		
		return "member";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<MemberVO> list = memberService.list();
		
		list.forEach(vo -> log.info(vo));
		
		model.addAttribute("list", list);
		
		return "memberList";
	}
	
	@PostMapping("/insert")
	public String insert(MemberVO vo) {
		
		log.info("-----------------");
		log.info(vo);
		//저장
		memberService.insertMember(vo);
		return null;
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id")int id) {
		
		log.info("delete : " + id);
		
		memberService.deleteMember(id);
		
		return null;
	}
	
	
}
