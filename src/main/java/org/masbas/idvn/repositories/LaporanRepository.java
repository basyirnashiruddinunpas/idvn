package org.masbas.idvn.repositories;

import java.util.List;

import org.masbas.idvn.models.LaporanModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LaporanRepository extends CrudRepository<LaporanModel, String> {
	
	@Query("{}")
	public List<LaporanModel> findLatestLaporan(Sort sort);
	
	@Query("{'code': ?0}")
	public LaporanModel findByCode(String code);
	
	@Query(value="{}", count = true)
	public Long findTotalKerentanan();

}
