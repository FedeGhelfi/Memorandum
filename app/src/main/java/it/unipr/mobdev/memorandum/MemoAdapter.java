package it.unipr.mobdev.memorandum;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<Memo> list;   // reference to the dataset

    // constructor without parameters (for child classes)
    public MemoAdapter() {
        this.list = new ArrayList<>();
    }

    public MemoAdapter(ArrayList<Memo> memoList) {
        this.list = memoList;
    }

    // to set up filtered lists
    public void setList(ArrayList<Memo> list) {
        this.list = list;
    }


    // creates the view holder for each item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //  initialize a viewHolder based on its position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // retrieve the memo from the list
        //Memo memo = list.get(position);
        Memo memo = list.get(position);

        // set title and date for the item
        holder.tv_memo_title.setText(memo.getTitle());
        holder.tv_memo_date.setText(memo.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_memo_title, tv_memo_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the layout for the element (text view)
            tv_memo_title = itemView.findViewById(R.id.textView_memo_title);
            tv_memo_date = itemView.findViewById(R.id.textView_memo_date);

            // click on a single cell opens detail activity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(),DetailActivity.class);
                    int position = getLayoutPosition();
                    Memo m = list.get(position);
                    intent.putExtra("ID",m.getId());
                    System.out.println("DALLA MAIN PASSO: " + m.getId());
                    intent.putExtra("TITLE", m.getTitle());
                    intent.putExtra("DESCRIPTION", m.getDescription());
                    intent.putExtra("DATE", m.getDate());
                    intent.putExtra("HOUR",m.getHour());
                    intent.putExtra("PLACE", m.getPlace());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

