package it.unipr.mobdev.memorandum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MemoList list = null;
    MemoAdapter adapter = null;
    MemoActiveAdapter activeAdapter = null;
    MemoExpiredAdapter expiredAdapter = null;
    MemoCompleteAdapter completeAdapter = null;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private RecyclerView rv_memo;
    private FloatingActionButton addFab;
    private FloatingActionButton mapFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the toolbar
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Memorandum");

        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button

        // get the instance
        list = MemoList.getInstance();

        String t = "ON-CREATE CALLED";
        Log.v(TAG,t);

        // restore data from memory
        loadData();

        // open MapActivity
        mapFab = findViewById(R.id.open_map);
        mapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });

        // open Add Activity
        addFab = findViewById(R.id.add_memo_button);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onStart(){
        super.onStart();

        buildRecyclerView();

        // get the tab layout
        tabLayout = findViewById(R.id.tabLayout);

        // the active tab --> the correct adapter
        switch (tabLayout.getSelectedTabPosition()){
            case 0:
                rv_memo.setAdapter(activeAdapter);
                break;
            case 1:
                rv_memo.setAdapter(completeAdapter);
                break;
            case 2:
                rv_memo.setAdapter(expiredAdapter);
                break;
            default:
                rv_memo.setAdapter(activeAdapter);
        }

        // change tab --> correct adapter
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {

                    case 0:
                        rv_memo.setAdapter(activeAdapter);
                        break;
                    case 1:
                        rv_memo.setAdapter(completeAdapter);
                        break;
                    case 2:
                        rv_memo.setAdapter(expiredAdapter);
                        break;
                    default:
                        rv_memo.setAdapter(activeAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public void buildRecyclerView() {
        // get the recycler view
        rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // separator
        rv_memo.addItemDecoration(new DividerItemDecoration(rv_memo.getContext(), DividerItemDecoration.VERTICAL));
        // define the adapters
        adapter = new MemoAdapter(MemoList.getInstance().getMemoList());
        activeAdapter = new MemoActiveAdapter(list.getMemoList());
        expiredAdapter = new MemoExpiredAdapter(list.getMemoList());
        completeAdapter = new MemoCompleteAdapter(list.getMemoList());

        rv_memo.setLayoutManager(layoutManager);
        // rv_memo.setAdapter(adapter);
        rv_memo.setAdapter(activeAdapter);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MemoList.getInstance().getMemoList());
        editor.putString("memo list", json);
        editor.apply();
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("memo list", null);
        Type type = new TypeToken<ArrayList<Memo>>() {}.getType();

       // temp arrayList with saved data
        ArrayList<Memo> recovered = gson.fromJson(json, type);

        if (recovered == null) {
            recovered = new ArrayList<>();
        }

        for (Memo memo : recovered) {
            list.addMemo(memo);
        }
    }
}