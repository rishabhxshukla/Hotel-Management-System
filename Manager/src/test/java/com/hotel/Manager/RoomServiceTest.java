package com.hotel.Manager;

import com.hotel.Manager.exception.NoRoomsFoundException;
import com.hotel.Manager.exception.RoomNotFoundException;
import com.hotel.Manager.model.Room;
import com.hotel.Manager.repository.RoomRepository;
import com.hotel.Manager.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;  // Mock the repository

    @InjectMocks
    private RoomService roomService;  // Inject the mocked repository into the service

    private Room room;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize a Room object to use in tests
        room = new Room();
        room.setRoomId(1);
        room.setType("Deluxe");
        room.setPrice(200);
        room.setAvailable("Yes");
    }

    @Test
    void testShowAllRooms_WhenNoRoomsFound() {
        // Mock the repository to return an empty list
        when(roomRepository.findAll()).thenReturn(Arrays.asList());

        // Assert that NoRoomsFoundException is thrown
        assertThrows(NoRoomsFoundException.class, () -> roomService.showAllRooms());

        // Verify the repository's findAll method was called once
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testShowAllRooms_WhenRoomsFound() {
        // Mock the repository to return a list of rooms
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));

        // Call the service method
        List<Room> result = roomService.showAllRooms();

        // Assert that the list is not null, contains one room, and check the room details
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deluxe", result.get(0).getType());

        // Verify the repository's findAll method was called once
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testShowRoomById_WhenRoomNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that RoomNotFoundException is thrown
        assertThrows(RoomNotFoundException.class, () -> roomService.showRoomById(1));

        // Verify the repository's findById method was called once
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testShowRoomById_WhenRoomFound() {
        // Mock the repository to return the room when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));

        // Call the service method
        Room result = roomService.showRoomById(1);

        // Assert the room's details
        assertNotNull(result);
        assertEquals(1, result.getRoomId());
        assertEquals("Deluxe", result.getType());

        // Verify the repository's findById method was called twice
        verify(roomRepository, times(2)).findById(1);
    }

    @Test
    void testAddRoom() {
        // Mock the save method of the repository to return the room
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // Call the service method to add the room
        roomService.addRoom(room);

        // Verify the repository's save method was called once
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void testUpdateRoom_WhenRoomNotFound() {
        // Prepare an updated room object
        Room updatedRoom = new Room();
        updatedRoom.setType("Suite");
        updatedRoom.setPrice(300);
        updatedRoom.setAvailable("No");

        // Mock the repository to return an empty Optional when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that RoomNotFoundException is thrown
        assertThrows(RoomNotFoundException.class, () -> roomService.updateRoom(1, updatedRoom));

        // Verify the repository's findById method was called once
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateRoom_WhenRoomFound() {
        // Prepare an updated room object
        Room updatedRoom = new Room();
        updatedRoom.setType("Suite");
        updatedRoom.setPrice(300);
        updatedRoom.setAvailable("No");

        // Mock the repository to return the existing room when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));

        // Mock the repository save method to return the updated room
        when(roomRepository.save(any(Room.class))).thenReturn(updatedRoom);

        // Call the service method to update the room
        roomService.updateRoom(1, updatedRoom);

        // Verify that the save method was called once
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void testDeleteRoom_WhenRoomNotFound() {
        // Mock the repository to return an empty Optional when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.empty());

        // Assert that RoomNotFoundException is thrown
        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoom(1));

        // Verify the repository's findById method was called once
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteRoom_WhenRoomFound() {
        // Mock the repository to return the room when searching by id
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));

        // Call the service method to delete the room
        roomService.deleteRoom(1);

        // Verify the repository's deleteById method was called once
        verify(roomRepository, times(1)).deleteById(1);
    }
}
