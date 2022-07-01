package com.dynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynamodb.entity.UserHistory;
import com.dynamodb.repository.UserRepository;

@RestController
public class UserHistoryController {

    @Autowired
    private UserRepository userRepository;

    

    @PostMapping("/add/userHistory")
    public UserHistory saveUserHistory(@RequestBody UserHistory userHistory) {
        return userRepository.saveUserHistory(userHistory);
    }

    @GetMapping("/get/userHistory/{personId}")
    public List<UserHistory> getUserHistory(@RequestParam(name="startDate", required=false) String startDate, @RequestParam(name="endDate", required=false) String endDate, @PathVariable("personId") String personId){
        return userRepository.getUserHistory(startDate,endDate,personId);
    }

    
}
