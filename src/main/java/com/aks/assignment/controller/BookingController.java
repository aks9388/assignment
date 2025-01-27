package com.aks.assignment.controller;

import com.aks.assignment.model.BookingDetails;
import com.aks.assignment.model.ClubClass;
import com.aks.assignment.model.ErrorResponse;
import com.aks.assignment.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> rawData) {
        try {
            String classId = (String) rawData.get("classId");
            String memberName = (String) rawData.get("memberName");
            String participationDate = (String) rawData.get("participationDate");

            ClubClass clubClass = classService.getAllClasses().stream()
                    .filter(c -> c. getId().equals(classId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Class not found"));

            LocalDate date = LocalDate.parse(participationDate);

            BookingDetails bookingDetails = classService.createBooking(memberName, clubClass, date);

            return new ResponseEntity<>(bookingDetails, HttpStatus.CREATED);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookingDetails>> searchBookings(
            @RequestParam(required = false) String member,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        try {
            LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
            LocalDate end = endDate != null ? LocalDate.parse(endDate) : null;

            List<BookingDetails> bookingDetails = classService.searchBookings(member, start, end);
            return ResponseEntity.ok(bookingDetails);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

