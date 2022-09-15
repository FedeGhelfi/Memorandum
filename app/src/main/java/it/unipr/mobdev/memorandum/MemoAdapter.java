package it.unipr.mobdev.memorandum;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {




    private ArrayList<Memo> list;


    public MemoAdapter(ArrayList<Memo> memoList) {
        this.list = memoList;
    }


    // constructor without parameters (for child classes)
    public MemoAdapter() {
        this.list = new ArrayList<>();
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

        if (memo.isExpired()){      // SET TEXT RED IF MEMO IS EXPIRED
            holder.tv_memo_date.setTextColor(Color.RED);
            holder.tv_memo_date.setText("SCADUTO");
        }
        else if(memo.getState() == "completed"){
            holder.tv_memo_date.setTextColor(Color.GREEN);
            holder.tv_memo_date.setText("COMPLETATO");
        }

        else {
            holder.tv_memo_date.setText(memo.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // strings that will be use inside intent to switch to detailActivity
        public static final String ID_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.ID_MEMO";
        public static final String TITLE_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.TITLE_MEMO";
        public static final String DESC_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.DESC_MEMO";
        public static final String DATE_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.DATE_MEMO";
        public static final String HOUR_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.HOUR_MEMO";
        public static final String PLACE_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.PLACE_MEMO";
        public static final String STATE_MEMO = "it.unipr.mobdev.memorandum.ViewHolder.STATE_MEMO";


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
                    intent.putExtra(ID_MEMO,m.getId());
                    intent.putExtra(TITLE_MEMO, m.getTitle());
                    intent.putExtra(DESC_MEMO, m.getDescription());
                    intent.putExtra(DATE_MEMO, m.getDate());
                    intent.putExtra(HOUR_MEMO,m.getHour());
                    intent.putExtra(PLACE_MEMO, m.getPlace());
                    intent.putExtra(STATE_MEMO, m.getState());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

