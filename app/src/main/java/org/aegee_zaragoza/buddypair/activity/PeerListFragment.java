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
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.adapter.PeerAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.List;

public class PeerListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_peer_list, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.peer_list);
        AsyncTask<Void, Void, List<Peer>> listPopulator = new AsyncTask<Void, Void, List<Peer>>() {
            @Override
            protected List<Peer> doInBackground(Void... params) {
                return DatabaseHelper.getPeers();
            }

            @Override
            protected void onPostExecute(final List<Peer> peerList) {
                PeerAdapter adapter = new PeerAdapter(PeerListFragment.this.getActivity(), R.layout.fragment_peer_list_item, peerList);
                listView.setAdapter(adapter);
            }
        };
        listPopulator.execute();

        return rootView;
    }
}
