package com.hotel.Owner.feign;

import com.hotel.Owner.dto.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "room-service-owner", url = "http://localhost:2000/manager/roomDetails")
public interface RoomClient
{
    @GetMapping("/show")
    List<Room> getAllRooms();
}