package it.unipr.mobdev.memorandum;

// Model class

public class Memo {

    private String title;
    private String description;
    private String date;
    private String place;
    private String hour;

    // Memo's state
    private Boolean expired = false;
    private Boolean completed = false;
    private Boolean active = true;

    // constructor
    public Memo (String title, String description, String date, String place, String hour, Boolean isActive) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.hour = hour;
        this.active = isActive;
    }

    /*      GETTERS             */

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getHour() {
        return hour;
    }

    public Boolean isExpired() {
        return expired;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Boolean isActive() {
        return active;
    }

    /* ------------------- */


}
