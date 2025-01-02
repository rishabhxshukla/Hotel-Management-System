package com.hotel.Owner.controller;

import com.hotel.Owner.dto.Report;
import com.hotel.Owner.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class ReportController
{
    @Autowired
    ReportService service;

    @GetMapping("/report")
    public Report generateReport()
    {
        return service.generateReport();
    }
}