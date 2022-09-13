package it.unipr.mobdev.memorandum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MemoActiveAdapter extends MemoAdapter {

    public MemoActiveAdapter(ArrayList<Memo> memoList) {
        super();

        // popolo una lista con memo attivi

        ArrayList<Memo> activeMemoList = new ArrayList<Memo>();
        for (Memo memo : memoList) {
           // if (memo.isActive()) {

             if(!memo.isExpired() && memo.getState().equals("active")){
                activeMemoList.add(memo);
            }
        }

        Collections.sort(activeMemoList); // uses the comparable criteria
        setList(activeMemoList);
    }
}
