package org.masbas.idvn.repositories;
import org.springframework.data.repository.CrudRepository;
import org.masbas.idvn.models.BaseModel;

public interface BaseRepository extends CrudRepository<BaseModel, String> {
	
	@Override
	public void delete(BaseModel base);

}
