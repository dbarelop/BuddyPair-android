package org.aegee_zaragoza.buddypair.data.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.Peer;

import java.util.List;

public class ErasmusAdapter extends ArrayAdapter<Erasmus> {
    private Activity activity;
    private int layoutResourceId;

    public ErasmusAdapter(Activity activity, int textViewResourceId, List<Erasmus> peerList) {
        super(activity, textViewResourceId, peerList);
        this.activity = activity;
        layoutResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Erasmus item = getItem(position);
        View v;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layoutResourceId, null);
        } else {
            v = convertView;
        }
        TextView name = (TextView) v.findViewById(R.id.activity_erasmus_list_item_name);
        TextView faculty = (TextView) v.findViewById(R.id.activity_erasmus_list_item_faculty);
        TextView studies = (TextView) v.findViewById(R.id.activity_erasmus_list_item_studies);
        ImageView avatar = (ImageView) v.findViewById(R.id.activity_erasmus_list_item_image);

        name.setText(item.getName() + " " + item.getSurname());
        faculty.setText(item.getFaculty());
        studies.setText(item.getStudies());
        if (item.isMale()) {
            avatar.setImageResource(R.drawable.male_avatar);
        } else {
            avatar.setImageResource(R.drawable.female_avatar);
        }

        return v;
    }
}
