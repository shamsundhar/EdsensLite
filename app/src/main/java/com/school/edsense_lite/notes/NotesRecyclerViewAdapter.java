package com.school.edsense_lite.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.Attendance;

import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int NOTES_LIST_ITEM = 0;
    private AdapterView.OnItemClickListener listener;

    public NotesRecyclerViewAdapter(){}
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
        if(viewType == NOTES_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_attendanceitem, viewGroup, false);
            viewHolder = new NotesRecyclerViewAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case NOTES_LIST_ITEM:
                NotesRecyclerViewAdapter.ViewHolder1 vh1 = (NotesRecyclerViewAdapter.ViewHolder1) viewHolder;
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
        if (items.get(position) instanceof Note) {
            return NOTES_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(NotesRecyclerViewAdapter.ViewHolder1 vh1, int position) {
        Note notesModel = (Note) items.get(position);
        if (notesModel != null) {
            vh1.getName().setText(notesModel.get_name());
            vh1.getReason().setText(notesModel.get_reason());
            vh1.getTraits().setText(notesModel.get_traits());
            //  vh1.bind(scheduleModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView traits;
        private TextView reason;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getTraits() {
            return traits;
        }

        public void setTraits(TextView status) {
            this.traits = status;
        }

        public TextView getReason() {
            return reason;
        }

        public void setReason(TextView reason) {
            this.reason = reason;
        }

        public ViewHolder1(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            traits = (TextView) v.findViewById(R.id.status);
            reason = (TextView) v.findViewById(R.id.reason);
        }
        public void bind(final Note note, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }

}
