package it.unipr.mobdev.memorandum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public void addMemo(Memo memo)  {
        list.add(memo);
    }

    public int size() {
        return list.size();
    }

    public Memo getMemoAtIndex(int position) {
       return list.get(position);
    }

    public ArrayList<Memo> getMemoList(){
        return list;
    }

    // given an ID and a state, it looks for the corresponding memo
    public void setMemoState(String state, String id) {
        for (int i = 0; i < list.size(); i++) {
            Memo m = getMemoAtIndex(i);
            if (m.getId().equals(id)){
                m.changeState(state);
                return;
            }
        }
    }


    public void removeMemo(String id) {
        for (int i = 0; i < list.size(); i++) {
            Memo m = getMemoAtIndex(i);
            if (m.getId().equals(id)) {
                list.remove(i);
                return;
            }
        }
    }
}


