package com.tenor.tsf.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
