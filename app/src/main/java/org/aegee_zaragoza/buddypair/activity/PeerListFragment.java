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
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.adapter.PeerAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PeerListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_peer_list, container, false);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.peer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final List<Peer> peerList = new ArrayList<>();
        final PeerAdapter adapter = new PeerAdapter(peerList);
        recyclerView.setAdapter(adapter);
        AsyncTask<Void, Void, Void> listPopulator = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                List<Peer> peers = DatabaseHelper.getPeers();
                if (peers != null) {
                    peerList.addAll(peers);
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
