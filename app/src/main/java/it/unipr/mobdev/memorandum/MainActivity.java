package it.unipr.mobdev.memorandum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final int CREATE_MEMO_REQUEST = 1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the toolbar
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Memorandum");

        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button

        /*
        TODO: Creare metodo per riempire provvisoriamente lista dopo aver controllato la size.
         */
        list = MemoList.getInstance();

        //  ------------------------------------------------------------------


    }

    @Override
    protected void onStart(){
        super.onStart();

        // get the recycler view
        rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // separator
        rv_memo.addItemDecoration(new DividerItemDecoration(rv_memo.getContext(), DividerItemDecoration.VERTICAL));
        // define the adapters
        adapter = new MemoAdapter(list.getMemoList());
        activeAdapter = new MemoActiveAdapter(list.getMemoList());
        expiredAdapter = new MemoExpiredAdapter(list.getMemoList());
        completeAdapter = new MemoCompleteAdapter(list.getMemoList());

        rv_memo.setLayoutManager(layoutManager);
        // rv_memo.setAdapter(adapter);
        rv_memo.setAdapter(activeAdapter);

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
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


        addFab = findViewById(R.id.add_memo_button);

        // the event triggered by addFab
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddActivity.class);
                //startActivityForResult(intent, CREATE_MEMO_REQUEST);
                startActivity(intent);
            }
        });

    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_MEMO_REQUEST && resultCode == RESULT_OK) {
            if(data.hasExtra("title") && data.hasExtra("description") &&
            data.hasExtra("date") && data.hasExtra("time") && data.hasExtra("place")) {

                list.addMemo(new Memo(data.getStringExtra("title"), data.getStringExtra("description"), data.getStringExtra("date"), data.getStringExtra("time"),
                        data.getStringExtra("place"), "active"));

                activeAdapter.notifyDataSetChanged();
                MemoList.getInstance().setMemoList(list.getMemoList());
            }
        }
    }

     */
}