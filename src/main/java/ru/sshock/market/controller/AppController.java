package ru.sshock.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sshock.market.config.AppSettings;
import ru.sshock.market.repository.UserRepository;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AppController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/settings")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> settings(@RequestParam("filter") Map<String, String> filters, @Autowired AppSettings appSettings) {
        if (filters.containsKey("jwt_expiration"))
            appSettings.setJwtExpirationMs(Integer.parseInt(filters.get("jwt_expiration")));
        System.out.println();
        return ResponseEntity.ok("" + appSettings.getJwtExpirationMs());
    }
}