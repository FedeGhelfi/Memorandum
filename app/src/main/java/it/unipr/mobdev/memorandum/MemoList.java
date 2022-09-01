package it.unipr.mobdev.memorandum;

import java.util.ArrayList;
import java.util.List;

public class MemoList {

    private static MemoList instance = null;
    private ArrayList<Memo> list;

    // singleton class
    public static MemoList getInstance() {
        if (instance == null) {
            instance = new MemoList();
        }
        return instance;
    }


    private MemoList () {
        this.list = new ArrayList<>();
    }

    public void setMemoList(ArrayList<Memo> list) { this.list = list; }

    public void addMemo(Memo memo) {
        list.add(memo);
    }

    public int size() {
        return list.size();
    }

    public void removeMemo(int position) {
        list.remove(position);
    }

    public Memo getMemoAtIndex(int position) {
       return list.get(position);
    }

    public ArrayList<Memo> getMemoList(){
        return list;
    }

    // given an ID and a state, it looks for the corresponding memo
    public void setMemoState(String state, int id) {
        for (int i = 0; i < list.size(); i++) {
            Memo m = getMemoAtIndex(i);
            if (m.getId() == id){
                m.changeState(state);
                return;
            }
        }
    }
}
