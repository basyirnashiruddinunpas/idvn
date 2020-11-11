package org.masbas.idvn.controllers;

import java.util.Optional;

import org.masbas.idvn.models.BaseModel;
import org.masbas.idvn.repositories.BaseRepository;
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
public class BaseController {
	
	@Autowired
	BaseRepository baseRepository;
	
	@RequestMapping("/base")
	public String base(Model model) {
		model.addAttribute("bases", baseRepository.findAll());
		return "content/base/base";
	}
	
	@RequestMapping("/create")
	public String create(Model model) {
		return "content/base/create";
	}

//	@RequestMapping(value = "/save"
//			, method = RequestMethod.POST
//            , produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//            , consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
	@PostMapping("/save")
	public String save(@ModelAttribute BaseModel baseModel) {
		BaseModel base = new BaseModel();
		base.setName(baseModel.getName());
		base.setDesc(baseModel.getDesc());
		base.setImg(baseModel.getImg());
		base.setPrice(baseModel.getPrice());
		baseRepository.save(base);
		
		return "redirect:/show/" + base.getId();
	}
	
	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("base", baseRepository.findById(id).get());
		return "content/base/show";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<BaseModel> base = baseRepository.findById(id);
		baseRepository.delete(base.get());
		return "redirect:/base";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("base", baseRepository.findById(id).get());
		return "content/base/edit";
	}
	
//	UPDATE DISINI MASIH PAKAI GET
	@RequestMapping("/update")
	public String update(@RequestParam String id, 
			@RequestParam String name, 
			@RequestParam String desc, 
			@RequestParam Double price, 
			@RequestParam String img) {
		Optional<BaseModel> base = baseRepository.findById(id);
		base.get().setName(name);
		base.get().setDesc(desc);
		base.get().setImg(img);
		base.get().setPrice(price);
		baseRepository.save(base.get());
		
		return "redirect:/show/" + base.get().getId();
	}
}
