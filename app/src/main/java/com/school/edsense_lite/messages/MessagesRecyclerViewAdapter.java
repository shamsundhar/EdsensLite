package com.school.edsense_lite.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.school.edsense_lite.R;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.today.Schedule;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MessagesResponseModel> items;
    private AdapterView.OnItemClickListener listener;
    private Context context;

    public MessagesRecyclerViewAdapter(Context applicationContext){
        context = applicationContext;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener){
        this.listener = clickListener;
    }
    public void setItems(List<MessagesResponseModel> items) {
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
        MessagesResponseModel messagesModel = (MessagesResponseModel) items.get(position);
        if (messagesModel != null) {
            vh1.getTitle().setText(messagesModel.getContextDisplayName());
            vh1.getMessage().setText(messagesModel.getMessageBody());
//            Glide.with(context)
//                    .load("https://glendale.tst.edsense.co.in"+messagesModel.getOriginatorImageUrl())
//                    .into(vh1.image);
            Picasso.with(context).load("https://glendale.tst.edsense.co.in"+messagesModel.getOriginatorImageUrl()).fit()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(vh1.image);
            vh1.getDate().setText(messagesModel.getTransactionDate());
            vh1.messageTime.setText(messagesModel.getTimeStampMsg());
            //vh1.favoriteCheck;
            //  vh1.bind(scheduleModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView message;
        private TextView date;
        private TextView messageTime;
        private CheckBox favoriteCheck;

        public CheckBox getFavoriteCheck() {
            return favoriteCheck;
        }

        public void setFavoriteCheck(CheckBox favoriteCheck) {
            this.favoriteCheck = favoriteCheck;
        }

        public TextView getMessageTime() {
            return messageTime;
        }

        public void setMessageTime(TextView messageTime) {
            this.messageTime = messageTime;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        private ImageView image;

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
            date = (TextView)v.findViewById(R.id.message_date);
            image = (ImageView)v.findViewById(R.id.imageView);
            messageTime = (TextView) v.findViewById(R.id.message_time);
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
