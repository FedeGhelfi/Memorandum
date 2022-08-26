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
    private Boolean active = false;

    // constructor
    public Memo (String title, String description, String date, String place, String hour, String state) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.hour = hour;
        setState(state);

    }

    private void setState(String state){
        switch(state){
            case "active":
                active = true;
                expired = false;
                completed = false;
                break;
            case "expired":
                active = false;
                expired = true;
                completed = false;
                break;

            case "completed":
                active = false;
                expired = false;
                completed = true;
        }
    }

    // to change memo state
    public void changeState(String state) {
        setState(state);
    }

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
