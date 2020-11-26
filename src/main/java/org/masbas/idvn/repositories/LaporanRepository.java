package org.masbas.idvn.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.Laporan;
import org.masbas.idvn.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LaporanRepository extends CrudRepository<Laporan, String> {
	
	@Query("{}")
	public List<Laporan> findLatestLaporan(Sort sort);
	
	@Query("{'code': ?0}")
	public Laporan findByCode(String code);
	
	@Query(value="{}", count = true)
	public Long findTotalKerentanan();

	@Query("{}")
	public List<Laporan> findLaporanPageable(Pageable pageable);
	
	@Query("{}")
	public Page<Laporan> findAllLaporanPageable(Pageable pageable);
	
	@Query("{'status': ?0}")
	public List<Laporan> findLaporanByStatusPageable(String status, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}}")
	public List<Laporan> findLaporanTrackingPageable(Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}}")
	public Page<Laporan> findAllLaporanTrackingPageable(Pageable pageable);
	
	@Query("{'status': 'INVALID'}")
	public List<Laporan> findLaporanInvalidPageable(Pageable pageable);
	
	@Query("{'status': 'INVALID'}")
	public Page<Laporan> findAllLaporanInvalidPageable(Pageable pageable);
	
	// UNTUK USER NOTIFIER
	@Query("{'status': ?0, 'createdBy.$id': ?1)}")
	public List<Laporan> findLaporanByStatusPageableCurrUser(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'createdBy.$id': ?0}")
	public List<Laporan> findLaporanTrackingPageableCurrUser(ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'createdBy.$id': ?0}")
	public Page<Laporan> findAllLaporanTrackingPageableCurrUser(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'createdBy.$id': ?0}")
	public List<Laporan> findLaporanInvalidPageableCurrUser(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'createdBy.$id': ?0}")
	public Page<Laporan> findAllLaporanInvalidPageableCurrUser(ObjectId id, Pageable pageable);

	@Query("{'createdBy.$id': ?0}")
	public List<Laporan> findLaporanPageableCurrUser(ObjectId id, Pageable pageable);
	
	@Query("{'createdBy.$id': ?0}")
	public Page<Laporan> findAllLaporanPageableCurrUser(ObjectId id, Pageable pageable);
	
	// UNTUK USER VENDOR
	@Query("{'status': ?0, 'vendor.$id': ?1)}")
	public List<Laporan> findLaporanByStatusPageableVendor(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public List<Laporan> findLaporanTrackingPageableVendor(ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanTrackingPageableVendor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public List<Laporan> findLaporanInvalidPageableVendor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanInvalidPageableVendor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public List<Laporan> findLaporanPageableVendor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanPageableVendor(ObjectId id, Pageable pageable);
	
	// UNTUK USER AUDITOR
	@Query("{'status': ?0, 'vendor.$id': ?1)}")
	public List<Laporan> findLaporanByStatusPageableAuditor(String status, ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public List<Laporan> findLaporanTrackingPageableAuditor(ObjectId id, Pageable pageable);
	
	@Query("{'status': {'$in':['VALID', 'PROCESSED', 'PATCHED']}, 'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanTrackingPageableAuditor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public List<Laporan> findLaporanInvalidPageableAuditor(ObjectId id, Pageable pageable);
	
	@Query("{'status': 'INVALID', 'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanInvalidPageableAuditor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public List<Laporan> findLaporanPageableAuditor(ObjectId id, Pageable pageable);

	@Query("{'vendor.$id': ?0}")
	public Page<Laporan> findAllLaporanPageableAuditor(ObjectId id, Pageable pageable);
	

}
