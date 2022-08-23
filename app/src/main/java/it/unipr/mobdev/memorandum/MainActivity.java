package it.unipr.mobdev.memorandum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MemoList memolist = new MemoList();

        Memo memo1 = new Memo("Prova", "prima prova", "23-08-22", "Polesine", "15.10");
        Memo memo2 = new Memo("Prova2", "prova 2", "23-08-22", "Polesine Pse", "15.15");

        memolist.addMemo(memo1);
        memolist.addMemo(memo2);

        for (int i = 0; i < memolist.size(); i++) {
            Memo memo = memolist.getMemoAtIndex(i);
            String info = memo.getTitle() + " " + memo.getDescription() + " " + memo.getDate() + " "
                    + memo.getHour() + " " + memo.getPlace();
            Log.v("Debug", info);
        }
    }
}