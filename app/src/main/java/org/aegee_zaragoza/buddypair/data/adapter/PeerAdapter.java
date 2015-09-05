package org.aegee_zaragoza.buddypair.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Peer;

import java.util.ArrayList;
import java.util.List;

public class PeerAdapter extends RecyclerView.Adapter<PeerViewHolder> {
    private List<Peer> peerList;

    public PeerAdapter(List<Peer> peerList) {
        this.peerList = new ArrayList<>(peerList);
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

    public void setModel(List<Peer> peerList) {
        this.peerList = new ArrayList<>(peerList);
        notifyDataSetChanged();
    }

    public void animateTo(List<Peer> newList) {
        applyAndAnimateRemovals(newList);
        applyAndAnimateAdditions(newList);
        applyAndAnimateMovedItems(newList);
    }

    private void applyAndAnimateRemovals(List<Peer> newList) {
        for (int i = peerList.size() - 1; i >= 0; i--) {
            final Peer p = peerList.get(i);
            if (!newList.contains(p)) {
                peerList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Peer> newList) {
        for (int i = 0, count = newList.size(); i < count; i++) {
            final Peer p = newList.get(i);
            if (!peerList.contains(p)) {
                peerList.add(i, p);
                notifyItemInserted(i);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Peer> newList) {
        for (int toPosition = newList.size() - 1; toPosition >= 0; toPosition--) {
            final Peer p = newList.get(toPosition);
            final int fromPosition = peerList.indexOf(p);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                peerList.remove(fromPosition);
                peerList.add(toPosition, p);
                notifyItemMoved(fromPosition, toPosition);
            }
        }
    }
}
