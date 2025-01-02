package com.hotel.Receptionist.service;

import com.hotel.Receptionist.exception.GuestNotFoundException;
import com.hotel.Receptionist.exception.NoGuestsFoundException;
import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService
{
	@Autowired
    private GuestRepository repo;

	public List<Guest> showAllGuests()
	{
		//Fetch all guests
		List<Guest> guests = repo.findAll();

		//Check if list is empty
		if (guests.isEmpty()) {
			throw new NoGuestsFoundException("Table is empty. No guests found in the database!");
		}

		return guests;
	}

	public Guest showGuestById(int id)
	{
		//Check if guest is not present
		if (!repo.findById(id).isPresent()) {
			throw new GuestNotFoundException("Guest with ID " + id + " not found!");
		}

		return repo.findById(id).get();
	}

	public void addGuest(Guest guest)
	{
		repo.save(guest);
	}

	public void updateGuest(int id, Guest requestBody)
	{
		//Check if guest is not present
		if (!repo.findById(id).isPresent()) {
			throw new GuestNotFoundException("Guest with ID " + id + " not found!");
        }

		//Fetch the guest
        Guest guest = repo.findById(id).get();

		//Change values coming from request body
        guest.setName(requestBody.getName());
        guest.setGender(requestBody.getGender());
        guest.setEmail(requestBody.getEmail());
        guest.setPhoneNo(requestBody.getPhoneNo());
        guest.setCompany(requestBody.getCompany());
        guest.setAddress(requestBody.getAddress());

		//Save the guest
        repo.save(guest);
    }

	public void deleteGuest(int id)
	{
		//Check if guest is not present
		if (!repo.findById(id).isPresent()) {
			throw new GuestNotFoundException("Guest with ID " + id + " not found!");
		}

		repo.deleteById(id);
	}
}