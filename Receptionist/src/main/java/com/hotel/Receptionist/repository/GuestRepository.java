package com.hotel.Receptionist.repository;

import com.hotel.Receptionist.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer>
{
    //
}