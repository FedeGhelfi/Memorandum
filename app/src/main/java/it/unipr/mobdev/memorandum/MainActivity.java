package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Memorandum");

        // set the Toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);        // back button

        /*
        TEST: fill the list manually
         */
        MemoList memoList = MemoList.getInstance();

        Memo memo1 = new Memo("Prova", "prima prova", "23-08-22", "Polesine", "15.10", "active");       // ACTIVE
        Memo memo2 = new Memo("Ciao", "Bla Bla", "29-10-22", "Busseto", "12.45","active");
        Memo memo3 = new Memo("Prova2", "prova 2", "23-08-22", "Polesine Pse", "15.15", "expired");     // EXPIRED
        memoList.addMemo(memo1);
        memoList.addMemo(memo2);
        memoList.addMemo(memo3);

        //  ------------------------------------------------------------------

        // get the recycler view
        RecyclerView rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_memo.addItemDecoration(new DividerItemDecoration(rv_memo.getContext(), DividerItemDecoration.VERTICAL));
        // define the adapters
        MemoAdapter adapter = new MemoAdapter(memoList.getMemoList());
        MemoActiveAdapter activeAdapter = new MemoActiveAdapter(memoList.getMemoList());
        MemoExpiredAdapter expiredAdapter = new MemoExpiredAdapter(memoList.getMemoList());
        MemoCompleteAdapter completeAdapter = new MemoCompleteAdapter(memoList.getMemoList());

        rv_memo.setLayoutManager(layoutManager);
        // rv_memo.setAdapter(adapter);
        rv_memo.setAdapter(activeAdapter);

        TabLayout tab = findViewById(R.id.tabLayout);

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


        FloatingActionButton fab = findViewById(R.id.add_memo_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: creare intent per passare all'activity dove vengono raccolti i dati
            }
        });


    }
}