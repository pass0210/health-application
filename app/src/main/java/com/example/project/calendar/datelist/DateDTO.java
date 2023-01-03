package com.example.project.calendar.datelist;

public class DateDTO {
    String day;
    String date;
    int complete;

    public DateDTO(String day, String date, int complete) {
        this.day = day;
        this.date = date;
        this.complete = complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }
}
