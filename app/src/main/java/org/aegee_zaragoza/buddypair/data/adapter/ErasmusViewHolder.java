package org.aegee_zaragoza.buddypair.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;

public class ErasmusViewHolder extends RecyclerView.ViewHolder {
    protected TextView vName;
    protected TextView vFaculty;
    protected TextView vStudies;
    protected ImageView vAvatar;

    public ErasmusViewHolder(View v) {
        super(v);
        vName = (TextView) v.findViewById(R.id.activity_erasmus_list_item_name);
        vFaculty = (TextView) v.findViewById(R.id.activity_erasmus_list_item_faculty);
        vStudies = (TextView) v.findViewById(R.id.activity_erasmus_list_item_studies);
        vAvatar = (ImageView) v.findViewById(R.id.activity_erasmus_list_item_image);
    }
}
