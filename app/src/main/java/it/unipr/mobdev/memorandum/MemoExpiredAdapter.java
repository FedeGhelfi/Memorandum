package it.unipr.mobdev.memorandum;

import java.util.ArrayList;

public class MemoExpiredAdapter extends MemoAdapter {
    public MemoExpiredAdapter(ArrayList<Memo> memoList) {
        super();

        // popolo una lista con memo scaduti

        ArrayList<Memo> expiredMemoList = new ArrayList<Memo>();
        for (Memo memo : memoList) {
            if (memo.isExpired()) {
                expiredMemoList.add(memo);
            }
        }
        setList(expiredMemoList);
    }
}
