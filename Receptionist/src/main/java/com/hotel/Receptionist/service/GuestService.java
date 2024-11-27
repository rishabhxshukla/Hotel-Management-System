package com.hotel.Receptionist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.repository.GuestRepository;

@Service
public class GuestService
{
	
	@Autowired
    private GuestRepository repo;
    
	
	// Will list all the guest name present in DB
	public List<Guest> showAllGuest()
	{
		return repo.findAll();
	}
    
	// Will give guest by there id
	public Optional<Guest> showGuestById(int id)
	{
		return repo.findById(id);
	}
	
	// Will add guest
	public void addGuest(Guest guest)
	{
		repo.save(guest);
	}
	
	//Will update guest
	public void updateGuest(Guest guest)
	{
	   repo.save(guest);
	}
	
	//Will delete guest by id
	public void deleteGuest(int id)
	{
		repo.deleteById(id);
	}
}