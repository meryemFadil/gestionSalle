package com.tenor.tsf.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Long>{

}
