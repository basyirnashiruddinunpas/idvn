package org.masbas.idvn.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.repositories.UserRepository;
import org.masbas.idvn.services.UserService;
import org.masbas.idvn.viewmodels.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	private String appMode;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	public WebController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:home";
	}
	
	@RequestMapping("/register/notifier")
	public String showRegistrationNotifier(WebRequest request, Model model) {
		RegistrationDto registrationDto = new RegistrationDto();
		model.addAttribute("registration", registrationDto);
		model.addAttribute("tipeUser", UserHelper.TIPE_NOTIFIER);
		return "content/register/notifier";
	}
	
	@PostMapping("/register")
	public ModelAndView registerUserAccount(@ModelAttribute @Valid RegistrationDto registrationDto, HttpServletRequest request, 
			Errors errors) {
		
		try {
			UserModel registered = userService.registerNewUserAccount(registrationDto);
		} catch(UserAlreadyExistException uaex) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", "Akun yang dimasukan sudah terdaftar");
			return mav;
		}
		
		return new ModelAndView("content/user/profile", "user", registrationDto);
	}

}
