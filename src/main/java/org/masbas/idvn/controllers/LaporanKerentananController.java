package org.masbas.idvn.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.masbas.idvn.helpers.CurrentUserHelper;
import org.masbas.idvn.helpers.StatusHelper;
import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.models.LaporanDao;
import org.masbas.idvn.models.UserDao;
import org.masbas.idvn.services.LaporanService;
import org.masbas.idvn.services.UserService;
import org.masbas.idvn.viewmodels.LaporanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageable(PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageable(PageRequest.of(0, 10)));
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		
//		userService.setAuditor("5fbcfe341186ce5be978686e", "5fbda8610e0fb13abbb655dc");
		
		return "content/index/index";
	}
	
	@RequestMapping("/kerentanan/my")
	public String myIndex(Model model) {
		UserDao user = this.getCurrentUser();
		System.out.println("TIPE: " + user.getTipeUser());
		if(UserHelper.TIPE_NOTIFIER.equalsIgnoreCase(user.getTipeUser())) {
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		} else if(UserHelper.TIPE_VENDOR.equalsIgnoreCase(user.getTipeUser())) {
			System.out.println("USER: " + user.getId());
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		} else if(UserHelper.TIPE_AUDITOR.equalsIgnoreCase(user.getTipeUser())) {
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		}
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		
		return "content/index/myindex";
	}

	@RequestMapping("/kerentanan/lapor")
	public String lapor(Model model) {
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("username", "Coba");
		model.addAttribute("mode", appMode);
		model.addAttribute("pageTitle", "IDVN - Indonesia Vulnerability Notes");
		List<UserDao> vendors = userService.findAllVendor();
		for (UserDao userModel : vendors) {
			System.out.println(userModel.getName());
		}
		model.addAttribute("vendors", userService.findAllVendor());
		return "content/pelaporan/lapor";
	}
	
	@PostMapping("/kerentanan/save")
	public String save(@ModelAttribute LaporanDto laporanDto) {
		UserDao vendor = userService.getUserById(laporanDto.getVendorStr());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
		String date = dateFormat.format(new Date());
		LaporanDao latest = laporanService.findLatestLaporan();
		String[] split = latest.getCode().split("-");
		int newCode = Integer.valueOf(split[3]);
		newCode++;
		
		
		LaporanDao laporan = new LaporanDao();
		laporan.setOverview(laporanDto.getOverview());
		laporan.setCode("IDVN-" + date + "-" + newCode);
		laporan.setProductAffected(laporanDto.getProductAffected());
		laporan.setVendor(vendor);
		laporan.setReferences(laporanDto.getReferences());
		laporan.setDescription(laporanDto.getDescription());
		laporan.setImpact(laporanDto.getImpact());
		laporan.setVectorString(laporanDto.getVectorString());
		laporan.setStatus(StatusHelper.STATUS_SUBMITTED);
		String email = CurrentUserHelper.GetLoggedUserName();
		if(email!="") {
			laporan.setCreatedBy(userService.getUserByEmail(email));
		}
			
		laporan.setCreatedTimeStamp(new Date());
		laporan.setUpdatedTimeStamp(new Date());
		laporanService.save(laporan);
		
		return "redirect:/kerentanan/show/" + laporan.getCode();
	}
	
	@RequestMapping("/kerentanan/show/{code}")
	public String show(@PathVariable String code, Model model) {
		LaporanDao laporan = laporanService.findByCode(code);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy, hh:mm", Locale.US);
		String date = dateFormat.format(laporan.getCreatedTimeStamp());
		
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("laporan", laporan);
		model.addAttribute("judul", laporan.getOverview() + " [" + date + "]");
		model.addAttribute("date", date);
		return "content/pelaporan/show";
	}
	
	public int getTotalLaporan() {
		return laporanService.findTotalKerentanan().intValue();
	}
	
	public UserDao getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(authentication.getName());
	}
	
}
