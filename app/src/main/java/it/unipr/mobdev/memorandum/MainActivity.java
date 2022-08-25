package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TEST: fill the list manually
         */

        MemoList memoList = new MemoList();

        Memo memo1 = new Memo("Prova", "prima prova", "23-08-22", "Polesine", "15.10");
        Memo memo2 = new Memo("Prova2", "prova 2", "23-08-22", "Polesine Pse", "15.15");

        memoList.addMemo(memo1);
        memoList.addMemo(memo2);

        // print each item to verify test
        for (int i = 0; i < memoList.size(); i++) {
            Memo memo = memoList.getMemoAtIndex(i);
            String info = memo.getTitle() + " " + memo.getDescription() + " " + memo.getDate() + " "
                    + memo.getHour() + " " + memo.getPlace();
            Log.v("Debug", info);
        }

        /*
        ------------------------------------------------------------------
         */

        // get the recycler view
        RecyclerView rv_memo = findViewById(R.id.memo_recycler_view);
        // define a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        // define the adapter
        MemoAdapter adapter = new MemoAdapter(memoList.getMemoList());

        rv_memo.setLayoutManager(layoutManager);
        rv_memo.setAdapter(adapter);
    }
}