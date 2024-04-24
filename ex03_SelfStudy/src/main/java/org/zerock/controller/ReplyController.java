package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply/")
@Log4j
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping(value = "/new", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		
		log.info("create......" + reply);
		
		int insertCount = replyService.register(reply);
		
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) :
									new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
		@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
			
			log.info("get......" + rno);
			
			ReplyVO vo = replyService.get(rno);
			
			return new ResponseEntity<ReplyVO>(replyService.get(rno), HttpStatus.OK);
		}
 		
		@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> delete(@PathVariable("rno") Long rno){
			
			log.info("delete......" + rno);
			
			return replyService.remove(rno) == 1
					? new ResponseEntity<String>("success", HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@PutMapping(value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> update(@PathVariable("rno") Long rno, @RequestBody ReplyVO reply){
			
			log.info("rno......" + rno);
			log.info("reply......" + reply);
			
			reply.setRno(rno);
			
			return replyService.modify(reply) == 1 ?
					new ResponseEntity<String>("success", HttpStatus.OK):
					new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<ReplyVO>> getList(
				@PathVariable("bno") Long bno,
				@PathVariable("page") int page			
				){
			log.info("getList......" + bno + ", " + page);
			
			Criteria crt = new Criteria(page, 10);
			
			List<ReplyVO> list = replyService.getList(crt, bno);
			
			return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
		}
		
}
