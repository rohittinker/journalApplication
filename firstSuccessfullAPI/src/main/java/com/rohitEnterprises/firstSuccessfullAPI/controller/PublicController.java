package com.rohitEnterprises.firstSuccessfullAPI.controller;


import com.rohitEnterprises.firstSuccessfullAPI.entity.User;
import com.rohitEnterprises.firstSuccessfullAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){ return "Ok" ;}

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.saveEntry(user);
            return ResponseEntity.ok("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists: " + user.getUserName());
        }
    }
}
