package com.school.edsense_lite.notes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.SeverityTypeResponse;

import java.util.List;

public class UsersListAdapter extends ArrayAdapter<GetUsersForNotesResponse.Response> {
    private List<GetUsersForNotesResponse.Response> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
    }
    public void setItems(List<GetUsersForNotesResponse.Response> items) {
        this.dataSet = items;
    }

    public UsersListAdapter(List<GetUsersForNotesResponse.Response> items, Context context) {
        super(context, R.layout.layout_sectionitem);
        this.mContext=context;
        this.dataSet = items;

    }

    @Nullable
    @Override
    public GetUsersForNotesResponse.Response getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getCount() {
        return (dataSet == null) ? 0 : dataSet.size();
    }
//    @Override
//    public void onClick(View v) {
//
//        int position=(Integer) v.getTag();
//        Object object= getItem(position);
//        SectionResponse.Response dataModel=(SectionResponse.Response)object;
//
//        switch (v.getId())
//        {
//            case R.id.title:
//                Snackbar.make(v, " " +dataModel.getCompositeTagName()+" "+dataModel.getCompositeTagId(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
//    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GetUsersForNotesResponse.Response dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        UsersListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new UsersListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_sectionitem, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (UsersListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        //  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //    result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(dataModel.getDisplayName());
        //   viewHolder.title.setOnClickListener(this);
        viewHolder.title.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
