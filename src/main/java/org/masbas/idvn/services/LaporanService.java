package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.LaporanDao;
import org.masbas.idvn.models.UserDao;
import org.masbas.idvn.repositories.LaporanRepository;
import org.masbas.idvn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LaporanService implements ILaporanService {

	@Autowired
	LaporanRepository laporanRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void save(LaporanDao laporan) {
		laporanRepository.save(laporan);
	}
	
	public Optional<LaporanDao> findById(String id) {
		return laporanRepository.findById(id);
	}
	
	public LaporanDao findLatestLaporan() {
		return laporanRepository.findLatestLaporan(Sort.by(Sort.Direction.DESC, "code")).get(0);
	}
	
	public LaporanDao findByCode(String code) {
		return laporanRepository.findByCode(code);
	}
	
	public Long findTotalKerentanan() {
		return laporanRepository.findTotalKerentanan();
	}

	public List<LaporanDao> findLaporanPageable(Pageable pageable) {
		return laporanRepository.findLaporanPageable(pageable);
	}
	
	public List<LaporanDao> findLaporanByStatusPageable(String status, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageable(status, pageable);
	}
	
	public List<LaporanDao> findLaporanTrackingPageable(Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageable(pageable);
	}

	public List<LaporanDao> findLaporanPageableCurrUser(UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanPageableCurrUser(new ObjectId(user.getId()), pageable);
	}

	public List<LaporanDao> findLaporanByStatusPageableCurrUser(String status, UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageableCurrUser(status, new ObjectId(user.getId()), pageable);
	}
	
	public List<LaporanDao> findLaporanTrackingPageableCurrUser(UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	public List<LaporanDao> findLaporanInvalidPageableCurrUser(UserDao user, Pageable pageable) {
		;
		return laporanRepository.findLaporanInvalidPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	// VENDOR

	public List<LaporanDao> findLaporanPageableVendor(UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanPageableVendor(new ObjectId(user.getId()), pageable);
	}

	public List<LaporanDao> findLaporanByStatusPageableVendor(String status, UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageableVendor(status, new ObjectId(user.getId()), pageable);
	}
	
	public List<LaporanDao> findLaporanTrackingPageableVendor(UserDao user, Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	public List<LaporanDao> findLaporanInvalidPageableVendor(UserDao user, Pageable pageable) {
		;
		return laporanRepository.findLaporanInvalidPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	// AUDITOR

	public List<LaporanDao> findLaporanPageableAuditor(UserDao user, Pageable pageable) {
		List<UserDao> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<LaporanDao> laporans = new ArrayList<LaporanDao>();
		
		for (UserDao vendor : vendors) {
			List<LaporanDao> lapors = laporanRepository.findLaporanPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (LaporanDao lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}

	public List<LaporanDao> findLaporanByStatusPageableAuditor(String status, UserDao user, Pageable pageable) {
		List<UserDao> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<LaporanDao> laporans = new ArrayList<LaporanDao>();
		
		for (UserDao vendor : vendors) {
			List<LaporanDao> lapors = laporanRepository.findLaporanByStatusPageableAuditor(status, new ObjectId(vendor.getId()), pageable);
			for (LaporanDao lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}
	
	public List<LaporanDao> findLaporanTrackingPageableAuditor(UserDao user, Pageable pageable) {
		List<UserDao> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<LaporanDao> laporans = new ArrayList<LaporanDao>();
		
		for (UserDao vendor : vendors) {
			List<LaporanDao> lapors =  laporanRepository.findLaporanTrackingPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (LaporanDao lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}
	
	public List<LaporanDao> findLaporanInvalidPageableAuditor(UserDao user, Pageable pageable) {
		List<UserDao> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<LaporanDao> laporans = new ArrayList<LaporanDao>();
		
		for (UserDao vendor : vendors) {
			List<LaporanDao> lapors = laporanRepository.findLaporanInvalidPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (LaporanDao lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}

}
