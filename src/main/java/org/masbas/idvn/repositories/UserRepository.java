package org.masbas.idvn.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{'email': ?0}")
	public User findByEmail(String email);
	
	@Query("{'name': ?0}")
	public User findByName(String name);
	
	@Query("{'id': ?0}")
	public User findByIdUser(String id);
	
	@Query("{'email': ?0, 'password': ?1}")
	public User findByEmailPassword(String email, String password);
	
	@Query("{'roles': ['ROLE_VENDOR']}")
	public List<User> findAllVendor();
	
	@Query("{'auditor.$id': ?0}")
	public List<User> findVendorByAuditor(ObjectId id);
	
}
