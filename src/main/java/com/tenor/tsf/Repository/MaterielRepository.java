package com.tenor.tsf.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.Materiel;

@Repository
public interface MaterielRepository extends CrudRepository<Materiel, Long>  {

}
