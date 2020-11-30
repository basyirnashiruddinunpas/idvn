package org.masbas.idvn.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.masbas.idvn.helpers.CurrentUserHelper;
import org.masbas.idvn.helpers.PageWrapper;
import org.masbas.idvn.helpers.StatusHelper;
import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.models.Laporan;
import org.masbas.idvn.models.Patch;
import org.masbas.idvn.models.StatusVendor;
import org.masbas.idvn.models.User;
import org.masbas.idvn.models.Workaround;
import org.masbas.idvn.services.LaporanService;
import org.masbas.idvn.services.UserService;
import org.masbas.idvn.viewmodels.LaporVM;
import org.masbas.idvn.viewmodels.PatchVM;
import org.masbas.idvn.viewmodels.StatusVendorVM;
import org.masbas.idvn.viewmodels.WorkaroundVM;
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
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("mode", appMode);
		
//		userService.setAuditor("5fbcfe341186ce5be978686e", "5fbda8610e0fb13abbb655dc");
		
		return "content/index/index";
	}
	
	@RequestMapping("/kerentanan/terbaru")
	public String indexTerbaru(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanPageable(PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("page", pager);
		model.addAttribute("mode", appMode);
		
		return "content/index/terbaru";
	}
	
	@RequestMapping("/kerentanan/tracking")
	public String indexTracking(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanTrackingPageable(PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("page", pager);
		model.addAttribute("mode", appMode);
		
		return "content/index/tracking";
	}
	
	@RequestMapping("/kerentanan/invalid")
	public String indexInvalid(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanInvalidPageable(PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("page", pager);
		model.addAttribute("mode", appMode);
		
		return "content/index/invalid";
	}
	
	@RequestMapping("/kerentanan/my")
	public String myIndex(Model model) {
		User user = this.getCurrentUser();
		if(UserHelper.TIPE_NOTIFIER.equalsIgnoreCase(user.getTipeUser())) {
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableCurrUser(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		} else if(UserHelper.TIPE_VENDOR.equalsIgnoreCase(user.getTipeUser())) {
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableVendor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		} else if(UserHelper.TIPE_AUDITOR.equalsIgnoreCase(user.getTipeUser())) {
			model.addAttribute("kerentananTerbaru", laporanService.findLaporanPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("trackingKerentanan", laporanService.findLaporanTrackingPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
			model.addAttribute("kerentananInvalid", laporanService.findLaporanInvalidPageableAuditor(user,PageRequest.of(0, 10, Sort.by("createdTimeStamp").descending())));
		}
		model.addAttribute("vendors", getListVendor());
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getRoles().get(0));
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("mode", appMode);
		
		return "content/index/myindex";
	}
	
	@RequestMapping("/kerentanan/my/terbaru")
	public String myIndexTerbaru(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		User user = this.getCurrentUser();
		if(UserHelper.TIPE_NOTIFIER.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanPageableCurrUser(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_VENDOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanPageableVendor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_AUDITOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanPageableAuditor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/terbaru");
			model.addAttribute("page", pager);
		}
		model.addAttribute("vendors", getListVendor());
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getRoles().get(0));
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("mode", appMode);
		
		return "content/index/myindexterbaru";
	}
	
	@RequestMapping("/kerentanan/my/tracking")
	public String myIndexTracking(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		User user = this.getCurrentUser();
		if(UserHelper.TIPE_NOTIFIER.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanTrackingPageableCurrUser(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/tracking");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_VENDOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanTrackingPageableVendor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/tracking");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_AUDITOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanTrackingPageableAuditor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/tracking");
			model.addAttribute("page", pager);
		}
		model.addAttribute("vendors", getListVendor());
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getRoles().get(0));
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("mode", appMode);
		
		return "content/index/myindextracking";
	}
	
	@RequestMapping("/kerentanan/my/invalid")
	public String myIndexInvalid(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currPage = page.orElse(1);
		int currSize = size.orElse(5);
		User user = this.getCurrentUser();
		if(UserHelper.TIPE_NOTIFIER.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanInvalidPageableCurrUser(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/invalid");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_VENDOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanInvalidPageableVendor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/invalid");
			model.addAttribute("page", pager);
		} else if(UserHelper.TIPE_AUDITOR.equalsIgnoreCase(user.getTipeUser())) {
			PageWrapper<Laporan> pager = new PageWrapper<Laporan>(laporanService.findAllLaporanInvalidPageableAuditor(user, PageRequest.of(currPage-1, currSize, Sort.by("createdTimeStamp").descending())), "/kerentanan/invalid");
			model.addAttribute("page", pager);
		}
		model.addAttribute("vendors", getListVendor());
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getRoles().get(0));
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("mode", appMode);
		
		return "content/index/myindexinvalid";
	}

	@RequestMapping("/kerentanan/lapor")
	public String lapor(Model model) {
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("mode", appMode);
		model.addAttribute("pageTitle", "IDVN - Indonesia Vulnerability Notes");
		model.addAttribute("vendors", userService.findAllVendor());
		return "content/pelaporan/lapor";
	}
	
	@PostMapping("/kerentanan/save")
	public String save(@ModelAttribute LaporVM laporanDto) {
		User vendor = userService.getUserById(laporanDto.getVendorStr());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd");
		String date = dateFormat.format(new Date());
		Laporan latest = laporanService.findLatestLaporan();
		String[] split = latest.getCode().split("-");
		int newCode = Integer.valueOf(split[3]);
		newCode++;
		
		
		Laporan laporan = new Laporan();
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
	
	@PostMapping("/kerentanan/my/update/verify")
	public String setVerify(@ModelAttribute StatusVendorVM statusVendor) {
		Laporan laporan = laporanService.findById(statusVendor.getId()).get();
		StatusVendor status = new StatusVendor();
		List<StatusVendor> lsStatus = new ArrayList<StatusVendor>();
		status.setCatatanVendor(statusVendor.getCatatanVendor());
		status.setStatusVendor(statusVendor.getStatusVendor());
		status.setCreatedTimestamp(new Date());
		status.setUpdatedTimestamp(new Date());
		lsStatus.add(status);
		laporan.setStatusVendor(lsStatus);
		laporan.setStatus(StatusHelper.STATUS_PROCESSED);
		laporanService.save(laporan);
		
		
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@RequestMapping("/kerentanan/my/update/invalid/{code}")
	public String setInvalid(@PathVariable String code) {
		Laporan laporan = laporanService.findById(code).get();
		laporan.setStatus(StatusHelper.STATUS_NOT_VALID);
		laporanService.save(laporan);
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@PostMapping("/kerentanan/my/update/addstatus")
	public String addStatus(@ModelAttribute StatusVendorVM statusVendor) {
		Laporan laporan = laporanService.findById(statusVendor.getId()).get();
		StatusVendor status = new StatusVendor();
		List<StatusVendor> lsStatus = laporan.getStatusVendor();
		status.setCatatanVendor(statusVendor.getCatatanVendor());
		status.setStatusVendor(statusVendor.getStatusVendor());
		status.setCreatedTimestamp(new Date());
		status.setUpdatedTimestamp(new Date());
		lsStatus.add(status);
		laporan.setStatusVendor(lsStatus);
		laporanService.save(laporan);
		
		
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@PostMapping("/kerentanan/my/update/addworkaround")
	public String addWorkaround(@ModelAttribute WorkaroundVM workaroundDto) {
		Laporan laporan = laporanService.findById(workaroundDto.getId()).get();
		Workaround workaround = new Workaround();
		List<Workaround> lsWorkaround = laporan.getWorkarounds();
		if(lsWorkaround==null)
			lsWorkaround = new ArrayList<Workaround>();
		
		workaround.setSolution(workaroundDto.getSolution());
		workaround.setCreatedTimestamp(new Date());
		workaround.setUpdatedTimestamp(new Date());
		lsWorkaround.add(workaround);
		laporan.setWorkarounds(lsWorkaround);
		laporanService.save(laporan);
		
		
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@PostMapping("/kerentanan/my/update/addpatch")
	public String addPatch(@ModelAttribute PatchVM patchDto) {
		Laporan laporan = laporanService.findById(patchDto.getId()).get();
		Patch patch = new Patch();
		
		patch.setCatatanPatch(patchDto.getCatatanPatch());
		patch.setUrlPatch(patchDto.getUrlPatch());
		patch.setCreatedTimestamp(new Date());
		patch.setUpdatedTimestamp(new Date());
		laporan.setStatus(StatusHelper.STATUS_PATCHED);
		laporan.setPatch(patch);
		laporanService.save(laporan);
		
		
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@RequestMapping("/kerentanan/my/update/archive/{code}")
	public String setArchived(@PathVariable String code) {
		Laporan laporan = laporanService.findById(code).get();
		laporan.setStatus(StatusHelper.STATUS_ARCHIVED);
		laporanService.save(laporan);
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@PostMapping("/kerentanan/my/update/save")
	public String update(@ModelAttribute LaporVM laporanDto) {
		Laporan laporan = laporanService.findById(laporanDto.getId()).get();
		laporan.setOverview(laporanDto.getOverview());
		laporan.setProductAffected(laporanDto.getProductAffected());
		laporan.setReferences(laporanDto.getReferences());
		laporan.setDescription(laporanDto.getDescription());
		laporan.setImpact(laporanDto.getImpact());
		laporan.setVectorString(laporanDto.getVectorString());
		String email = CurrentUserHelper.GetLoggedUserName();
		if(email!="") {
			laporan.setEditedBy(userService.getUserByEmail(email));
		}
		laporan.setUpdatedTimeStamp(new Date());
		laporanService.save(laporan);
		
		return "redirect:/kerentanan/my/show/" + laporan.getCode();
	}
	
	@RequestMapping("/kerentanan/show/{code}")
	public String show(@PathVariable String code, Model model) {
		Laporan laporan = laporanService.findByCode(code);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy, hh:mm", Locale.US);
		String date = dateFormat.format(laporan.getCreatedTimeStamp());
		String dateEdit = "";
		if(laporan.getEditedBy() != null) {
			dateEdit = dateFormat.format(laporan.getUpdatedTimeStamp());
		}
				
		
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("laporan", laporan);
		model.addAttribute("judul", laporan.getOverview() + " [" + date + "]");
		model.addAttribute("date", date);
		model.addAttribute("dateEdit", dateEdit);
		return "content/pelaporan/show";
	}
	
	@RequestMapping("/kerentanan/my/show/{code}")
	public String showMy(@PathVariable String code, Model model) {
		Laporan laporan = laporanService.findByCode(code);
		if(!checkHasAccess(laporan)) {
			return "redirect:/kerentanan/my/";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yy, hh:mm", Locale.US);
		String date = dateFormat.format(laporan.getCreatedTimeStamp());
		String dateEdit = "";
		if(laporan.getEditedBy() != null) {
			dateEdit = dateFormat.format(laporan.getUpdatedTimeStamp());
		}
		
		model.addAttribute("totalKerentanan", getTotalLaporan());
		model.addAttribute("laporan", laporan);
		model.addAttribute("judul", laporan.getOverview() + " [" + date + "]");
		model.addAttribute("date", date);
		model.addAttribute("dateEdit", dateEdit);
		model.addAttribute("vendors", userService.findAllVendor());
		return "content/pelaporan/kelola";
	}
	
	private int getTotalLaporan() {
		return laporanService.findTotalKerentanan().intValue();
	}

	private User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserByEmail(authentication.getName());
	}
	
	private List<User> getListVendor() {
		User user = getCurrentUser();
		return userService.findVendorByAuditor(user);
	}
	
	private boolean checkHasAccess(Laporan laporan) {
		User user = getCurrentUser();
		// CEK APAKAH VENDOR
		if(laporan.getVendor().getId().equals(user.getId())) 
			return true;
		// CEK APAKAH AUDITOR
		if(laporan.getVendor().getAuditor()!=null) {
			for (User usr : laporan.getVendor().getAuditor())
				if(usr.getId().equals(usr.getId()))
					return true;
		}
		
		return false;
	}
	
}
