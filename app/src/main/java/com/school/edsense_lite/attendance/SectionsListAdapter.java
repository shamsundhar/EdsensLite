package com.school.edsense_lite.attendance;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.edsense_lite.R;

import java.util.ArrayList;

public class SectionsListAdapter extends ArrayAdapter<SectionResponse.Response>{

    private ArrayList<SectionResponse.Response> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
    }

    public SectionsListAdapter(ArrayList<SectionResponse.Response> data, Context context) {
        super(context, R.layout.layout_sectionitem, data);
        this.dataSet = data;
        this.mContext=context;

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
        SectionResponse.Response dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_sectionitem, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

      //  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
    //    result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(dataModel.getCompositeTagName());
     //   viewHolder.title.setOnClickListener(this);
        viewHolder.title.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
