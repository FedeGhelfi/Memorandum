package it.unipr.mobdev.memorandum;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

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

        String strTime = (String.valueOf(hour) + ":" + String.valueOf(minutes));
        time.setText(strTime);
    }
}
