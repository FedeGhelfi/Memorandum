package it.unipr.mobdev.memorandum;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView data = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // do something with the date chosen by the user

        // controllo che il bundle abbia argomenti
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            if (bundle.containsKey("Text")) {   // se contiene text lo recupero come text view
                // imposto la tv locale come quella che è stata cliccata
                data = (TextView) getActivity().findViewById(bundle.getInt("Text"));
            }
            else if (bundle.containsKey("EditText")){
                // se contiene edit text lo recupero come edit text
                data = (EditText) getActivity().findViewById(bundle.getInt("EditText"));
            }
        }

        String strData = (String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
        data.setText(strData);
    }
}
