package com.tenor.tsf.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.Reclamation;

@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Long>{

}
