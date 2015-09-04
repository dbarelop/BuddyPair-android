package org.aegee_zaragoza.buddypair.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.activity.StudentInfoActivity;
import org.aegee_zaragoza.buddypair.data.Peer;

import java.util.List;

public class PeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final List<Peer> peerList;
    private final Context context;
    protected TextView vName;
    protected TextView vFaculty;
    protected TextView vStudies;
    protected ImageView vAvatar;

    public PeerViewHolder(View v, List<Peer> peerList) {
        super(v);
        this.peerList = peerList;
        context = v.getContext();
        v.setOnClickListener(this);
        vName = (TextView) v.findViewById(R.id.activity_peer_list_item_name);
        vFaculty = (TextView) v.findViewById(R.id.activity_peer_list_item_faculty);
        vStudies = (TextView) v.findViewById(R.id.activity_peer_list_item_studies);
        vAvatar = (ImageView) v.findViewById(R.id.activity_peer_list_item_image);
    }

    @Override
    public void onClick(View v) {
        Peer peer = peerList.get(getAdapterPosition());
        final Intent intent = new Intent(context, StudentInfoActivity.class);
        intent.putExtra("student", peer);
        context.startActivity(intent);
    }
}
