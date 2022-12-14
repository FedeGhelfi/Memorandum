package it.unipr.mobdev.memorandum;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TextView time = null;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstance) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minutes, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            if (bundle.containsKey("Text")) {
                time = (TextView) getActivity().findViewById(bundle.getInt("Text"));
            }
            else if (bundle.containsKey("EditText")) {
                time = (EditText) getActivity().findViewById(bundle.getInt("EditText"));
            }
        }
        Format formatter = new SimpleDateFormat("HH:mm");
        String strTime;

        if (hour < 10 && minutes < 10) {
            strTime = ("0" + String.valueOf(hour) + ":" + "0" + String.valueOf(minutes));
        }
        else if (hour >= 10 && minutes < 10) {
            strTime = (String.valueOf(hour) + ":" + "0" + String.valueOf(minutes));
        } else if (hour < 10 && minutes >= 10) {
            strTime = ("0" + String.valueOf(hour) + ":" + String.valueOf(minutes));
        }
        else {
            strTime = (String.valueOf(hour) + ":" + String.valueOf(minutes));
        }
        time.setText(strTime);
    }
}
