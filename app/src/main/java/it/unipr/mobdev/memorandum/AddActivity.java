package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";

    private Toolbar toolbar = null;
    private EditText titleMemo = null;
    private EditText decriptionMemo = null;
    private TextView dateMemo = null;
    private ImageButton pickDateMemo = null;
    private ImageButton pickTimeMemo = null;
    private TextView timeMemo = null;
    private EditText placeMemo = null;
    private Button save = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Aggiungi un promemoria");
        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button


        // DatePicker's onClick
        pickDateMemo = (ImageButton) findViewById(R.id.imgBtnDate);
        pickDateMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("Text", R.id.textView_date);
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        // TimePicker's onClick
        pickTimeMemo = findViewById(R.id.btnTime);
        pickTimeMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("Text", R.id.textView_time);
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "timePicker");

            }
        });

        // save button
        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    check();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void check() throws ParseException {
        Toast toast = null;

        titleMemo = (EditText) findViewById(R.id.edit_text_title);
        decriptionMemo = (EditText) findViewById(R.id.edit_text_description);
        dateMemo = (TextView) findViewById(R.id.textView_date);
        timeMemo = (TextView) findViewById(R.id.textView_time);
        placeMemo = (EditText) findViewById(R.id.edit_text_place);

        String title = titleMemo.getText().toString();
        String description = decriptionMemo.getText().toString();
        String date = dateMemo.getText().toString();
        String time = timeMemo.getText().toString();
        String place = placeMemo.getText().toString();

        // Current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


        if(title.length() == 0) {
            toast.makeText(this, "Inserisci il titolo del promemoria!",toast.LENGTH_SHORT).show();
        }
        else if(description.length() == 0) {
            toast.makeText(this, "Inserisci la descrizione del promemoria!", toast.LENGTH_SHORT).show();
        }
        else if(date.length() == 0) {
            toast.makeText(this, "Inserisci la data del promemoria!", toast.LENGTH_SHORT).show();
        }
        else if(place.length() == 0){
            toast.makeText(this, "Inserisci il luogo del promemoria", toast.LENGTH_SHORT).show();
        }

        else if(sdf.parse(date).before(sdf.parse(formattedDate))){
            toast.makeText(this, "La data del promemoria deve essere successiva a quella odierna",toast.LENGTH_SHORT).show();
        }

        else {
            finish(title, description, date, time, place);
        }
    }


    private void finish(String title, String description, String date, String time, String place) {

        Memo m = new Memo(title, description, date, time, place, "active");
        MemoList.getInstance().addMemo(m);
        super.finish();
    }
}