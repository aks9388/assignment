package com.aks.assignment.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDetails {

    private String memberName;
    private String className;
    private String classId;
    private LocalTime classStartTime;
    private int classDuration;
    private LocalDate bookingDate;

    public BookingDetails(String memberName, String classId, String className, LocalTime classStartTime, int classDuration, LocalDate bookingDate) {
        this.memberName = memberName;
        this.classId = classId;
        this.className = className;
        this.classStartTime = classStartTime;
        this.classDuration = classDuration;
        this.bookingDate = bookingDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getClassName() {
        return className;
    }

    public String getClassId() {
        return classId;
    }

    public LocalTime getClassStartTime() {
        return classStartTime;
    }

    public int getClassDuration() {
        return classDuration;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
}
