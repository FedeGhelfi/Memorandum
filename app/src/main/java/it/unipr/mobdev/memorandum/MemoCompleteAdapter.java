package it.unipr.mobdev.memorandum;

import java.util.ArrayList;

public class MemoCompleteAdapter extends MemoAdapter {
    public MemoCompleteAdapter(ArrayList<Memo> memoList) {
        super();

        // popolo una lista con memo completati
        ArrayList<Memo> completeMemoList = new ArrayList<Memo>();
        for (Memo memo : memoList) {
            if (memo.isCompleted()) {
                completeMemoList.add(memo);
            }
        }
        setList(completeMemoList);
    }
}
