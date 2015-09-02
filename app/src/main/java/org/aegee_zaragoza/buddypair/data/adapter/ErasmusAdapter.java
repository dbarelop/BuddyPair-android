package org.aegee_zaragoza.buddypair.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;

import java.util.List;

public class ErasmusAdapter extends RecyclerView.Adapter<ErasmusViewHolder> {
    private List<Erasmus> erasmus;

    public ErasmusAdapter(List<Erasmus> erasmus) {
        this.erasmus = erasmus;
    }

    @Override
    public ErasmusViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_erasmus_list_item, viewGroup, false);
        return new ErasmusViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ErasmusViewHolder viewHolder, int i) {
        Erasmus e = erasmus.get(i);
        viewHolder.vName.setText(e.getName() + " " + e.getSurname());
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
        return erasmus.size();
    }
}
