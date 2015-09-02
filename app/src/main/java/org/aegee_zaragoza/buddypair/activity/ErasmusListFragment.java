package org.aegee_zaragoza.buddypair.activity;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.adapter.ErasmusAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ErasmusListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_erasmus_list, container, false);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.erasmus_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final List<Erasmus> erasmusList = new ArrayList<>();
        final ErasmusAdapter adapter = new ErasmusAdapter(erasmusList);
        recyclerView.setAdapter(adapter);
        AsyncTask<Void, Void, Void> listPopulator = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                List<Erasmus> erasmus = DatabaseHelper.getErasmus();
                if (erasmus != null) {
                    erasmusList.addAll(erasmus);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();
            }
        };
        listPopulator.execute();

        return rootView;
    }
}
