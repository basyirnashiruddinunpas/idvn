package org.masbas.idvn.services;

import java.util.List;
import java.util.Optional;

import org.masbas.idvn.models.LaporanModel;
import org.masbas.idvn.repositories.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class LaporanService implements ILaporanService {

	@Autowired
	LaporanRepository laporanRepository;
	
	@Override
	public void save(LaporanModel laporan) {
		laporanRepository.save(laporan);
	}
	
	public Optional<LaporanModel> findById(String id) {
		return laporanRepository.findById(id);
	}
	
	public LaporanModel findLatestLaporan() {
		return laporanRepository.findLatestLaporan(Sort.by(Sort.Direction.DESC, "code")).get(0);
	}
	
	public LaporanModel findByCode(String code) {
		return laporanRepository.findByCode(code);
	}
	
	public Long findTotalKerentanan() {
		return laporanRepository.findTotalKerentanan();
	}
	

}
