package com.school.edsense_lite.events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.news.News;
import com.school.edsense_lite.today.Schedule;

import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int EVENTS_LIST_ITEM = 0;
    private AdapterView.OnItemClickListener listener;

    public EventsRecyclerViewAdapter(){}
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
        if(viewType == EVENTS_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_scheduleitem, viewGroup, false);
            viewHolder = new EventsRecyclerViewAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case EVENTS_LIST_ITEM:
                EventsRecyclerViewAdapter.ViewHolder1 vh1 = (EventsRecyclerViewAdapter.ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            default:
                //  RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                //  configureDefaultViewHolder(vh, position);
                break;
        }
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
        if (items.get(position) instanceof Event) {
            return EVENTS_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(EventsRecyclerViewAdapter.ViewHolder1 vh1, int position) {
        Event eventModel = (Event) items.get(position);
        if (eventModel != null) {
            vh1.getTitle().setText(eventModel.get_title());
            vh1.getSubtitle().setText(eventModel.get_description());
            //  vh1.bind(scheduleModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title, subtitle;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(TextView subtitle) {
            this.subtitle = subtitle;
        }

        public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
        }
        public void bind(final Event event, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }

}
