package org.aegee_zaragoza.buddypair.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Peer;

import java.util.List;

public class PeerAdapter extends RecyclerView.Adapter<PeerViewHolder> {
    private final List<Peer> peerList;

    public PeerAdapter(List<Peer> peerList) {
        this.peerList = peerList;
    }

    @Override
    public PeerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_peer_list_item, viewGroup, false);
        return new PeerViewHolder(itemView, peerList);
    }

    @Override
    public void onBindViewHolder(PeerViewHolder viewHolder, int i) {
        Peer p = peerList.get(i);
        viewHolder.vName.setText(p.toString());
        viewHolder.vFaculty.setText(p.getFaculty());
        viewHolder.vStudies.setText(p.getStudies());
        if (p.isMale()) {
            viewHolder.vAvatar.setImageResource(R.drawable.male_avatar);
        } else {
            viewHolder.vAvatar.setImageResource(R.drawable.female_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return peerList.size();
    }
}
