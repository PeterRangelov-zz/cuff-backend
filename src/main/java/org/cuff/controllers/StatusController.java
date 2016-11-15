package org.cuff.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatusController {
	
	@RequestMapping(value = "/pulse")
	public String healthCheck() {
		return "Application is running.";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/transmit")
	public String samplePost(@RequestParam(value="param1") String param1) {
		System.out.println(param1);
		return param1;
	}
	

}
