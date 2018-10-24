package com.school.edsense_lite.attendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.events.Event;

import java.util.List;

public class AttendanceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int ATTENDANCE_LIST_ITEM = 0;
    private AdapterView.OnItemClickListener listener;

    public AttendanceRecyclerViewAdapter(){}
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
        if(viewType == ATTENDANCE_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_attendanceitem, viewGroup, false);
            viewHolder = new AttendanceRecyclerViewAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ATTENDANCE_LIST_ITEM:
                AttendanceRecyclerViewAdapter.ViewHolder1 vh1 = (AttendanceRecyclerViewAdapter.ViewHolder1) viewHolder;
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
        if (items.get(position) instanceof GetUserResponse.Response) {
            return ATTENDANCE_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(AttendanceRecyclerViewAdapter.ViewHolder1 vh1, int position) {
        GetUserResponse.Response attendanceModel = (GetUserResponse.Response) items.get(position);
        if (attendanceModel != null) {
            vh1.getName().setText(attendanceModel.getDisplayName());
            vh1.getReason().setText(attendanceModel.getIsAttended());
            vh1.getStatus().setText(attendanceModel.getStudentUserId());
            //  vh1.bind(scheduleModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView status;
        private TextView reason;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getStatus() {
            return status;
        }

        public void setStatus(TextView status) {
            this.status = status;
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
            status = (TextView) v.findViewById(R.id.status);
            reason = (TextView) v.findViewById(R.id.reason);
        }
        public void bind(final Attendance attendance, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }

}
