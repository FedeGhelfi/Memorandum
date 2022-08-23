package it.unipr.mobdev.memorandum;

import java.util.ArrayList;

public class MemoList {

    private final ArrayList<Memo> memoArrayList;

    // constructor
    public MemoList () {
        this.memoArrayList = new ArrayList<>();
    }

    public void addMemo(Memo memo) {
        memoArrayList.add(memo);
    }

    public int size() {
        return memoArrayList.size();
    }

    public void removeMemo(int position) {
        memoArrayList.remove(position);
    }

    public Memo getMemoAtIndex(int position) {
       return memoArrayList.get(position);
    }


}
