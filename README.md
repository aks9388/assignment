This is a spring boot application and the entry class is AssignmentApplication.

I have added sample postman collection for all the APIs. [postman collection](/Assignment.postman_collection.json)


## Problem statement:
## Story - Create Classes
As a club owner, I want to be able to create classes for my club so that my members can attend classes.
### Acceptance Criteria
- There is an API to create a class
- A class should have the following required details: Name, Start Date, End Date, Start Time, Duration, Capacity
- There is one class per day
  -For example, if a class named Pilates starts on December 1st and ends on December20th ,
    occurs at 2pm and has a capacity of 10, that means Pilates has 20 classes and for each class the maximum attendance capacity is 10.
- The class name does not need to be unique
- The capacity must be at least 1
- The end date must be in the future

## Story - Book a Class
As a member of a studio, I want to book myself for a class, so that I can attend.
### Acceptance Criteria
- There is an API to create a booking
- In order to book a spot in a class, the following is required: Member name, Class, Participation Date
- A new booking may not cause a class to exceed its capacity
- A member may book multiple classes for the same day and time. There is no validation needed for the member’s availability.
- The participation date must be in the future

## Story – Search Bookings
As a club owner, I want to review the bookings for my classes
### Acceptance Criteria
- There is an API to search bookings
- I can search by member (i.e. show me all bookings for a member)
- I can search for a date range (i.e. show me all bookings between May 2nd and May 9th)
- I can combine these to search for both member and date range
- Results should return details about the matching bookings including class name, class start time, booking date, member

