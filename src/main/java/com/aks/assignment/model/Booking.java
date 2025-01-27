package com.aks.assignment.model;

import java.time.LocalDate;

public class Booking {

    private String memberName;
    private ClubClass clubClass;
    private LocalDate participationDate;

    public Booking(String memberName, ClubClass clubClass, LocalDate participationDate) {
        this.memberName = memberName;
        this.clubClass = clubClass;
        this.participationDate = participationDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public ClubClass getClubClass() {
        return clubClass;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }
}

