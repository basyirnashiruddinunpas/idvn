package org.masbas.idvn.controllers;

import java.util.Optional;

import org.masbas.idvn.models.BasedModel;
import org.masbas.idvn.repositories.BasedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasedController {
	
	@Autowired
	BasedRepository basedRepository;

	@RequestMapping("/base")
	public String base(Model model) {
		model.addAttribute("bases", basedRepository.findAll());
		return "content/base/base";
	}
	
	@RequestMapping("/bases")
	public String bases(Model model) {
		model.addAttribute("bases", basedRepository.findAll());
		return "content/edit/edit_vendor";
	}
	
	@RequestMapping("/base/create")
	public String create(Model model) {
		return "content/base/create";
	}

//	@RequestMapping(value = "/save"
//			, method = RequestMethod.POST
//            , produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//            , consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
	@PostMapping("/base/save")
	public String save(@ModelAttribute BasedModel basedModel) {
		BasedModel base = new BasedModel();
		base.setName(basedModel.getName());
		base.setDesc(basedModel.getDesc());
		base.setImg(basedModel.getImg());
		base.setPrice(basedModel.getPrice());
		basedRepository.save(base);
		
		return "redirect:/base/show/" + base.getId();
	}
	
	@RequestMapping("/base/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("base", basedRepository.findById(id).get());
		return "content/base/show";
	}
	
	@RequestMapping("/base/delete")
	public String delete(@RequestParam String id) {
		Optional<BasedModel> base = basedRepository.findById(id);
		basedRepository.delete(base.get());
		return "redirect:/base";
	}
	
	@RequestMapping("/base/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("base", basedRepository.findById(id).get());
		return "content/base/edit";
	}
	
//	UPDATE DISINI MASIH PAKAI GET
	@RequestMapping("/base/update")
	public String update(@RequestParam String id, 
			@RequestParam String name, 
			@RequestParam String desc, 
			@RequestParam Double price, 
			@RequestParam String img) {
		Optional<BasedModel> base = basedRepository.findById(id);
		base.get().setName(name);
		base.get().setDesc(desc);
		base.get().setImg(img);
		base.get().setPrice(price);
		basedRepository.save(base.get());
		
		return "redirect:/base/show/" + base.get().getId();
	}
}
