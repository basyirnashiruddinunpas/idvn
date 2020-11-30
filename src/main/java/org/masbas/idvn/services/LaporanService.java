package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.Laporan;
import org.masbas.idvn.models.User;
import org.masbas.idvn.repositories.LaporanRepository;
import org.masbas.idvn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
	public void save(Laporan laporan) {
		laporanRepository.save(laporan);
	}
	
	public Optional<Laporan> findById(String id) {
		return laporanRepository.findById(id);
	}
	
	public Laporan findLatestLaporan() {
		return laporanRepository.findLatestLaporan(Sort.by(Sort.Direction.DESC, "createdTimeStamp")).get(0);
	}
	
	public Laporan findByCode(String code) {
		return laporanRepository.findByCode(code);
	}
	
	public Long findTotalKerentanan() {
		return laporanRepository.findTotalKerentanan();
	}

	public List<Laporan> findLaporanPageable(Pageable pageable) {
		return laporanRepository.findLaporanPageable(pageable);
	}

	public Page<Laporan> findAllLaporanPageable(Pageable pageable) {
		return laporanRepository.findAllLaporanPageable(pageable);
	}
	
	public List<Laporan> findLaporanByStatusPageable(String status, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageable(status, pageable);
	}
	
	public List<Laporan> findLaporanTrackingPageable(Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageable(pageable);
	}
	
	public Page<Laporan> findAllLaporanTrackingPageable(Pageable pageable) {
		return laporanRepository.findAllLaporanTrackingPageable(pageable);
	}

	public Page<Laporan> findAllLaporanInvalidPageable(Pageable pageable) {
		return laporanRepository.findAllLaporanInvalidPageable(pageable);
	}

	public List<Laporan> findLaporanPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findLaporanPageableCurrUser(new ObjectId(user.getId()), pageable);
	}

	public Page<Laporan> findAllLaporanPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanPageableCurrUser(new ObjectId(user.getId()), pageable);
	}

	public List<Laporan> findLaporanByStatusPageableCurrUser(String status, User user, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageableCurrUser(status, new ObjectId(user.getId()), pageable);
	}
	
	public List<Laporan> findLaporanTrackingPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	public Page<Laporan> findAllLaporanTrackingPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanTrackingPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	public List<Laporan> findLaporanInvalidPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findLaporanInvalidPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	public Page<Laporan> findAllLaporanInvalidPageableCurrUser(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanInvalidPageableCurrUser(new ObjectId(user.getId()), pageable);
	}
	
	// VENDOR

	public List<Laporan> findLaporanPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findLaporanPageableVendor(new ObjectId(user.getId()), pageable);
	}

	public Page<Laporan> findAllLaporanPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanPageableVendor(new ObjectId(user.getId()), pageable);
	}

	public List<Laporan> findLaporanByStatusPageableVendor(String status, User user, Pageable pageable) {
		return laporanRepository.findLaporanByStatusPageableVendor(status, new ObjectId(user.getId()), pageable);
	}
	
	public List<Laporan> findLaporanTrackingPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findLaporanTrackingPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	public Page<Laporan> findAllLaporanTrackingPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanTrackingPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	public List<Laporan> findLaporanInvalidPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findLaporanInvalidPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	public Page<Laporan> findAllLaporanInvalidPageableVendor(User user, Pageable pageable) {
		return laporanRepository.findAllLaporanInvalidPageableVendor(new ObjectId(user.getId()), pageable);
	}
	
	// AUDITOR

	public List<Laporan> findLaporanPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findLaporanPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}

	public Page<Laporan> findAllLaporanPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findAllLaporanPageableAuditor(new ObjectId(vendor.getId()), pageable).getContent();
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		Page<Laporan> pager = new PageImpl<Laporan>(
				laporans.subList((int) pageable.getOffset(), 
						(int)((pageable.getPageSize() + pageable.getOffset()) > laporans.size() ? laporans.size() : (pageable.getPageSize() + pageable.getOffset()))
								), pageable, laporans.size());
		return pager;
	}

	public List<Laporan> findLaporanByStatusPageableAuditor(String status, User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findLaporanByStatusPageableAuditor(status, new ObjectId(vendor.getId()), pageable);
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}
	
	public List<Laporan> findLaporanTrackingPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		
		for (User vendor : vendors) {
			List<Laporan> lapors =  laporanRepository.findLaporanTrackingPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}

	public Page<Laporan> findAllLaporanTrackingPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findAllLaporanTrackingPageableAuditor(new ObjectId(vendor.getId()), pageable).getContent();
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		Page<Laporan> pager = new PageImpl<Laporan>(
				laporans.subList((int) pageable.getOffset(), 
						(int)((pageable.getPageSize() + pageable.getOffset()) > laporans.size() ? laporans.size() : (pageable.getPageSize() + pageable.getOffset()))
								), pageable, laporans.size());
		return pager;
	}
	
	public List<Laporan> findLaporanInvalidPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findLaporanInvalidPageableAuditor(new ObjectId(vendor.getId()), pageable);
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		return laporans;
	}

	public Page<Laporan> findAllLaporanInvalidPageableAuditor(User user, Pageable pageable) {
		List<User> vendors = userRepository.findVendorByAuditor(new ObjectId(user.getId()));
		List<Laporan> laporans = new ArrayList<Laporan>();
		for (User vendor : vendors) {
			List<Laporan> lapors = laporanRepository.findAllLaporanInvalidPageableAuditor(new ObjectId(vendor.getId()), pageable).getContent();
			for (Laporan lapor : lapors) {
				laporans.add(lapor);
			}
		}
		Page<Laporan> pager = new PageImpl<Laporan>(
				laporans.subList((int) pageable.getOffset(), 
						(int)((pageable.getPageSize() + pageable.getOffset()) > laporans.size() ? laporans.size() : (pageable.getPageSize() + pageable.getOffset()))
								), pageable, laporans.size());
		return pager;
	}

}
