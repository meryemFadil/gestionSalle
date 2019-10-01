package com.tenor.tsf.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenor.tsf.dao.entity.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	
	

}
