package com.tenor.tsf.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.Departement;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {

	
}
