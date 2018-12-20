package com.school.edsense_lite.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.GetUserResponseModel;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int NOTES_LIST_ITEM = 0;
    private ClickListener clickListener;

    public NotesRecyclerViewAdapter(){}
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
        if(viewType == NOTES_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_notesitem, viewGroup, false);
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
        if (items.get(position) instanceof GetUserNotesResponse.Response) {
            return NOTES_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(NotesRecyclerViewAdapter.ViewHolder1 vh1, int position) {
        GetUserNotesResponse.Response notesModel = (GetUserNotesResponse.Response) items.get(position);
        if (notesModel != null) {
            vh1.getName().setText(notesModel.getStudentName());
            List<GetUserNotesResponse.Tag> tags =  notesModel.getTags();
            String traitsString = "";
            if(tags != null) {
                for (int i = 0; i < tags.size(); i++) {
                    GetUserNotesResponse.Tag tag = tags.get(i);
                    if (traitsString.isEmpty()) {
                        traitsString = tag.getTagName();
                    } else {
                        traitsString = traitsString + ", " + tag.getTagName();
                    }
                }
            }
            vh1.getTraits().setText("Traits: " + traitsString);
            vh1.getReason().setText("Notes: " + notesModel.getNote());
            vh1.getBy().setText("By: "+notesModel.getCreatedByName());
            vh1.bind(notesModel, clickListener, position);
        }
    }


    class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView traits;
        private TextView reason;



        private TextView by;
        private ImageView modifyButton;
        private WeakReference<ClickListener> listenerRef;
        public ImageView getModifyButton() {
            return modifyButton;
        }

        public void setModifyButton(ImageView modifyButton) {
            this.modifyButton = modifyButton;
        }

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
        public TextView getBy() {
            return by;
        }

        public void setBy(TextView by) {
            this.by = by;
        }

        public ViewHolder1(View v) {
            super(v);
            listenerRef = new WeakReference<>(clickListener);
            name = (TextView) v.findViewById(R.id.name);
            traits = (TextView) v.findViewById(R.id.status);
            reason = (TextView) v.findViewById(R.id.reason);
            by = (TextView)v.findViewById(R.id.by);
            modifyButton = (ImageView)v.findViewById(R.id.modifyButton);
            //modifyButton.setOnClickListener(this);
        }
        public void bind(final GetUserNotesResponse.Response notesModel, final ClickListener listener, final int position) {
            modifyButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onModifyButtonClicked(notesModel, position);
                }
            });
        }
        @Override
        public void onClick(View v) {
//            if(v.getId() == modifyButton.getId()){
//                listenerRef.get().onModifyButtonClicked( , getAdapterPosition());
//            }
        }
    }

}
