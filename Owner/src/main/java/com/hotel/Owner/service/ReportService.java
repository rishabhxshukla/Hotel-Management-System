package com.hotel.Owner.service;

import com.hotel.Owner.dto.Report;
import com.hotel.Owner.dto.Room;
import com.hotel.Owner.dto.Staff;
import com.hotel.Owner.feign.RoomClient;
import com.hotel.Owner.feign.StaffClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService
{
    @Autowired
    private RoomClient roomClient;

    @Autowired
    private StaffClient staffClient;

    public Report generateReport()
    {
        //Fetch data from microservices
        List<Room> rooms = roomClient.getAllRooms();
        List<Staff> staffs = staffClient.getAllStaffs();

        //Calculate statistics
        int totalRooms = rooms.size();

        int availableRooms = (int) rooms.stream()
                .filter(room -> room.getAvailable().equals("Yes"))
                .count();

        int totalEmployees = staffs.size();

        double totalSalary = staffs.stream()
                .mapToDouble(staff -> staff.getSalary())
                .sum();

        //Return report
        return new Report(totalRooms, availableRooms, totalEmployees, totalSalary);
    }
}