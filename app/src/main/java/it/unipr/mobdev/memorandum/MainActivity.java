package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG = "MainActivity";

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
        MemoList memoList = MemoList.getInstance();

        Memo memo1 = new Memo("Prova", "prima prova", "23-08-22", "Polesine", "15.10", "active");       // ACTIVE
        Memo memo2 = new Memo("Ciao", "Bla Bla", "29-10-22", "Busseto", "12.45","active");
        Memo memo3 = new Memo("Prova2", "prova 2", "23-08-22", "Polesine Pse", "15.15", "expired");     // EXPIRED
        memoList.addMemo(memo1);
        memoList.addMemo(memo2);
        memoList.addMemo(memo3);
        Log.i(TAG,"CREAZIONE");

        //  ------------------------------------------------------------------

        // get the recycler view
        rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // separator
        rv_memo.addItemDecoration(new DividerItemDecoration(rv_memo.getContext(), DividerItemDecoration.VERTICAL));
        // define the adapters
        MemoAdapter adapter = new MemoAdapter(memoList.getMemoList());
        MemoActiveAdapter activeAdapter = new MemoActiveAdapter(memoList.getMemoList());
        MemoExpiredAdapter expiredAdapter = new MemoExpiredAdapter(memoList.getMemoList());
        MemoCompleteAdapter completeAdapter = new MemoCompleteAdapter(memoList.getMemoList());

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
                // TODO: creare intent per passare all'activity dove vengono raccolti i dati
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "CIAO");
                startActivity(intent);

            }
        });


    }
}