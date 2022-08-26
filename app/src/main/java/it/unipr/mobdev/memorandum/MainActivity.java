package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
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

        /*
        TEST: fill the list manually
         */

        MemoList memoList = MemoList.getInstance();

        Memo memo1 = new Memo("Prova", "prima prova", "23-08-22", "Polesine", "15.10", true);
        Memo memo2 = new Memo("Prova2", "prova 2", "23-08-22", "Polesine Pse", "15.15", false);

        memoList.addMemo(memo1);
        memoList.addMemo(memo2);


        //  ------------------------------------------------------------------

        // get the recycler view
        RecyclerView rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // define the adapter
        MemoAdapter adapter = new MemoAdapter(memoList.getMemoList());
        MemoActiveAdapter adapter_active = new MemoActiveAdapter(memoList.getMemoList());

        rv_memo.setLayoutManager(layoutManager);
        rv_memo.setAdapter(adapter);


        TabLayout tab = findViewById(R.id.tabLayout);

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    rv_memo.setAdapter(adapter);
                }
                else {
                    rv_memo.setAdapter(adapter_active);
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