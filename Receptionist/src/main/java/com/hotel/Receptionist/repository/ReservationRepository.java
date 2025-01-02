package com.hotel.Receptionist.repository;

import com.hotel.Receptionist.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>
{
    //
}