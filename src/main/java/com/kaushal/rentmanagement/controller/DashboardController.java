package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.DashboardDto;
import com.kaushal.rentmanagement.service.DashboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://moonlit-crisp-51b17b.netlify.app"
})
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public List<DashboardDto> getDashboard(

            @RequestParam Integer month,

            @RequestParam Integer year

    ) {

        return dashboardService.getDashboard(month, year);

    }

}