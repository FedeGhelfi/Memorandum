package it.unipr.mobdev.memorandum;

// Model class

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Memo implements Comparable<Memo> {

    private String id;
    private final String title;
    private final String description;
    private final String date;
    private final String place;
    private final String hour;
    private String state;

    // Memo's state
    private Boolean expired = false;
    private Boolean completed = false;
    private Boolean active = false;


    public Memo(String title, String description, String date, String hour, String place, String state) {
        this.id = UUID.randomUUID().toString(); // get a random id
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.hour = hour;
        setState(state);
    }


    private void setState(String state) {
        switch (state) {
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

    public String getId() {
        return this.id;
    }

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


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        //SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date current = Calendar.getInstance().getTime();

        String datehour = getDate() + "-" + getHour();
        Date datehourparsed;



        try {
            /*
            memoDate = formatter.parse(date);
            memoHour = df.parse(hour);
            */
            datehourparsed = formatter.parse(datehour);


            if (current.compareTo(datehourparsed) > 0) {
                System.out.println("ABBIAMO SUPERATO LA SCADENZA DEL MEMO");
                setState("expired");
                return true;
            }

            /*
            // se è stesso giorno ma ora è passata
            else if ((current.compareTo(memoDate) == 0) && (current.compareTo(memoHour) > 0)) {
                System.out.println("ABBIAMO SUPERATO L'ORA DEL MEMO");
                setState("expired");
                expired = true;
            }
            */
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;

    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean isActive() {
        return this.active;
    }

    public String getState() {

        String state = null;

        if (isActive()) {
            state = "active";
        } else if (isCompleted()) {
            state = "completed";
        } else if (isExpired()) {
            state = "expired";
        }

        return state;
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
