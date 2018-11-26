package com.school.edsense_lite.today;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.school.edsense_lite.R;
import com.school.edsense_lite.model.AssignmentResponseModel;
import com.school.edsense_lite.model.Row;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.DateTimeUtils;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.school.edsense_lite.today.Header.ASSIGNMENT_HEADER;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT4;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT5;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT6;

/**
 * Created by shyam on 2/25/2018.
 */

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int SCHEDULE_LIST_ITEM = 0;
    private final int ASSIGNMENT_LIST_ITEM = 1;
    private final int ASSIGNMENT_HEADER_ITEM = 2;
    private final int NEWS_EVENTS_LIST_ITEM = 3;
    private final int SCHEDULE_HEADER_ITEM = 4;
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
        else if(viewType == SCHEDULE_HEADER_ITEM){
            View v1 = inflater.inflate(R.layout.layout_schedule_header_item, viewGroup, false);
            viewHolder = new ViewHolder5(v1);
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
            case SCHEDULE_HEADER_ITEM:
                ViewHolder5 vh5 = (ViewHolder5)viewHolder;
                configureViewHolder5(vh5, position);
                break;
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
        if (items.get(position) instanceof Row) {
            return SCHEDULE_LIST_ITEM;
        }
        else if(items.get(position) instanceof AssignmentResponseModel){
            return ASSIGNMENT_LIST_ITEM;
        }
        else if(items.get(position) instanceof Header){
            if(((Header)items.get(position)).getHeaderType() == ASSIGNMENT_HEADER){
                return ASSIGNMENT_HEADER_ITEM;
            }
            else{
                return SCHEDULE_HEADER_ITEM;
            }
        }
        else if(items.get(position) instanceof NewsEvents){
            return NEWS_EVENTS_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        Row scheduleModel = (Row) items.get(position);
        if (scheduleModel != null) {
            vh1.getClas().setText(scheduleModel.getGradeName());
            vh1.getSubject().setText(scheduleModel.getSubject());
            vh1.getTopic().setText(scheduleModel.getTitle());
            vh1.getTime().setText(scheduleModel.getStartTimeSlot());
            //  vh1.bind(scheduleModel, listener);
        }
    }
    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        AssignmentResponseModel assignmentModel = (AssignmentResponseModel) items.get(position);
        if (assignmentModel != null) {
            vh2.getTitle().setText(assignmentModel.getName());
            String startDate = assignmentModel.getStartDate();
            if(startDate != null && startDate.trim().length() > 0) {
                startDate = DateTimeUtils.parseDateTime(startDate, Constants.DATE_FORMAT6, Constants.DATE_FORMAT7);
                startDate = startDate.replace(',','\n');
                vh2.getDate().setText(startDate);
            }
            String endDate = assignmentModel.getEndDate();
            if(endDate != null && endDate.trim().length() > 0) {
                endDate = DateTimeUtils.parseDate(endDate, DATE_FORMAT6, DATE_FORMAT4);
                vh2.getDueDate().setText("Due: "+endDate);
            }

            if(assignmentModel.getDescription() != null && assignmentModel.getDescription().trim().length() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    vh2.getDescription().setText(Html.fromHtml(assignmentModel.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    vh2.getDescription().setText(Html.fromHtml(assignmentModel.getDescription()));
                }
            }
            //  vh1.bind(scheduleModel, listener);
        }
    }
    private void configureViewHolder3(ViewHolder3 vh3, int position) {
        Header headerModel = (Header) items.get(position);
        if (headerModel != null) {
            vh3.getTitle().setText(headerModel.getTitle());

        }
    }
    private void configureViewHolder5(ViewHolder5 vh5, int position) {
        Header headerModel = (Header) items.get(position);
        if (headerModel != null) {
            vh5.getTitle().setText(headerModel.getTitle());

        }
    }
    private void configureViewHolder4(ViewHolder4 vh4, int position) {
//        NewsEventsModel newsEventsModel = (NewsEventsModel) items.get(position);
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView clas;
        private TextView subject;
        private TextView topic;

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        private TextView time;

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
            time = (TextView)v.findViewById(R.id.time);

            applyFonts(v);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            clas.setTypeface(tf);
            subject.setTypeface(tf);
            topic.setTypeface(tf);
            time.setTypeface(tf);
        }
        public void bind(final ScheduleResponse schedule, final AdapterView.OnItemClickListener listener) {
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
        private TextView description;
        private TextView date;

        public TextView getDate() {
            return date;
        }
        public void setDate(TextView date) {
            this.date = date;
        }
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

        public TextView getDescription() {
            return description;
        }

        public void setDescription(TextView description) {
            this.description = description;
        }

        public ViewHolder2(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            dueDate = (TextView) v.findViewById(R.id.duedate);
            description = (TextView)v.findViewById(R.id.description);
            date = (TextView)v.findViewById(R.id.time);
            applyFonts(v);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            String fontPath2 = "fonts/framd.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            Typeface tf2 = Typeface.createFromAsset(v.getContext().getAssets(), fontPath2);

            title.setTypeface(tf);
            dueDate.setTypeface(tf);
            description.setTypeface(tf2);
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
            applyFonts(v);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            String fontPath2 = "fonts/framd.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            title.setTypeface(tf);
        }
        public void bind(final Header header, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
    class ViewHolder5 extends RecyclerView.ViewHolder {

        private TextView title;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public ViewHolder5(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.headerTitle);
            applyFonts(v);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            title.setTypeface(tf);
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
        private TextView news;
        private TextView events;
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

            news = (TextView)v.findViewById(R.id.news);
            events = (TextView)v.findViewById(R.id.events);

            applyFonts(v);
            newsLayout.setOnClickListener(this);
            eventsLayout.setOnClickListener(this);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            news.setTypeface(tf);
            events.setTypeface(tf);
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
