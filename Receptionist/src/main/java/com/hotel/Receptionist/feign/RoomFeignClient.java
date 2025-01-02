package com.hotel.Receptionist.feign;

import com.hotel.Receptionist.dto.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "room-service-receptionist", url = "http://localhost:2000/manager/roomDetails")
public interface RoomFeignClient
{
    @GetMapping("/show/{id}")
    Room showRoomById(@PathVariable int id);

    @GetMapping("/checkAvailability")
    String checkRoomAvailability(@RequestParam int id);

    @PutMapping("/update/availability/{id}")
    void updateRoomAvailability(@PathVariable int id, @RequestParam("available") String available);
}