package org.aegee_zaragoza.buddypair.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.activity.StudentInfoActivity;
import org.aegee_zaragoza.buddypair.data.Erasmus;

import java.util.List;

public class ErasmusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final List<Erasmus> erasmusList;
    private final Context context;
    protected TextView vName;
    protected TextView vFaculty;
    protected TextView vStudies;
    protected ImageView vAvatar;

    public ErasmusViewHolder(View v, List<Erasmus> erasmusList) {
        super(v);
        this.erasmusList = erasmusList;
        context = v.getContext();
        v.setOnClickListener(this);
        vName = (TextView) v.findViewById(R.id.activity_erasmus_list_item_name);
        vFaculty = (TextView) v.findViewById(R.id.activity_erasmus_list_item_faculty);
        vStudies = (TextView) v.findViewById(R.id.activity_erasmus_list_item_studies);
        vAvatar = (ImageView) v.findViewById(R.id.activity_erasmus_list_item_image);
    }

    @Override
    public void onClick(View v) {
        Erasmus erasmus = erasmusList.get(getAdapterPosition());
        final Intent intent = new Intent(context, StudentInfoActivity.class);
        intent.putExtra("student", erasmus);
        context.startActivity(intent);
    }
}
