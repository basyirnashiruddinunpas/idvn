package org.masbas.idvn.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.helpers.exceptions.UserAlreadyExistException;
import org.masbas.idvn.models.User;
import org.masbas.idvn.repositories.UserRepository;
import org.masbas.idvn.services.LaporanService;
import org.masbas.idvn.services.UserService;
import org.masbas.idvn.viewmodels.LoginVM;
import org.masbas.idvn.viewmodels.RegistrationVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {
	private String appMode;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LaporanService laporanService;
	
	@Autowired
	public WebController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:home";
	}
	
	@RequestMapping("/denied")
	public String denied(Model model) {
		return "redirect:home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
//		userService.setAuditor("5fdc46eaa3cb863e9646050d", "5fdc4b67a3cb863e96460516");
//		userService.setAuditor("5fdc47a3a3cb863e96460510", "5fdc4b45a3cb863e96460515");
//		userService.setAuditor("5fdc4a89a3cb863e96460514", "5fdc4b45a3cb863e96460515");
//		userService.setAuditor("5fdc4944a3cb863e96460512", "5fdc4b67a3cb863e96460516");
		return "content/about";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=false,defaultValue = "") String error, Model model) {
		if (error.equals("login")) {
			model.addAttribute("errorLogin", "Username / Password Salah");
			model.addAttribute("errorMsg", "");
		} else {
			model.addAttribute("errorMsg", "");
			model.addAttribute("errorLogin", "");
		}
		return "content/auth/login";
	}
	
//	@PostMapping("/dologin")
//	public String doLogin(@RequestParam(required=false,defaultValue = "false") String error, @ModelAttribute @Valid LoginDto loginDto, HttpServletRequest request, 
//			Errors errors) {
//		System.out.println("AAAAAAAA");
//		return "";
//	}
	
//	@RequestMapping("/register/notifier")
//	public String showRegistrationNotifier(WebRequest request, Model model) {
//		RegistrationDto registrationDto = new RegistrationDto();
//		model.addAttribute("registration", registrationDto);
//		model.addAttribute("tipeUser", UserHelper.TIPE_NOTIFIER);
//		return "content/register/notifier";
//	}
	
//	@PostMapping("/register")
//	public ModelAndView registerUserAccount(@ModelAttribute @Valid RegistrationDto registrationDto, HttpServletRequest request, 
//			Errors errors) {
//		
//		try {
//			registrationDto.setTipeUser(UserHelper.TIPE_NOTIFIER);
//			UserModel registered = userService.registerNewUserAccount(registrationDto);
//		} catch(UserAlreadyExistException uaex) {
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("message", "Akun yang dimasukan sudah terdaftar");
//			return mav;
//		}
//		
//		return new ModelAndView("content/user/profile", "user", registrationDto);
//	}
	@PostMapping("/register/notifier")
	public String registerUserAccount(@ModelAttribute @Valid RegistrationVM registrationDto, HttpServletRequest request, 
			Errors errors, RedirectAttributes redir) {
		if(!registrationDto.getPassword().equals(registrationDto.getMatchingPassword())) {
			redir.addFlashAttribute("error", "Password Tidak Cocok");
			return("redirect:/login");
		}
		
		try {
			registrationDto.setTipeUser(UserHelper.TIPE_NOTIFIER);
			User registered = userService.registerNewUserAccount(registrationDto);
		} catch(UserAlreadyExistException uaex) {
			redir.addFlashAttribute("error", "Pengguna Sudah Terdaftar");
			return("redirect:/login");
		}
		
		return("redirect:/home");
	}
	
	public int getTotalLaporan() {
		return laporanService.findTotalKerentanan().intValue();
	}

}
