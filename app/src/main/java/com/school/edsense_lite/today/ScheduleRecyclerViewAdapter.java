package com.school.edsense_lite.today;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.school.edsense_lite.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by shyam on 2/25/2018.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int SCHEDULE_LIST_ITEM = 0;
    private final int ASSIGNMENT_LIST_ITEM = 1;
    private final int ASSIGNMENT_HEADER_ITEM = 2;
    private final int NEWS_EVENTS_LIST_ITEM = 3;
  //  private AdapterView.OnItemClickListener listener;
    private ClickListener clickListener;

    public ScheduleRecyclerViewAdapter(){}
    public void setOnClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setItems(List<Object> items) {
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == SCHEDULE_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_scheduleitem, viewGroup, false);
            viewHolder = new ViewHolder1(v1);
        }
        else if(viewType == ASSIGNMENT_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_assignmentitem, viewGroup, false);
            viewHolder = new ViewHolder2(v1);
        }
        else if(viewType == ASSIGNMENT_HEADER_ITEM){
            View v1 = inflater.inflate(R.layout.layout_header_item, viewGroup, false);
            viewHolder = new ViewHolder3(v1);
        }
        else if(viewType == NEWS_EVENTS_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_news_events_item, viewGroup, false);
            viewHolder = new ViewHolder4(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SCHEDULE_LIST_ITEM:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case ASSIGNMENT_LIST_ITEM:
                ViewHolder2 vh2 = (ViewHolder2)viewHolder;
                configureViewHolder2(vh2, position);
                break;
            case ASSIGNMENT_HEADER_ITEM:
                ViewHolder3 vh3 = (ViewHolder3)viewHolder;
                configureViewHolder3(vh3, position);
                break;
            case NEWS_EVENTS_LIST_ITEM:
                ViewHolder4 vh4 = (ViewHolder4)viewHolder;
                configureViewHolder4(vh4, position);
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
        if (items.get(position) instanceof Schedule) {
            return SCHEDULE_LIST_ITEM;
        }
        else if(items.get(position) instanceof Assignment){
            return ASSIGNMENT_LIST_ITEM;
        }
        else if(items.get(position) instanceof Header){
            return ASSIGNMENT_HEADER_ITEM;
        }
        else if(items.get(position) instanceof NewsEvents){
            return NEWS_EVENTS_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        Schedule scheduleModel = (Schedule) items.get(position);
        if (scheduleModel != null) {
            vh1.getClas().setText(scheduleModel.get_time());
            vh1.getSubject().setText(scheduleModel.get_section());
            vh1.getTopic().setText("fdfd");
            //  vh1.bind(scheduleModel, listener);
        }
    }
    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        Assignment assignmentModel = (Assignment) items.get(position);
        if (assignmentModel != null) {
            vh2.getTitle().setText(assignmentModel.get_title());
            vh2.getDueDate().setText(assignmentModel.get_dueDate());
            vh2.getStatus().setText(assignmentModel.get_status());
            //  vh1.bind(scheduleModel, listener);
        }
    }
    private void configureViewHolder3(ViewHolder3 vh3, int position) {
        Header headerModel = (Header) items.get(position);
        if (headerModel != null) {
            vh3.getTitle().setText(headerModel.getTitle());

        }
    }
    private void configureViewHolder4(ViewHolder4 vh4, int position) {
//        NewsEventsModel newsEventsModel = (NewsEventsModel) items.get(position);
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView clas;
        private TextView subject;
        private TextView topic;

        public TextView getClas() {
            return clas;
        }

        public void setClas(TextView clas) {
            this.clas = clas;
        }

        public TextView getSubject() {
            return subject;
        }

        public void setSubject(TextView subject) {
            this.subject = subject;
        }

        public TextView getTopic() {
            return topic;
        }

        public void setTopic(TextView topic) {
            this.topic = topic;
        }

        public ViewHolder1(View v) {
            super(v);
            clas = (TextView) v.findViewById(R.id.clas);
            subject = (TextView) v.findViewById(R.id.subject);
            topic = (TextView)v.findViewById(R.id.topic);
        }
        public void bind(final Schedule schedule, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView dueDate;
        private TextView status;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getDueDate() {
            return dueDate;
        }

        public void setDueDate(TextView dueDate) {
            this.dueDate = dueDate;
        }

        public TextView getStatus() {
            return status;
        }

        public void setStatus(TextView status) {
            this.status = status;
        }

        public ViewHolder2(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            dueDate = (TextView) v.findViewById(R.id.duedate);
            status = (TextView)v.findViewById(R.id.status);
        }
        public void bind(final Assignment assignment, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
    class ViewHolder3 extends RecyclerView.ViewHolder {

        private TextView title;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public ViewHolder3(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.headerTitle);
        }
        public void bind(final Header header, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
    class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout newsLayout;
        private LinearLayout eventsLayout;
        private WeakReference<ClickListener> listenerRef;

        public LinearLayout getNewsLayout() {
            return newsLayout;
        }

        public void setNewsLayout(LinearLayout newsLayout) {
            this.newsLayout = newsLayout;
        }

        public LinearLayout getEventsLayout() {
            return eventsLayout;
        }

        public void setEventsLayout(LinearLayout eventsLayout) {
            this.eventsLayout = eventsLayout;
        }

        public ViewHolder4(View v) {
            super(v);
            listenerRef = new WeakReference<>(clickListener);
            newsLayout = (LinearLayout) v.findViewById(R.id.newsLayout);
            eventsLayout = (LinearLayout) v.findViewById(R.id.eventsLayout);

            newsLayout.setOnClickListener(this);
            eventsLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == newsLayout.getId()){
                listenerRef.get().onNewsClicked(getAdapterPosition());
            }
            else if(v.getId() == eventsLayout.getId()){
                listenerRef.get().onEventsClicked(getAdapterPosition());
            }
        }
    }
}
