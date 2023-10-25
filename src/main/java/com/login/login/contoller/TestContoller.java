package com.login.login.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestContoller {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mode")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        String sql = "SELECT 
            "PP.provinceCode, "
            "PP.description, " +
            "PP.status.description, " +
            "PP.lastupdateduser, " +
            "PP.lastupdatedtime, " +
            "PP.createdtime, " +
            "PP.remarks, " +
            "PP.email, " +
            "PP.country.description " +
            ") FROM " +
            "Province PP " +
            "WHERE PP.provinceCode LIKE %?1% AND PP.description LIKE %?2% AND PP.status.statuscode like %?3% AND PP.country.countrycode like %?4%  " +
            "ORDER BY " +
            "PP.lastupdatedtime DESC"
        return "Admin Board.";
    }
}
