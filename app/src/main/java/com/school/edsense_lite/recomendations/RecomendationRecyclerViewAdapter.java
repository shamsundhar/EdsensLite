package com.school.edsense_lite.recomendations;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.messages.MessagesModel;
import com.school.edsense_lite.messages.MessagesRecyclerViewAdapter;
import com.school.edsense_lite.today.Schedule;

import java.util.List;

public class RecomendationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecomendationModel>  itemList;
    private Context context;

    public RecomendationRecyclerViewAdapter(Context context, List<RecomendationModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = inflater.inflate(R.layout.layout_recomendation_item, parent, false);
        viewHolder = new ViewHolder1(v1);

        return viewHolder;
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }
    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        RecomendationModel recomendationModel = (RecomendationModel) itemList.get(position);
        if (recomendationModel != null) {
            vh1.getName_txt().setText(recomendationModel.getName());
            //vh1.getPhoto().setImageResource(recomendationModel.getImagePath());

        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 vh1 = (ViewHolder1) holder;
        configureViewHolder1(vh1, position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        private ImageView photo;
        private TextView name_txt;

        public ImageView getPhoto() {
            return photo;
        }

        public void setPhoto(ImageView photo) {
            this.photo = photo;
        }

        public TextView getName_txt() {
            return name_txt;
        }

        public void setName_txt(TextView name_txt) {
            this.name_txt = name_txt;
        }

        public ViewHolder1(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.rec_photo);
            name_txt = (TextView) itemView.findViewById(R.id.name_txt);
        }
        public void bind(final RecomendationModel recomendationModel, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(schedule.get_section(), schedule.get_time());
                }
            });
        }
    }
}
