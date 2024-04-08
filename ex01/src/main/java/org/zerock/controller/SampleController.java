package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic.................");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET})
	public void basicGetPost() {
		log.info("basic get.............");
	}

	@RequestMapping(value = "/basic", method = {RequestMethod.POST})
	public void basicGetPost2() {
		log.info("basic post.............");
	}
	
	@GetMapping("/basic2")
	public void basicGet2() {
		log.info("basic get3.............");
	}
	
	@PostMapping("/basic2")
	public void basicPost2() {
		log.info("basic post2.............");
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
	public String ex02(@RequestParam("name") String name
			, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
			return "ex02";
	}
	
	@PostMapping("/ex02")
	public String ex02_(@RequestParam("name") String name
			, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}

	
	
	
	
	
}

