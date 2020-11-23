package org.masbas.idvn.controllers;

import java.util.Date;

import org.masbas.idvn.helpers.CurrentUserHelper;
import org.masbas.idvn.helpers.DateTimeHelpers;
import org.masbas.idvn.models.BasedModel;
import org.masbas.idvn.models.LaporanModel;
import org.masbas.idvn.models.UserModel;
import org.masbas.idvn.services.LaporanService;
import org.masbas.idvn.services.UserService;
import org.masbas.idvn.viewmodels.LaporanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LaporanKerentananController {
	private String appMode;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LaporanService laporanService;
	
	@Autowired
	public LaporanKerentananController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/home")
	public String index(Model model) {
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		
		return "content/index/index";
	}

	@RequestMapping("/kerentanan/lapor")
	public String lapor(Model model) {
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		model.addAttribute("pageTitle", "IDVN - Indonesia Vulnerability Notes");
		return "content/pelaporan/lapor";
	}
	
	@PostMapping("/kerentanan/save")
	public String save(@ModelAttribute LaporanDto laporanDto) {
		UserModel vendor = userService.getUserById(laporanDto.getVendorStr());
		
		
		LaporanModel laporan = new LaporanModel();
		laporan.setOverview(laporanDto.getOverview());
		laporan.setProductAffected(laporanDto.getProductAffected());
		laporan.setVendor(vendor);
		laporan.setReferences(laporanDto.getReferences());
		laporan.setDescription(laporanDto.getDescription());
		laporan.setImpact(laporanDto.getImpact());
		laporan.setVectorString(laporanDto.getVectorString());
		String email = CurrentUserHelper.GetLoggedUserName();
		if(email!="") {
			laporan.setCreatedBy(userService.getUserByEmail(email));
		}
			
		laporan.setCreatedTimeStamp(new Date());
		laporan.setUpdatedTimeStamp(new Date());
		laporanService.save(laporan);
		
		return "redirect:/base/show/" + laporan.getId();
	}
	
}
