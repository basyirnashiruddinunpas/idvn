package org.masbas.idvn.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.LaporanDao;
import org.masbas.idvn.models.UserDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LaporanRepository extends CrudRepository<LaporanDao, String> {
	
	@Query("{}")
	public List<LaporanDao> findLatestLaporan(Sort sort);
	
	@Query("{'code': ?0}")
	public LaporanDao findByCode(String code);
	
	@Query(value="{}", count = true)
	public Long findTotalKerentanan();

	@Query("{}")
	public List<LaporanDao> findLaporanPageable(Pageable pageable);
	
	@Query("{'status': ?0}")
	public List<LaporanDao> findLaporanByStatusPageable(String status, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}}")
	public List<LaporanDao> findLaporanTrackingPageable(Pageable pageable);
	
	@Query("{'status': 'INVALID'}")
	public List<LaporanDao> findLaporanInvalidPageable(Pageable pageable);
	
	// UNTUK USER NOTIFIER
	@Query("{'status': ?0, 'createdBy.$id': ?1)}")
	public List<LaporanDao> findLaporanByStatusPageableCurrUser(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'createdBy.$id': ?0}")
	public List<LaporanDao> findLaporanTrackingPageableCurrUser(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'createdBy.$id': ?0}")
	public List<LaporanDao> findLaporanInvalidPageableCurrUser(ObjectId id, Pageable pageable);

	@Query("{'createdBy.$id': ?0}")
	public List<LaporanDao> findLaporanPageableCurrUser(ObjectId id, Pageable pageable);
	
	// UNTUK USER VENDOR
	@Query("{'status': ?0, 'vendor.$id': ?1)}")
	public List<LaporanDao> findLaporanByStatusPageableVendor(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanTrackingPageableVendor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanInvalidPageableVendor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanPageableVendor(ObjectId id, Pageable pageable);
	
	// UNTUK USER AUDITOR
	@Query("{'status': ?0, 'vendor.$id': ?1)}")
	public List<LaporanDao> findLaporanByStatusPageableAuditor(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanTrackingPageableAuditor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanInvalidPageableAuditor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public List<LaporanDao> findLaporanPageableAuditor(ObjectId id, Pageable pageable);
	

}
