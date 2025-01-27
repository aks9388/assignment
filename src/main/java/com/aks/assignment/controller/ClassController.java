package com.aks.assignment.controller;

import com.aks.assignment.model.ClubClass;
import com.aks.assignment.model.ErrorResponse;
import com.aks.assignment.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<ClubClass>> getClasses() {
        List<ClubClass> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody ClubClass clubClass) {
        try {
            ClubClass createdClass = classService.createClass(clubClass);
            return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.OK);
        }
    }
}

