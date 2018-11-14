package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
public class SampleController {

	@GetMapping("/doA")
	public ResponseEntity<SampleVO> doA(){
		
		return new ResponseEntity(
				SampleVO.builder().v1("v1").v2("v2").v3("v3").build(),
				HttpStatus.OK);
	}//내가원하는 것만 세팅가능, 생성자에 대한 제약이 사라짐.(생성자를 많이 만들어야되는 상황에 고려한다.)
	
}
