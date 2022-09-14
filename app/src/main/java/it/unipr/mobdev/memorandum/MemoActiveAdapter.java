package it.unipr.mobdev.memorandum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MemoActiveAdapter extends MemoAdapter {

    public MemoActiveAdapter(ArrayList<Memo> memoList) {
        super();


        // list with only active Memo
        ArrayList<Memo> activeMemoList = new ArrayList<Memo>();
        for (Memo memo : memoList) {

             if(!memo.isExpired() && memo.getState().equals("active")){
                activeMemoList.add(memo);
            }
        }

        Collections.sort(activeMemoList); // uses the comparable criteria
        setList(activeMemoList);
    }
}
