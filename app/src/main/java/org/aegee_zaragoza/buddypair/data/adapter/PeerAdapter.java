package org.aegee_zaragoza.buddypair.data.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Peer;

import java.util.List;

/**
 * Created by dbarelop on 1/9/15.
 */
public class PeerAdapter extends ArrayAdapter<Peer> {
    private Activity activity;
    private List<Peer> peerList;
    private int layoutResourceId;

    public PeerAdapter(Activity activity, int textViewResourceId, List<Peer> peerList) {
        super(activity, textViewResourceId, peerList);
        this.activity = activity;
        this.peerList = peerList;
        layoutResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Peer item = getItem(position);
        View v = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layoutResourceId, null);
        } else {
            v = convertView;
        }
        TextView name = (TextView) v.findViewById(R.id.activity_peers_list_item_name);
        TextView faculty = (TextView) v.findViewById(R.id.activity_peers_list_item_faculty);
        TextView studies = (TextView) v.findViewById(R.id.activity_peers_list_item_studies);

        name.setText(item.getName() + " " + item.getSurname());
        faculty.setText(item.getFaculty());
        studies.setText(item.getStudies());

        return v;
    }
}
