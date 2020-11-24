package org.masbas.idvn.controllers;

import org.masbas.idvn.services.LaporanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
	@Autowired
	LaporanService laporanService;
	

}
