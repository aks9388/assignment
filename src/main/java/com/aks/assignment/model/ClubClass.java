package com.aks.assignment.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClubClass {

    private String id;  // Added ID for class identification
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int duration;
    private int capacity;

    public ClubClass(String name, LocalDate startDate, LocalDate endDate, LocalTime startTime, int duration, int capacity) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.duration = duration;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }
}
