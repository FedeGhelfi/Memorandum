package it.unipr.mobdev.memorandum;

// Model class

public class Memo {

    private int id;
    private final String title;
    private final String description;
    private final String date;
    private final String place;
    private final String hour;

    // Memo's state
    private Boolean expired = false;
    private Boolean completed = false;
    private Boolean active = false;


    public Memo (String title, String description, String date, String hour, String place, String state) {
       // this.id = MemoList.getInstance().size();
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
                break;
        }
    }

    // public method to change memo state
    public void changeState(String state) {
        setState(state);
    }

    public int getId(){ return this.id; }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public String getPlace() {
        return this.place;
    }

    public String getHour() {
        return this.hour;
    }

    public Boolean isExpired() {
        return this.expired;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean isActive() {
        return this.active;
    }
}
