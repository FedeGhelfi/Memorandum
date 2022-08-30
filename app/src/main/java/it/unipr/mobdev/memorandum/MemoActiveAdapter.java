package it.unipr.mobdev.memorandum;

import java.util.ArrayList;

public class MemoActiveAdapter extends MemoAdapter {

    public MemoActiveAdapter(ArrayList<Memo> memoList) {
        super();

        // popolo una lista con memo attivi

        ArrayList<Memo> activeMemoList = new ArrayList<Memo>();
        for (Memo memo : memoList) {
            if (memo.isActive()) {
                activeMemoList.add(memo);
            }
        }
        setList(activeMemoList);
    }
}