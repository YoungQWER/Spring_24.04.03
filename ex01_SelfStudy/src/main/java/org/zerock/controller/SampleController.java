package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic...............");
	}
	
	@RequestMapping(value = "/basic", method = { RequestMethod.GET })
	public void basicGetPost() {
		log.info("basic get............");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.POST })
	public void basicGetPost2() {
		log.info("basic post............");
	}

	@GetMapping(value = "/basic2")
	public void basicGet2() {
		log.info("basic get3............");
	}

	@PostMapping("/basic2")
	public void basicPost2() {
		log.info("basic post2............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	@PostMapping("/ex01")
	public String ex01_(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}

	@PostMapping("/ex02")
	public String ex02_(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.info(dto);
		log.info(dto.getTitle());
		log.info(dto.getDueDate());
		return "ex03";
	}

	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page, Model model) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		model.addAttribute("list", dto);
		model.addAttribute("page", page);
		
		return "/sample/ex04";
	}

	



}
