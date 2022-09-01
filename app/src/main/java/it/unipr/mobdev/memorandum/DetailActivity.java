package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar = null;
    private TextView tv_description = null;
    private TextView tv_date = null;
    private TextView tv_hour = null;
    private TextView tv_place = null;
    private Button complete = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");

        // setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.my_toolbar_detail);
        toolbar.setTitle(title);
        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button

        // prendo i dati passati dalla view precedente
        String description = intent.getStringExtra("DESCRIPTION");
        String date = intent.getStringExtra("DATE");
        String hour = intent.getStringExtra("HOUR");
        String place = intent.getStringExtra("PLACE");
        int id = intent.getIntExtra("ID",-1);


        // setto i dati nelle textView
        tv_description = (TextView) findViewById(R.id.tv_setDescription);
        tv_date = (TextView) findViewById(R.id.tv_setDate);
        tv_hour = (TextView) findViewById(R.id.tv_setHour);
        tv_place = (TextView) findViewById(R.id.tv_setPlace);
        tv_description.setText(description);
        tv_date.setText(date);
        tv_hour.setText(hour);
        tv_place.setText(place);


        complete = (Button) findViewById(R.id.complete_button);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoList list = MemoList.getInstance();
                list.setMemoState("completed",id);
            }
        });



    }
}