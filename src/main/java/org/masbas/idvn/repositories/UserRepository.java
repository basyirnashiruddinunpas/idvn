package org.masbas.idvn.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.masbas.idvn.models.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserDao, String> {
	
	@Query("{'email': ?0}")
	public UserDao findByEmail(String email);
	
	@Query("{'name': ?0}")
	public UserDao findByName(String name);
	
	@Query("{'id': ?0}")
	public UserDao findByIdUser(String id);
	
	@Query("{'email': ?0, 'password': ?1}")
	public UserDao findByEmailPassword(String email, String password);
	
	@Query("{'roles': ['ROLE_VENDOR']}")
	public List<UserDao> findAllVendor();
	
	@Query("{'auditor.$id': ?0}")
	public List<UserDao> findVendorByAuditor(ObjectId id);
	
}
