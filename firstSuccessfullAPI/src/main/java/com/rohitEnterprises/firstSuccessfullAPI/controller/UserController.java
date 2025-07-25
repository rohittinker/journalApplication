package com.rohitEnterprises.firstSuccessfullAPI.controller;



import com.rohitEnterprises.firstSuccessfullAPI.api.response.WeatherResponse;
import com.rohitEnterprises.firstSuccessfullAPI.entity.User;
import com.rohitEnterprises.firstSuccessfullAPI.repository.UserRepository;
import com.rohitEnterprises.firstSuccessfullAPI.service.UserService;
import com.rohitEnterprises.firstSuccessfullAPI.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName =authentication.getName();
       User userInDb = userService.findByUserName(userName);
       if (userInDb != null){
           userInDb.setUserName(user.getUserName());
           userInDb.setPassword(user.getPassword());
           userService.saveEntry(userInDb);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse =weatherService.getWeather("Mumbai");
        String greeting ="";
        if (weatherResponse != null && weatherResponse.getCurrent() != null){
            greeting = ", Weather fells like " + weatherResponse.getCurrent().getFeelslike();
        }else{
            greeting = ", Weather data not available";
        }
        return new ResponseEntity<>(" Hi "+ authentication.getName() + greeting, HttpStatus.OK);
    }



}
