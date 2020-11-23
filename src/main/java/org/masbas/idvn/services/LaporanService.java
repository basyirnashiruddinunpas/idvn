package org.masbas.idvn.services;

import org.masbas.idvn.models.LaporanModel;
import org.masbas.idvn.repositories.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaporanService implements ILaporanService {

	@Autowired
	LaporanRepository laporanRepository;
	
	@Override
	public void save(LaporanModel laporan) {
		laporanRepository.save(laporan);
	}
	

}
