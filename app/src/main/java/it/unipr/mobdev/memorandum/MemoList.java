package it.unipr.mobdev.memorandum;

import java.util.ArrayList;

public class MemoList {

    private static MemoList instance = null;
    private ArrayList<Memo> memoArrayList;


    private MemoList () {
        this.memoArrayList = new ArrayList<>();
    }
    public void setMemoList(ArrayList<Memo> list) { this.memoArrayList = list; }
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
    public ArrayList<Memo> getMemoList(){
        return memoArrayList;
    }


    // singleton
    public static MemoList getInstance() {
        if (instance == null) {
            instance = new MemoList();
        }
        return instance;
    }

}
