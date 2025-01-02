package com.hotel.Receptionist;

import com.hotel.Receptionist.exception.GuestNotFoundException;
import com.hotel.Receptionist.exception.NoGuestsFoundException;
import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.repository.GuestRepository;
import com.hotel.Receptionist.service.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;  // Mock the repository

    @InjectMocks
    private GuestService guestService;  // Inject the mocked repository into the service

    private Guest guest;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        
        // Initialize a Guest object to use in tests
        guest = new Guest();
        guest.setGuestId(1);
        guest.setName("John Doe");
        guest.setGender("Male");
        guest.setEmail("john@example.com");
        guest.setPhoneNo("123456789");
        guest.setCompany("ABC Corp");
        guest.setAddress("123 Street");
    }

    @Test
    void testAddGuest() {
        // Mock the save method of the repository to return the guest
        when(guestRepository.save(any(Guest.class))).thenReturn(guest);

        // Call the service method which uses the mocked repository
        guestService.addGuest(guest);

        // Verify that the save method was called exactly once
        verify(guestRepository, times(1)).save(any(Guest.class));
    }

    @Test
    void testShowAllGuests_WhenGuestsExist() {
        // Mock the repository to return a list of guests
        when(guestRepository.findAll()).thenReturn(List.of(guest));

        // Call the service method
        List<Guest> guests = guestService.showAllGuests();

        // Assert that the list is not null and contains one guest
        assertNotNull(guests);
        assertEquals(1, guests.size());

        // Verify the repository's findAll method was called once
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testShowAllGuests_WhenNoGuestsExist() {
        // Mock the repository to return an empty list
        when(guestRepository.findAll()).thenReturn(List.of());

        // Assert that NoGuestsFoundException is thrown
        assertThrows(NoGuestsFoundException.class, () -> guestService.showAllGuests());

        // Verify the repository's findAll method was called once
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testShowAllGuests_WhenGuestsExist1() {
        // Mock the repository to return a list of guests
        when(guestRepository.findAll()).thenReturn(List.of(guest));

        // Call the service method
        List<Guest> guests = guestService.showAllGuests();

        // Assert that the list is not null and contains one guest
        assertNotNull(guests);
        assertEquals(1, guests.size());

        // Verify the repository's findAll method was called once
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    void testShowAllGuests_WhenNoGuestsExist1() {
        // Mock the repository to return an empty list
        when(guestRepository.findAll()).thenReturn(List.of());

        // Call the service method and assert that NoGuestsFoundException is thrown
        NoGuestsFoundException exception = assertThrows(NoGuestsFoundException.class, () -> guestService.showAllGuests());
        
        // Assert the exception message (Optional)
        assertEquals("Table is empty. No guests found in the database!", exception.getMessage());

        // Verify the repository's findAll method was called once
        verify(guestRepository, times(1)).findAll();
    }

    
    @Test
    void testUpdateGuest_WhenGuestExists() {
        // Mock the repository to return the guest when searching by id
        when(guestRepository.findById(1)).thenReturn(Optional.of(guest));
        
        // Modify the guest's details
        guest.setName("John Smith");
        when(guestRepository.save(any(Guest.class))).thenReturn(guest); // Mock the save method
        
        // Call the service method to update the guest
        guestService.updateGuest(1, guest);

        // Verify that the save method was called once
        verify(guestRepository, times(1)).save(any(Guest.class));
    }

    @Test
    void testUpdateGuest_WhenGuestNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(guestRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that GuestNotFoundException is thrown
        assertThrows(GuestNotFoundException.class, () -> guestService.updateGuest(1, guest));

        // Verify the repository's findById method was called once
        verify(guestRepository, times(1)).findById(1);
    }

    
    
    
    
    @Test
    void testDeleteGuest_WhenGuestExists() {
        // Mock the repository to return the guest when searching by id
        when(guestRepository.findById(1)).thenReturn(Optional.of(guest));

        // Call the service method to delete the guest
        guestService.deleteGuest(1);

        // Verify that the deleteById method was called once
        verify(guestRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteGuest_WhenGuestNotFound() {
        // Mock the repository to return an empty Optional when the guest is not found
        when(guestRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that GuestNotFoundException is thrown
        assertThrows(GuestNotFoundException.class, () -> guestService.deleteGuest(1));

        // Verify the repository's findById method was called once
        verify(guestRepository, times(1)).findById(1);
    }
}
