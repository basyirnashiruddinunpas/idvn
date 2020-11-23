package org.masbas.idvn.repositories;
import org.masbas.idvn.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserModel, String> {
	
	@Query("{'email': ?0}")
	public UserModel findByEmail(String email);
	
	@Query("{'name': ?0}")
	public UserModel findByName(String name);
	
	@Query("{'id': ?0}")
	public UserModel findByIdUser(String id);
	
	@Query("{'email': ?0, 'password': ?1}")
	public UserModel findByEmailPassword(String email, String password);
	
}
