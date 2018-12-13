package com.school.edsense_lite.messages;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.school.edsense_lite.MainActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT3;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT4;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT5;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT6;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String MESSAGE_DETAILS_FRAGMENT_TAG = "MessageDetailsFragment";
    private List<MessagesResponseModel> items;
    private MessageItemClickListener listener;
    private Context context;
    private String primaryUrl;

    public MessagesRecyclerViewAdapter(Context applicationContext, String primaryUrl){
        context = applicationContext;
        this.primaryUrl = primaryUrl;
    }
    public void setOnItemClickListener(MessageItemClickListener clickListener){
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
        viewHolder = new ViewHolder1(v1,listener);
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
            vh1.getTitle().setText(messagesModel.getOrginator());

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                vh1.getMessage().setText(Html.fromHtml(messagesModel.getMessageBody(), Html.FROM_HTML_MODE_COMPACT));
//            } else {
//                vh1.getMessage().setText(Html.fromHtml(messagesModel.getSubject()));
//
//            }
            vh1.getMessage().setText(messagesModel.getSubject());

//            ShapeDrawable sd = new ShapeDrawable(new OvalShape());
//            sd.setIntrinsicHeight(100);
//            sd.setIntrinsicWidth(100);
//            sd.getPaint().setColor(Color.CYAN);
//            vh1.image.setBackground(sd);

            //"https://www.gstatic.com/webp/gallery/1.jpg"
            
            Picasso.with(context).load(primaryUrl+messagesModel.getOriginatorImageUrl()).fit()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(vh1.image);


            vh1.getDate().setText(DateTimeUtils.parseDateTime(messagesModel.getTransactionDate(), DATE_FORMAT6, DATE_FORMAT1));
            //vh1.getMessageTime().setText(messagesModel.getTimeStampMsg());
            String timestamp = messagesModel.getTimeStampMsg();
            vh1.getMessageTime().setText(timestamp.substring(timestamp.indexOf(',')+1));
            //vh1.favoriteCheck;
            //vh1.bind(messagesModel, listener);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView message;
        private TextView date;
        private TextView messageTime;
        private CheckBox favoriteCheck;
        private WeakReference<MessageItemClickListener> listenerRef;

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

        public ViewHolder1(View v, MessageItemClickListener clickListener) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            message = (TextView) v.findViewById(R.id.message);
            date = (TextView)v.findViewById(R.id.message_date);
            image = (ImageView)v.findViewById(R.id.imageView);
            messageTime = (TextView) v.findViewById(R.id.message_time);
            listenerRef = new WeakReference<>(clickListener);
            //applyFonts(v);
            v.setOnClickListener(this);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            title.setTypeface(tf);
            message.setTypeface(tf);
            date.setTypeface(tf);
            messageTime.setTypeface(tf);
        }
        public void bind(final MessagesResponseModel messagesModel, final AdapterView.OnItemClickListener listener) {

        }
        @Override
        public void onClick(View v) {
            listenerRef.get().onMessageItemClicked(getAdapterPosition());
        }
//        public void bind(final Schedule schedule, final AdapterView.OnItemClickListener listener) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
//                }
//            });
//        }
    }
}
