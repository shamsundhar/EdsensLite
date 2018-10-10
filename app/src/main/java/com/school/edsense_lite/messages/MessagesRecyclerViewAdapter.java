package com.school.edsense_lite.messages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.today.Schedule;

import java.util.List;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Object> items;
    private AdapterView.OnItemClickListener listener;

    public MessagesRecyclerViewAdapter(){

    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    public void setItems(List<Object> items) {
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.layout_messages_item, viewGroup, false);
        viewHolder = new ViewHolder1(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder1 vh1 = (ViewHolder1) viewHolder;
        configureViewHolder1(vh1, position);
    }

    @Override
    public int getItemCount() {
        if(items != null) {
            return this.items.size();
        }
        else{
            return 0;
        }
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }
    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        MessagesModel messagesModel = (MessagesModel) items.get(position);
        if (messagesModel != null) {
            vh1.getTitle().setText(messagesModel.get_title());
            vh1.getMessage().setText(messagesModel.get_message());
            //vh1.getDate().setText(messagesModel.get_date());
            //  vh1.bind(scheduleModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView message;
        //private TextView date;

//        public TextView getDate() {
//            return date;
//        }
//
//        public void setDate(TextView date) {
//            this.date = date;
//        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getMessage() {
            return message;
        }

        public void setMessage(TextView subtitle) {
            this.message = message;
        }

        public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            message = (TextView) v.findViewById(R.id.message);
            //date = (TextView)v.findViewById(R.id.date);
        }
        public void bind(final Schedule schedule, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
}
