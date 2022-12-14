package it.unipr.mobdev.memorandum;

import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.ID_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.DESC_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.DATE_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.HOUR_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.STATE_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.PLACE_MEMO;
import static it.unipr.mobdev.memorandum.MemoAdapter.ViewHolder.TITLE_MEMO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private MemoList list = null;
    private Toolbar toolbar = null;
    private TextView tv_description = null;
    private TextView tv_date = null;
    private TextView tv_hour = null;
    private TextView tv_place = null;
    private Button complete = null;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get the intent coming from mainActivty
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE_MEMO);

        // setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.my_toolbar_detail);
        toolbar.setTitle(title);
        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button


        // get the cell data
        String description = intent.getStringExtra(DESC_MEMO);
        String date = intent.getStringExtra(DATE_MEMO);
        String hour = intent.getStringExtra(HOUR_MEMO);
        String place = intent.getStringExtra(PLACE_MEMO);
        String state = intent.getStringExtra(STATE_MEMO);
        id = intent.getStringExtra(ID_MEMO);



        tv_description = (TextView) findViewById(R.id.tv_setDescription);
        tv_date = (TextView) findViewById(R.id.tv_setDate);
        tv_hour = (TextView) findViewById(R.id.tv_setHour);
        tv_place = (TextView) findViewById(R.id.tv_setPlace);

        complete = (Button) findViewById(R.id.complete_button);

        // complete button available only if the memo is active
        if (state.equals("completed") || state.equals("expired")) {
            complete.setVisibility(View.GONE);
        }

        if (state.equals("expired")) {
            tv_date.setTextColor(Color.RED);
            tv_hour.setTextColor(Color.RED);
        }

        tv_description.setText(description);
        tv_date.setText(date);
        tv_hour.setText(hour);
        tv_place.setText(place);


        // complete button event
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = MemoList.getInstance();
                list.setMemoState("completed", id);
                System.out.println(id);
                DetailActivity.super.finish();
            }
        });
    }


    // click on toolbar items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // back
            case android.R.id.home:
                finish();
                return true;

            // delete
            case R.id.delete_item:
                removeMemo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // insert the item in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    public void removeMemo() {

        // alert dialog with choices
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setMessage("Sei sicuro di voler rimuovere il promemoria?");
        builder.setTitle("Attenzione!");
        builder.setCancelable(true);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MemoList list = MemoList.getInstance();
                list.removeMemo(id);
                dialogInterface.cancel();
                DetailActivity.super.finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert1 = builder.create();
        alert1.show();
    }
}