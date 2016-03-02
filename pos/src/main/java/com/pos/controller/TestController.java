package com.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("first")
	public void test() {
		System.out.println("helloworld");
	}
}
