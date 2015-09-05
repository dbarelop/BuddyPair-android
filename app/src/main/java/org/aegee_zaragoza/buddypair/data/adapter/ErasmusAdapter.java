package org.aegee_zaragoza.buddypair.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;

import java.util.ArrayList;
import java.util.List;

public class ErasmusAdapter extends RecyclerView.Adapter<ErasmusViewHolder> {
    private List<Erasmus> erasmusList;

    public ErasmusAdapter(List<Erasmus> erasmusList) {
        this.erasmusList = new ArrayList<>(erasmusList);
    }

    @Override
    public ErasmusViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_erasmus_list_item, viewGroup, false);
        return new ErasmusViewHolder(itemView, erasmusList);
    }

    @Override
    public void onBindViewHolder(ErasmusViewHolder viewHolder, int i) {
        Erasmus e = erasmusList.get(i);
        viewHolder.vName.setText(e.toString());
        viewHolder.vFaculty.setText(e.getFaculty());
        viewHolder.vStudies.setText(e.getStudies());
        if (e.isMale()) {
            viewHolder.vAvatar.setImageResource(R.drawable.male_avatar);
        } else {
            viewHolder.vAvatar.setImageResource(R.drawable.female_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return erasmusList.size();
    }

    public void setModel(List<Erasmus> erasmusList) {
        this.erasmusList = new ArrayList<>(erasmusList);
        notifyDataSetChanged();
    }

    public void animateTo(List<Erasmus> newList) {
        applyAndAnimateRemovals(newList);
        applyAndAnimateAdditions(newList);
        applyAndAnimateMovedItems(newList);
    }

    private void applyAndAnimateRemovals(List<Erasmus> newList) {
        for (int i = erasmusList.size() - 1; i >= 0; i--) {
            final Erasmus e = erasmusList.get(i);
            if (!newList.contains(e)) {
                erasmusList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Erasmus> newList) {
        for (int i = 0, count = newList.size(); i < count; i++) {
            final Erasmus e = newList.get(i);
            if (!erasmusList.contains(e)) {
                erasmusList.add(i, e);
                notifyItemInserted(i);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Erasmus> newList) {
        for (int toPosition = newList.size() - 1; toPosition >= 0; toPosition--) {
            final Erasmus e = newList.get(toPosition);
            final int fromPosition = erasmusList.indexOf(e);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                erasmusList.remove(fromPosition);
                erasmusList.add(toPosition, e);
                notifyItemMoved(fromPosition, toPosition);
            }
        }
    }
}
