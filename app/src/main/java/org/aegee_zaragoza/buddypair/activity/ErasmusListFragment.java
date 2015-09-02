package org.aegee_zaragoza.buddypair.activity;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.adapter.ErasmusAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.List;

public class ErasmusListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_erasmus_list, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.erasmus_list);
        AsyncTask<Void, Void, List<Erasmus>> listPopulator = new AsyncTask<Void, Void, List<Erasmus>>() {
            @Override
            protected List<Erasmus> doInBackground(Void... params) {
                return DatabaseHelper.getErasmus();
            }

            @Override
            protected void onPostExecute(final List<Erasmus> erasmusList) {
                ErasmusAdapter adapter = new ErasmusAdapter(ErasmusListFragment.this.getActivity(), R.layout.fragment_erasmus_list_item, erasmusList);
                listView.setAdapter(adapter);
            }
        };
        listPopulator.execute();

        return rootView;
    }
}
