package it.unipr.mobdev.memorandum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<Memo> memoList;   // reference to the dataset

    public MemoAdapter(ArrayList<Memo> memoList) {
        this.memoList = memoList;
    }

    // creates the view holder for each item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // replace the content of a view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // retrieve the memo from the list
        Memo memo = memoList.get(position);

        // set title and date for the item
        holder.tv_memo_title.setText(memo.getTitle());
        holder.tv_memo_date.setText(memo.getDate());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_memo_title, tv_memo_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the layout for the element (text view)
            tv_memo_title = itemView.findViewById(R.id.textView_memo_title);
            tv_memo_date = itemView.findViewById(R.id.textView_memo_date);
        }
    }
}
