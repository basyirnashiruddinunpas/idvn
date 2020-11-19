package org.masbas.idvn.repositories;
import org.masbas.idvn.models.UserModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, String> {
	
	@Query("{email: ?0}")
	public UserModel findByEmail(String email);
	
	@Query("{name: ?0}")
	public UserModel findByName(String name);
	
}
