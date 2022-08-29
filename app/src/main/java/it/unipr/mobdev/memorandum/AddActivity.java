package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class AddActivity extends AppCompatActivity {

    private Toolbar toolbar = null;
    private EditText titleMemo = null;
    private EditText decriptionMemo = null;
    private TextView dateMemo = null;
    private ImageButton pickDateMemo = null;
    private Button pickTimeMemo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // setting the toolbar
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Aggiungi un promemoria");
        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button

        pickDateMemo = findViewById(R.id.imgBtnDate);
        pickDateMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("Text", R.id.textView_date);
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

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


    }
}