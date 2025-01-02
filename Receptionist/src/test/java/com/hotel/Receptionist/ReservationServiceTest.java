package com.hotel.Receptionist;

import com.hotel.Receptionist.dto.Room;
import com.hotel.Receptionist.exception.NoReservationsFoundException;
import com.hotel.Receptionist.exception.ReservationNotFoundException;
import com.hotel.Receptionist.feign.RoomFeignClient;
import com.hotel.Receptionist.model.Reservation;
import com.hotel.Receptionist.repository.ReservationRepository;
import com.hotel.Receptionist.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository; // Mock the reservation repository

    @Mock
    private RoomFeignClient roomFeignClient; // Mock the RoomFeignClient for fetching room details

    @InjectMocks
    private ReservationService reservationService; // Inject the mocks into the service

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Create a Reservation object to use in tests
        reservation = new Reservation();
        reservation.setRoomId(101);
        reservation.setMembers(2);
        reservation.setCheckInDate("2024-12-10");
        reservation.setCheckOutDate("2024-12-15");
        reservation.setNoOfNights(5);
    }

    @Test
    void testShowAllReservations_WhenReservationsExist() {
        // Mock the repository to return a list of reservations
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        // Call the service method
        List<Reservation> reservations = reservationService.showAllReservations();

        // Assert the list is not null and contains the reservation
        assertNotNull(reservations);
        assertEquals(1, reservations.size());

        // Verify that the findAll method was called once
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testShowAllReservations_WhenNoReservationsExist() {
        // Mock the repository to return an empty list
        when(reservationRepository.findAll()).thenReturn(List.of());

        // Assert that NoReservationsFoundException is thrown
        assertThrows(NoReservationsFoundException.class, () -> reservationService.showAllReservations());

        // Verify that findAll was called once
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testShowReservationById_WhenReservationExists() {
        // Mock the repository to return the reservation when searching by id
        when(reservationRepository.findById(101)).thenReturn(Optional.of(reservation));

        // Call the service method
        Reservation foundReservation = reservationService.showReservationById(101);

        // Assert the found reservation is not null and matches the expected reservation
        assertNotNull(foundReservation);
        assertEquals(101, foundReservation.getRoomId());

        // Verify that findById was called once
        verify(reservationRepository, times(2)).findById(101);
    }

    @Test
    void testShowReservationById_WhenReservationNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(reservationRepository.findById(101)).thenReturn(Optional.empty());

        // Assert that ReservationNotFoundException is thrown
        assertThrows(ReservationNotFoundException.class, () -> reservationService.showReservationById(101));

        // Verify that findById was called once
        verify(reservationRepository, times(1)).findById(101);
    }

    @Test
    void testAddReservation() {
        // Mock the repository's save method to return the reservation
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Call the service method to add the reservation
        reservationService.addReservation(reservation);

        // Verify that the save method was called once
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testDeleteReservation_WhenReservationExists() {
        // Mock the repository to return the reservation when searching by id
        when(reservationRepository.findById(101)).thenReturn(Optional.of(reservation));

        // Call the service method to delete the reservation
        reservationService.deleteReservation(101);

        // Verify that the deleteById method was called once
        verify(reservationRepository, times(1)).deleteById(101);
    }

    @Test
    void testDeleteReservation_WhenReservationNotFound() {
        // Mock the repository to return an empty Optional when the reservation is not found
        when(reservationRepository.findById(101)).thenReturn(Optional.empty());

        // Assert that ReservationNotFoundException is thrown
        assertThrows(ReservationNotFoundException.class, () -> reservationService.deleteReservation(101));

        // Verify that findById was called once
        verify(reservationRepository, times(1)).findById(101);
    }

    @Test
    void testCalculateBill_WhenReservationExists() {
        // Step 1: Mock the Reservation repository to return a valid Reservation object for ID 101
        Reservation reservation = new Reservation();
        reservation.setRoomId(101);  // Room ID linked to this reservation
        reservation.setMembers(2);    // 2 members in the reservation
        reservation.setNoOfNights(5); // 5 nights stay
        
        when(reservationRepository.findById(101)).thenReturn(Optional.of(reservation));

        // Step 2: Mock the Room Feign client to return a Room object with a price for the roomId
        Room room = new Room();
        room.setRoomId(101);      // Room ID must match the reservation's room ID
        room.setPrice(100);       // Assume room price is 100 per night
        room.setAvailable("Yes"); // Room is available
        
        when(roomFeignClient.showRoomById(101)).thenReturn(room);

        // Step 3: Call the service method to calculate the bill
        int bill = reservationService.calculateBill(101);

        // Step 4: Calculate the expected bill: 2 members * 5 nights * 100 price per night = 1000
        assertEquals(1000, bill);

        // Step 5: Verify that the findById method of the reservationRepository was called twice
        verify(reservationRepository, times(2)).findById(101);


        // Step 6: Verify that the showRoomById method of the roomFeignClient was called once
        verify(roomFeignClient, times(1)).showRoomById(101);
    }




}
