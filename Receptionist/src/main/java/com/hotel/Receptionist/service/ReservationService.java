package com.hotel.Receptionist.service;

import com.hotel.Receptionist.dto.Room;
import com.hotel.Receptionist.exception.GuestNotFoundException;
import com.hotel.Receptionist.exception.NoReservationsFoundException;
import com.hotel.Receptionist.exception.ReservationNotFoundException;
import com.hotel.Receptionist.feign.RoomFeignClient;
import com.hotel.Receptionist.model.Reservation;
import com.hotel.Receptionist.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService
{
    @Autowired
    private ReservationRepository repo;

    @Autowired
    private RoomFeignClient roomFeign;

    public List<Reservation> showAllReservations()
    {
        //Fetch all reservations
        List<Reservation> reservations = repo.findAll();

        //Check if list is empty
        if (reservations.isEmpty()) {
            throw new NoReservationsFoundException("Table is empty. No Reservations found!");
        }

        return reservations;
    }

    public Reservation showReservationById(int id)
    {
        //Check if guest is not present
        if (!repo.findById(id).isPresent()) {
            throw new GuestNotFoundException("Reservation with ID " + id + " not found!");
        }

        return repo.findById(id).get();
    }

    public ResponseEntity<String> addReservation(Reservation reservation)
    {
        int roomId = reservation.getRoomId();
        String checkInDate = reservation.getCheckInDate();
        String checkOutDate = reservation.getCheckOutDate();

        //Check room availability
        String availability = roomFeign.checkRoomAvailability(roomId);

        //If room is not available
        if (availability.equals("No"))
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Room is not available for the selected dates!");
        }

        //Save the reservation if the room is available
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        repo.save(reservation);

        //Update room availability to "No" after booking
        roomFeign.updateRoomAvailability(roomId, "No");

        return ResponseEntity.ok("Reservation added successfully!");
    }

    public void deleteReservation(int id)
    {
        //Check if reservation does not exist
        if (!repo.existsById(id)) {
            throw new ReservationNotFoundException("Reservation with ID " + id + " not found.");
        }

        Reservation reservation = repo.findById(id).get();

        roomFeign.updateRoomAvailability(reservation.getRoomId(), "Yes");

        repo.deleteById(id);
    }

    public int calculateBill(int id)
    {
        Reservation reservation = repo.findById(id).get();
        Room room = roomFeign.showRoomById(reservation.getRoomId());

        int bill = reservation.getMembers() *
                   reservation.getNoOfNights() *
                   room.getPrice();

        return bill;
    }
}