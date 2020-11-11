package org.masbas.idvn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	private String appMode;
	
	@Autowired
	public WebController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		return "index";
	}

}
