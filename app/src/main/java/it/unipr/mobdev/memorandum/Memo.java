package it.unipr.mobdev.memorandum;

// Model class

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Memo implements Comparable<Memo> {

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
        this.id = MemoList.getInstance().size();
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

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date current = Calendar.getInstance().getTime();

        String date = getDate();
        Date memoDate;
        try {
            memoDate = formatter.parse(date);

            if (current.compareTo(memoDate) > 0) {
                System.out.println("ABBIAMO SUPERATO LA DATA DEL MEMO");
                setState("expired");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.expired;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean isActive() {
        return this.active;
    }

    // sort by data
    @Override
    public int compareTo(Memo m) {

        String stringData1 = getDate();
        String stringData2 = m.getDate();

        Date date1;
        Date date2;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            date1 = sdf.parse(stringData1);
            date2 = sdf.parse(stringData2);

            assert date1 != null;
            return date1.compareTo(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
