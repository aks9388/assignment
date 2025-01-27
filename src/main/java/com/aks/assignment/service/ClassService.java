package com.aks.assignment.service;


import com.aks.assignment.model.Booking;
import com.aks.assignment.model.BookingDetails;
import com.aks.assignment.model.ClubClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private List<ClubClass> clubClasses = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public List<ClubClass> getAllClasses() {
        return clubClasses;
    }

    public ClubClass createClass(ClubClass clubClass) {
        if (clubClass.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

        if (clubClass.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date must be in the future.");
        }

        if (clubClass.getStartDate().isAfter(clubClass.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after the end date.");
        }

        clubClass.setId(UUID.randomUUID().toString());
        clubClasses.add(clubClass);

        return clubClass;
    }

    public BookingDetails createBooking(String memberName, ClubClass clubClass, LocalDate participationDate) {
        if (participationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Participation date must be in the future.");
        }
        if (participationDate.isBefore(clubClass.getStartDate()) || participationDate.isAfter(clubClass.getEndDate())) {
            throw new IllegalStateException(String.format("Participation Date is  not within the %s's schedule.", clubClass.getName()));
        }

        long bookedCount = bookings.stream()
                .filter(booking -> booking.getClubClass().equals(clubClass) &&
                        booking.getParticipationDate().equals(participationDate))
                .count();

        if (bookedCount >= clubClass.getCapacity()) {
            throw new IllegalStateException("Class capacity exceeded for this date.");
        }

        Booking booking = new Booking(memberName, clubClass, participationDate);
        bookings.add(booking);

        BookingDetails bookingDetails = new BookingDetails(
                booking.getMemberName(),
                booking.getClubClass().getId(),
                booking.getClubClass().getName(),
                booking.getClubClass().getStartTime(),
                booking.getClubClass().getDuration(),
                booking.getParticipationDate());
        return bookingDetails;
    }

    public List<BookingDetails> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        List<Booking> filteredBookings = bookings;

        if (memberName != null && !memberName.isEmpty()) {
            filteredBookings = filteredBookings.stream()
                    .filter(booking -> booking.getMemberName().equalsIgnoreCase(memberName))
                    .collect(Collectors.toList());
        }

        if (startDate != null) {
            filteredBookings = filteredBookings.stream()
                    .filter(booking -> !booking.getParticipationDate().isBefore(startDate))
                    .collect(Collectors.toList());
        }

        if (endDate != null) {
            filteredBookings = filteredBookings.stream()
                    .filter(booking -> !booking.getParticipationDate().isAfter(endDate))
                    .collect(Collectors.toList());
        }

        List<BookingDetails> bookingDetails = filteredBookings.stream()
                .map(booking -> new BookingDetails(
                        booking.getMemberName(),
                        booking.getClubClass().getId(),
                        booking.getClubClass().getName(),
                        booking.getClubClass().getStartTime(),
                        booking.getClubClass().getDuration(),
                        booking.getParticipationDate()))
                .collect(Collectors.toList());

        return bookingDetails;
    }
}
