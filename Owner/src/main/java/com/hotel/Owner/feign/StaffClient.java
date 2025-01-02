package com.hotel.Owner.feign;

import com.hotel.Owner.dto.Staff;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "staff-service-owner", url = "http://localhost:2000/manager/staffDetails")
public interface StaffClient
{
    @GetMapping("/show")
    List<Staff> getAllStaffs();
}