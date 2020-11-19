package org.masbas.idvn.repositories;
import org.springframework.data.repository.CrudRepository;
import org.masbas.idvn.models.BasedModel;

public interface BasedRepository extends CrudRepository<BasedModel, String> {
	
	@Override
	public void delete(BasedModel base);

}
