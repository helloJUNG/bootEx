package org.zerock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/sample/*")
public class SampleController {

	@PreAuthorize("permitAll()")//isAnonymous() 로그인안한 사용자만 됨 , 로그인하면 안됨.
	@GetMapping("/all")
	public void doAll() {
		log.info("doAll................");
	}
	
	@PreAuthorize("isAuthenticated()")  //로그인한 사용자만 볼 수 있다.
	@GetMapping("/member")
	public void doMember() {
		log.info("doMember................");
	}
	
	@PreAuthorize("hasRole('ROLE_BUYER')")
	@GetMapping("/buyer")
	public void doBuyer() {
		log.info("doBuyer................");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("doAdmin................");
	}
	
}
