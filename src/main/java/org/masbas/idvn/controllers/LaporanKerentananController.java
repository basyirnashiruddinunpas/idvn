package org.masbas.idvn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LaporanKerentananController {
	private String appMode;
	
	@Autowired
	public LaporanKerentananController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/home")
	public String index(Model model) {
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		return "index";
	}
	
}
